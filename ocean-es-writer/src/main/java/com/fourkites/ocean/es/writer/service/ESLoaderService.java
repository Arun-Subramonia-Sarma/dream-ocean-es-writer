package com.fourkites.ocean.es.writer.service;

import com.fourkites.ocean.es.writer.annotation.TrackExecutionTime;
import com.fourkites.ocean.es.writer.config.properties.TrackingServiceProperties;
import com.fourkites.ocean.es.writer.model.Load;
import com.fourkites.ocean.es.writer.model.OceanInfo;
import com.fourkites.ocean.es.writer.model.PageInfo;
import com.fourkites.ocean.es.writer.repository.LoadRepository;
import com.fourkites.ocean.es.writer.sao.TrackingService;
import com.fourkites.ocean.es.writer.utils.TimerUtils;
import com.fourkites.ocean.es.writer.utils.Utilities;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ESLoaderService {

    @NonNull
    private TrackingService trackingService;

    @NonNull
    private OceanInfoService oceanInfoService;

    @NonNull
    private LoadRepository loadRepository;

    @NonNull
    private ExecutorService executorService;

    @NonNull
    private TrackingServiceProperties trackingServiceProperties;

    @NonNull
    private RestHighLevelClient elasticsearchClient;

    @TrackExecutionTime("save load to elastic search using a tracking id")
    public boolean saveLoadFromTrackingToES(Long trackingId) throws ExecutionException, InterruptedException {
        CompletableFuture<Optional<Load>> trackingServiceFuture=callTrackingService(trackingId);
        CompletableFuture<Optional<OceanInfo>> oceanInfoOptionalFuture=fetchTrackingFromDB(trackingId);

        Optional<Load> loadOptional=trackingServiceFuture.get();
        Optional<OceanInfo> oceanInfoOptional=oceanInfoOptionalFuture.get();
        if(loadOptional.isPresent()){
            if(oceanInfoOptional.isPresent()){
                Load load=loadOptional.get();
                OceanInfo oceanInfo=oceanInfoOptional.get();
                load.setOceanInfo(oceanInfo);
                CompletableFuture<Long> loadFuture = saveLoadToES(load);
                loadFuture.get();
                return true;
            }
        }
        return false;
    }

    @TrackExecutionTime("save load to elastic search using a tracking id list")
    public Map<String,List<Long>> saveLoadFromTrackingToES(List<Long> trackingIds) throws ExecutionException, InterruptedException {
        Map<String,List<Long>> result=initResult(trackingIds);
        if(trackingIds.size()>500)
            return result;
        CompletableFuture<Map<Long,OceanInfo>> oceanInfoMapFromDBFuture=fetchTrackingFromDB(trackingIds);
        //CompletableFuture<Map<Long,Load>> loadsFromTrackingFuture=callTrackingService(trackingIds);


        //Map<Long, Load> loadsFromTracking=loadsFromTrackingFuture.get();
        Map<Long, Load> loadsFromTracking = callTrackingServiceForAllPage(trackingIds);
        Map<Long, OceanInfo> oceanInfoMapFromDB=oceanInfoMapFromDBFuture.get();
        List<Load> loadsToSave=getOceanInfoLoadedLoads(loadsFromTracking, oceanInfoMapFromDB);
        try {
            CompletableFuture<List<Long>> savedTrackingIdsFuture = saveLoadToES(loadsToSave);
            List<Long> savedTrackingIds = savedTrackingIdsFuture.get();
            result.get("saved").addAll(savedTrackingIds);
            result.get("unsaved").removeAll(savedTrackingIds);
        }catch(Exception e){
            log.error("[saveLoadFromTrackingToES] The following exception happened while saving the Loads into Elastic search",e);
        }
//        List<CompletableFuture<Long>> saveLoadsFuture=new ArrayList<>();
//        loadsFromTracking.keySet().forEach(trackingId -> {
//            Load load=loadsFromTracking.get(trackingId);
//            if(load!=null){
//               OceanInfo oceanInfo= oceanInfoMapFromDB.get(trackingId);
//                if(oceanInfo!=null) {
//                    load.setOceanInfo(oceanInfo);
//                    //saveLoadToES(load);
//                    //saveLoadsFuture.add(saveLoadToES(load));
//                }
//            }
//        });
        //saveLoadToES(loadsFromEs);
        //processSaveResult(saveLoadsFuture, result.get("saved"), result.get("unsaved"));
        return result;
    }

    private Map<String,List<Long>> initResult(List<Long> trackingIds){
        Map<String,List<Long>> result=new HashMap<>();
        List<Long> saved=new ArrayList<>();
        List<Long> unSaved=new ArrayList<>(trackingIds);
        result.put("saved",saved);
        result.put("unsaved",unSaved);
        return result;
    }

    /**********
     *
     * @param trackingId
     * @return
     *
     * Description: Method to fetch the load from tracking service
     */


    private CompletableFuture<Optional<Load>> callTrackingService(Long trackingId){
        return CompletableFuture.supplyAsync(()->{
            TimerUtils timerUtils=TimerUtils.startTimer();
            try {

                log.info("[callTrackingService] Calling tracking service to fetch the load with tracking id {}",trackingId);
                return trackingService.fetchLoadFromTrackingServiceByTrackingId(trackingId);
            } catch (Exception e) {
                log.error("[callTrackingService] Exception while calling Tracking service with tracking id {}", trackingId, e);
                return Optional.empty();
            }
        });
    }

    private Map<Long,Load> callTrackingServiceForAllPage(List<Long> trackingIds) throws ExecutionException, InterruptedException {
        //Map<Long,Load> trackingInfoMap=new HashMap<>();
        PageInfo pageInfo= Utilities.getPageInfo(1,trackingServiceProperties.getPageSize(), trackingIds.size());
        int pageCount=pageInfo.getPageCount();
        log.debug("The total page to load from tracking services {}", pageCount);
        List<CompletableFuture<Map<Long,Load>>> loadInfoFutureList=new ArrayList<>();
        for(int i=0;i<pageCount;i++){
            pageInfo= Utilities.getPageInfo(i+1,trackingServiceProperties.getPageSize(), trackingIds.size());
            log.info("[callTrackingServiceForAllPage] Loading the tracking info with page number {}, start index {} and end index {}",(i+1),pageInfo.getStartIndex(), pageInfo.getEndIndex());
            loadInfoFutureList.add(callTrackingService(trackingIds.subList(pageInfo.getStartIndex(), pageInfo.getEndIndex())));
        }
        return processMultipleFuture(loadInfoFutureList);
        //return trackingInfoMap;
    }

    private CompletableFuture<Map<Long,Load>> callTrackingService(List<Long> trackingIds){
        return CompletableFuture.supplyAsync(()->{
            try {
                log.info("[callTrackingService] Calling tracking service to fetch the load with tracking ids {}",trackingIds);
                return trackingService.fetchLoadFromTrackingServiceByTrackingId(trackingIds);
            } catch (Exception e) {
                log.error("[callTrackingService] Exception while calling Tracking service with tracking id {}", trackingIds, e);
                return null;
            }
        });
    }

    /*********
     *
     * @param trackingId
     * @return
     *
     * Description: Method to fetch to OceanInfo from the Ocean Database
     */

    private CompletableFuture<Optional<OceanInfo>> fetchTrackingFromDB(Long trackingId){
        return CompletableFuture.supplyAsync(()->{
            log.info("[fetchTrackingFromDB] Fetching the ocean info from the OCean DB for the tracking id {}", trackingId);
            return oceanInfoService.getOceaninfoFromDB(trackingId);
        },executorService);
    }

    private CompletableFuture<Map<Long, OceanInfo>> fetchTrackingFromDB(List<Long> trckingIds){
        return CompletableFuture.supplyAsync(()->{
            log.info("[fetchTrackingFromDB] Fetching the ocean info from the OCean DB for the tracking ids {}", trckingIds);
            return oceanInfoService.getOceaninfoFromDB(trckingIds);
        },executorService);
    }

    /***************
     *
     * @param loadsFromTracking
     * @param oceanInfoMapFromDB
     * @return
     *
     * Description: Load the OceanInfo fetched from Ocean DB to the Load fetched from the Tracking Service
     */
    private List<Load> getOceanInfoLoadedLoads(Map<Long, Load> loadsFromTracking, Map<Long, OceanInfo> oceanInfoMapFromDB){
        List<Load> toSaveLoads=new ArrayList<>();
        log.info("[getOceanInfoLoadedLoads] Mapping the oceans info fetched from Ocean DB to the Loads fetched from tracking service");
        loadsFromTracking.keySet().stream()
                .forEach(trackingId->{
                    Load load = loadsFromTracking.get(trackingId);
                    OceanInfo oceanInfo=oceanInfoMapFromDB.get(trackingId);
                    if(load!=null && oceanInfo!=null){
                        log.debug("[getOceanInfoLoadedLoads] Mapping the ocean info fetched from Ocean DB to the Load fetched from tracking service for tracking id {}",trackingId);
                        load.setOceanInfo(oceanInfo);
                        toSaveLoads.add(load);
                    }
                });
        return toSaveLoads;
    }

    /*********
     *
     * @param saveLoadsFuture
     * @param saved
     * @param unSaved
     *
     * Descriotion: Method will be used when loads are saved individually
     */
    private void processSaveResult(List<CompletableFuture<Long>> saveLoadsFuture, List<Long> saved, List<Long> unSaved){
        saveLoadsFuture.stream().forEach(future->{
            try {
                Long savedLoadId=future.get();
                saved.add(savedLoadId);
                unSaved.remove(savedLoadId);
            } catch (Exception e) {
                log.error("Exception while calling the get method while saving the load into elastic search ",e);
            }
        });
    }


    /***************
     *
     * @param loads
     * @return
     *
     * Description: Method to save the load information into Elastic search
     */
    private CompletableFuture<List<Long>> saveLoadToES(List<Load> loads){
        return CompletableFuture.supplyAsync(()->{
            log.info("[saveLoadToES] Saving the loads info to the elastic search");
            TimerUtils timerUtils = TimerUtils.startTimer();
            Iterable<Load> savedLoads = loadRepository.saveAll(loads);
            log.info("[saveLoadToES] Save into elastic search completed and now fetching all saved tracking ids");
            List<Long> savedLoadIds = new ArrayList<>();
            for (Load savedLoad : savedLoads) {
                savedLoadIds.add(savedLoad.getId());
            }
            timerUtils.endTimer();
            log.info("[saveLoadToES] Time taken to save the load with tracking ids {} to elastic search is {} ms", savedLoadIds, timerUtils.getExecutionTime());
            return savedLoadIds;
        });
    }

    private CompletableFuture<Long> saveLoadToES(Load load){
        return CompletableFuture.supplyAsync(()->{
            log.info("[saveLoadToES] Saving the load info to the elastic search for tracking id {}",load.getId());
            TimerUtils timerUtils=TimerUtils.startTimer();
            Load savedLoad= loadRepository.save(load);
            timerUtils.endTimer();
            log.info("[saveLoadToES] Time taken to save the load with tracking id {} to elastic search is {} ms",load.getId(),timerUtils.getExecutionTime());
            return savedLoad.getId();
        },executorService);
    }


    private Map<Long, Load> processMultipleFuture(List<CompletableFuture<Map<Long,Load>>> loadFutureList) throws ExecutionException, InterruptedException {
        Map<Long, Load> loadMap=new HashMap<>();
        for(CompletableFuture<Map<Long,Load>> loadFuture:loadFutureList){
            loadMap.putAll(loadFuture.get());
        }
        return loadMap;
    }
}
