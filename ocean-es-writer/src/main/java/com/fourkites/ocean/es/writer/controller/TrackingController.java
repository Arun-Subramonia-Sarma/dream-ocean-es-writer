package com.fourkites.ocean.es.writer.controller;

import com.fourkites.ocean.es.writer.model.Load;
import com.fourkites.ocean.es.writer.sao.TrackingService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("oceanservice/tracking")
@RequiredArgsConstructor
public class TrackingController {
    @NonNull
    private TrackingService trackingService;

    @GetMapping("/{trackingId}")
    public Optional<Load> getLoadFromTrackingForTrackingId(@PathVariable("trackingId") Long trackingId) throws NoSuchAlgorithmException, InvalidKeyException {
        return trackingService.fetchLoadFromTrackingServiceByTrackingId(trackingId);
    }

    @PostMapping("/")
    public Map<Long, Load> getLoadFromTrackingForTrackingId(@RequestBody List<Long> trackingIds) throws NoSuchAlgorithmException, InvalidKeyException {
        return trackingService.fetchLoadFromTrackingServiceByTrackingId(trackingIds);
    }
}
