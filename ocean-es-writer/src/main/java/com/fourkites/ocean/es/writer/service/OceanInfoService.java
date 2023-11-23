package com.fourkites.ocean.es.writer.service;

import com.fourkites.ocean.es.writer.annotation.TrackExecutionTime;
import com.fourkites.ocean.es.writer.dbao.TrackingDao;
import com.fourkites.ocean.es.writer.model.OceanInfo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OceanInfoService {

    @NonNull
    private TrackingDao trackingDao;


    @TrackExecutionTime("Load ocean info from db")
    public Optional<OceanInfo> getOceaninfoFromDB(Long trackingId){
        Optional<OceanInfo>  oceanInfoOptional= trackingDao.fetchOceanInfoFromDB(trackingId);
        return oceanInfoOptional;
    }

    @TrackExecutionTime("Load oceans info from db")
    public Map<Long, OceanInfo> getOceaninfoFromDB(List<Long> trackingIds){
        Map<Long, OceanInfo> oceanInfoMap= trackingDao.fetchOceanInfoFromDb(trackingIds);
        return oceanInfoMap;
    }
}
