package com.fourkites.ocean.es.writer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class OceanInfo {
    @NonNull
    private long trackingId;
    private String bookingNumber;
    @JsonProperty("bookingNumberKeyword.keyword")
    private String bookingNumberKeyword;
    private String billOfLading;
    @JsonProperty("billOfLading.keyword")
    private String billOfLadingKeyword;
    private String containerNumber;
    private String customStatus;
    private String exitMode;
    private String lastVessel;
    private String currentVoyage;
    private String containerType;
    private String contractualTransitTime;
    private String containerValidity;
    private String detentionLastFreeDay;
    private boolean usdaHold;
    private boolean availableForDelivery;
    private String polDetention;
    private String polDemurrage;
    private String podDetention;
    private String podDemurrage;
    private String detentionFirstFreeDay;
    private String demurrageFirstFreeDay;
    private String demurrageLastFreeDay;
    private String curatedContainerType;
    private String fkEtaPolLoading;
    private String cargoReadyDate;
    private String polStorageCost;
    private String podStorageCost;
    private String polDemurrageChargeableDays;
    private String podDemurrageChargeableDays;
    private String polDetentionChargeableDays;
    private String podDetentionChargeableDays;
    private String polStorageChargeableDays;
    private String podStorageChargeableDays;
    @JsonRawValue
    @JsonIgnore
    private String additionalData;
    private String preCarriageTransitTime;
    private String transitTime;
    private String onCarriageTransitTime;
    private String overAllTransitTime;
    private String adjustedTransitTime;
    private String shippingFrequency;
    private String carrierName;
    private String upcomingVessel;
    private List<String> failureCode;
    private String operatingCarrierScac;
    private List<StopInfo> stops;

    public OceanInfo addStopInfo(StopInfo stopInfo){
        if(stopInfo==null)
            return this;
        if(stops==null){
            stops=new ArrayList<>();
        }
        stops.add(stopInfo);
        return this;

    }
}
