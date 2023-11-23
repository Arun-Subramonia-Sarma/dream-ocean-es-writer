package com.fourkites.ocean.es.writer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude
public class TrackingStatus {
    private LatestLocation latestLocation;
    private DeliverySchedule deliverySchedule;
    private boolean autoEtaTriggered;
    @Data
    public static class LatestLocation{
        private String id;
        private String name;
        private String latitude;
        private String longitude;
        private String city;
        private String state;
        private String country;
        private String headingAngle;
        private String timestamp;
        private String locatedAt;
        private int timeZoneOffset;
        private String timeZoneName;
        private String mode;
        private String event;
        private String loadMode;
        private int lastCheckCallTimeFromNow;
        private AdditionalData additionalData;
        private String provider;
        private List<String> tags;
        private String trackingMethod;
    }
    @Data
    public static class AdditionalData{
        private float course;
        private String destination;
        private String eta;
        private float headingAngle;
        private float speed;
        private String shipName;
        private String timestamp;
        private Long imo;
        private Long mmsi;
    }
    @Data
    public static class DeliverySchedule{
        private Long timeUntilPlannedDeliveryInMinutes;
        private Long timeUntilPlannedDeliveryInMinutesForWantTime;
        private Long timeUntilActualDeliveryInMinutes;
        private Long timeUntilEstimatedDeliveryInMinutes;
        private String nextStopCarrierETA;
        private String nextStopETA;
        private String nextStopMLEtaRange;
        private String nextStopMLEtaConfidence;
        private boolean nextStopCarrierETADateOnly;
        private String nextDeliveryStopMode;
        private ETAStatusDetails nextStopETAStatusDetails;
        private String carrierEtaSource;
        private String etaSource;
        private String fkEtaLastUpdatedAt;
        private String timeZoneName;
        private Long timeZoneOffset;
    }
    @Data
    public static class ETAStatusDetails{
        private TimeValues wantTime;
        private TimeValues appointmentTime;
        private TimeValues appointmentTimeAM;
    }
    @Data
    public static class TimeValues{
        private Long diff;
        private String status;
    }
}