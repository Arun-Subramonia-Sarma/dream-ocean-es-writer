package com.fourkites.ocean.es.writer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class StopInfo {
    @NonNull
    private Long stopId;
    private Long trackingId;
    private String terminalName;
    private String lastFreeDay;
    private String calculatedETA;
    private String carrierETA;
    private String estimatedDepartureTime;
    private String originalETD;
    private String etdSource;
    private String dwellTime;
    private String stopType;
    private String imo;
    private String mmsi;
    private String vesselName;
    private String carrierETD;
    private String carrierETASource;
    private String carrierETAUpdatedAt;
    private String geofenceArrivalTime;
    private String geofenceDepartureTime;
}
