package com.fourkites.ocean.es.writer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document(indexName = "load")
public class Load {

    private String batchSequence;
    private String createdBy;
    private String deletedBy;
    private String loadNumber;
    private String originalScac;
    private String proNumber;
    private String priority;
    private List<String> referenceNumbers;
    private boolean deleted;
    private String deletedAt;
    private boolean demoLoad;
    private Customer customer;
    @NonNull
    @Id
    private long id;
    private long loadId;
    private int numberOfDeliveryStops;
    private List<Stop> stops;
    private List<Product> products;
    private boolean temperatureTrackingStarted;
    private String status;
    private String mappableStatus;
    private String deliveredAt;
    private long originStopId;
    private String origin;
    private String originAddress;
    private int originTimeZoneOffset;
    private String originTimeZoneName;
    private String originAppointmentTime;
    private String originEarliestAppointmentTime;
    private String originLatestAppointmentTime;
    private String originEarliestAppointmentTimeAm;
    private String originLatestAppointmentTimeAm;
    private long destinationStopId;
    private String destination;
    private String destinationAddress;
    private String destinationAppointmentTime;
    private String destinationEarliestAppointmentTime;
    private String destinationLatestAppointmentTime;
    private int destinationTimeZoneOffset;
    private String destinationTimeZoneName;
    private String destinationCalculatedAppointmentTime;
    private String destinationEarliestAppointmentTimeAm;
    private String destinationLatestAppointmentTimeAm;
    private long totalDistance;
    private String currentHos;
    private String currentElapsedRestTime;
    private String restingSince;
    private LastProcessedLocationDetails lastProcessedLocationDetails;
    private int progressPercentage;
    private boolean canUpdateStops;
    private boolean canDeleteLoad;
    private boolean canUpdateLoad;
    private boolean canUpdateTrackingInfo;
    private boolean canCompleteStops;
    private boolean missingTrackingInfoAlertSent;
    private boolean excludeFromAnalytics;
    private boolean stopTracking;
    private boolean relayLoad;
    private String forceStart;
    private String loadMode;
    private String actualLoadMode;
    private String onboardingStatus;
    private List<String> haulParameters;
    private String trackingStrategy;
    private boolean showMap;
    private String terminatedAt;
    private String billingType;
    private boolean railTrackingStarted;
    private boolean railTrackingEnded;
    private boolean railTrackingInProgress;
    private String inGateTime;
    private String railTrackingStartedAt;
    private String oceanTrackingStartedAt;
    private boolean oceanTrackingInProgress;
    private String latestHeadingAngle;
    private String trackingNumber;
    private String selfAssignPinNumber;
    private String selfAssignedBy;
    private Shipper shipper;
    private Carrier carrier;
    private String operatingCarrierScac;
    private Carrier managingCarrier;
    private Carrier operatingCarrier;
    @JsonProperty("3pl")
    private Carrier thirdParty;
    private boolean crossDockLoad;
    private List<String> modes;
    private String encryptedAccessToken;
    private int delayBufferInMinutes;
    private String sequencingLogic;
    private boolean mobileTracking;
    private boolean useMobileTracking;
    private String lastComputedCheckCall;
    private List<String> tags;
    private String truckNumber;
    private String trailerNumber;
    private String driverId;
    private String deviceId;
    private String railEquipmentInitials;
    private String railEquipmentNumber;
    private String vesselName;
    private String imo;
    private String mmsi;
    private String containerNumber;
    private String driverPhone;
    @JsonProperty("isLocationServiceTrackedLoad")
    private boolean isLocationServiceTrackedLoad;
    private String latestCheckCallAt;
    private String firstCheckCallAt;
    private String firstLocationPingAt;
    private int timeZoneOffset;
    private String timeZoneName;
    private String createdAt;
    private String updatedAt;
    private String pickupCheckCallAt;
    private String deliveryCheckCallAt;
    private long remainingDistance;
    private String latestStatusUpdateAt;
    private TrackingStatus trackingStatus;
    private boolean pickedUp;
    private String parentLoadNumber;
    private boolean canGetDeviceLocation;
    private boolean canSubscribeDevice;
    private RealTimeNotification realTimeNotification;
    private TruckRoutingParams truckRoutingParams;
    @JsonProperty("isLinkedLoad")
    private boolean isLinkedLoad;
    private boolean receivedLocationsForLoad;
    private List<String> shipperReferenceNumber;
    private boolean atRisk;
    private List<String> atRiskReasons;
    private List<AtRiskShortReasons> atRiskShortReasons;
    private long linkedLoadId;
    private String linkedLoadEtaEnabled;
    private DwellTimeStatus dwellTimeStatus;
    private long headrunProcessedId;
    private String startedReceivingCarrierFiles;
    private String pickupAppointmentRange;
    private String deliveryAppointmentRange;
    private EtaConfiguration etaConfiguration;
    private String consignmentImpactBy;
    private String consignmentImpactInMinutes;
    private String consignmentImpactWantTimeInMinutes;
    private String consignmentImpactLoadNumber;
    private String appointmentRange;
    @JsonProperty("isETAPushedDueToBusinessHours")
    private boolean isETAPushedDueToBusinessHours;
    private String liveDwellStartAt;
    private String estimatedDwellTime;
    private List<String> carrierReferenceNumbers;
    private String primaryReferenceNumber;
    private String serviceLevel;
    private String deliveryType;
    private int masterSequenceNumber;
    private String masterReferenceNumber;
    private String consignmentId;
    private String terminalName;
    private String trackingAssignmentId;
    private boolean showTrackingMethodForLTL;
    @JsonProperty("isSfaEnabled")
    private boolean isSfaEnabled;
    private String trainingShipperLoadCount;
    private String latestEvent;
    private String latestEventTime;
    private LocationMilestones locationMilestones;
    private boolean validContainer;
    private boolean otrCarrierEtaEnabled;
    @JsonProperty("isWeightsAndRatesEnabled")
    private boolean isWeightsAndRatesEnabled;
    private String showParams;
    private OceanInfo oceanInfo;
    @Data
    public static class Carrier {
        private String id;
        private String name;
        private boolean loadUpdatesEnabled;
        private String railTrackingMode;
        private int holdTimeInMinutes;
    }
    @Data
    public static class LocationMilestones{
        private String firstLocationAt;
        private String latestLocationAt;
        private String lastComputedLocationAt;
    }
    @Data
    public static class LastProcessedLocationDetails{
        private String latitude;
        private String provider;
        private String longitude;
        private String headingAngle;
        private String checkCallTime;
    }

    @Data
    public static class DwellTimeStatus{
        private int distanceTravelledInMetresDuringDwell;
        private String dwellTimeEnd;
        private String dwellTimeStart;
        private String geofenceStatus;
        private String lastCheckCallProcessedAt;
        private float latitude;
        private String location;
        private float longitude;
        private Long stopId;
        private String assetLastCheckCallProcessedId;
    }
    @Data
    public static class RealTimeNotification{
        private String channel;
    }
    @Data
    public static class TruckRoutingParams{
        private float height;
        private float length;
        private float limitedWeight;
        private String shippedHazardousGoods;
        private int trailersCount;
        private String truckType;
        private String tunnelCategory;
        private float weightPerAxle;
        private float width;
    }
    @Data
    public static class AtRiskShortReasons{
        private String reason;
        private String timestamp;
    }

}