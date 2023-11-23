package com.fourkites.ocean.es.writer.controller;

import com.fourkites.ocean.es.writer.annotation.TrackExecutionTime;
import com.fourkites.ocean.es.writer.model.GroupedResponse;
import com.fourkites.ocean.es.writer.model.Load;
import com.fourkites.ocean.es.writer.model.enums.GroupingColumn;
import com.fourkites.ocean.es.writer.service.ESLoaderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("oceanservice/api/esloader")
public class ESLoadController {

    @NonNull
    private ESLoaderService esLoaderService;

    @TrackExecutionTime("save the load from the tracking service to elastic search")
    @PostMapping("/tracking/{trackingId}")
    public boolean saveLoadInfoToEs(@PathVariable("trackingId") Long trackingId) throws ExecutionException, InterruptedException, NoSuchAlgorithmException, InvalidKeyException {
        return esLoaderService.saveLoadFromTrackingToES(trackingId);
    }

    @TrackExecutionTime("save all the loads from the tracking service to elastic search")
    @PostMapping("/tracking")
    public Map<String, List<Long>> saveLoadInfoToEs(@RequestBody List<Long> trackingIds) throws ExecutionException, InterruptedException, NoSuchAlgorithmException, InvalidKeyException {
        return esLoaderService.saveLoadFromTrackingToES(trackingIds);
    }


}
