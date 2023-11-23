package com.fourkites.ocean.es.writer.controller;

import com.fourkites.ocean.es.writer.model.OceanInfo;
import com.fourkites.ocean.es.writer.service.OceanInfoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("oceanservice/api/oceanInfo")
@RequiredArgsConstructor
public class OceanInfoController {
    @NonNull
    OceanInfoService oceanInfoService;
    @GetMapping("/{trackindId}")
    public Optional<OceanInfo> fetchOceanInfoFromDb(@PathVariable("trackindId") Long trackingId){
        return oceanInfoService.getOceaninfoFromDB(trackingId);
    }

    @PostMapping("/")
    public Map<Long, OceanInfo> fetchOceanInfosFromDb(@RequestBody List<Long> trackingIds){
        return oceanInfoService.getOceaninfoFromDB(trackingIds);
    }
}
