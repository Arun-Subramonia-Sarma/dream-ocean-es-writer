package com.fourkites.ocean.es.writer.model;

import lombok.Data;
import java.util.List;

@Data
public class Stop {
    private String appointmentTime;
    private String earliestAppointmentTime;
    private String latestAppointmentTime;
    private String wantTime;
    private String earliestWantTime;
    private String latestWantTime;
    private String calculatedAppointmentTime;
    private long stopId;
    private String estimatedArrivalMabdTimeUsingAppointmentTime;
    private String estimatedArrivalMabdTimeUsingWantTime;
    private String estimatedMabdTimeUsingAppointmentTime;
    private String estimatedMabdTimeUsingWantTime;
    private String estimatedRelayTimeUsingAppointmentTime;
    private String estimatedRelayTimeUsingWantTime;
    private String estimatedArrivalTime;
    private EtaAttributes etaAttributes;
    private long timeZoneOffset;
    private String timezoneFullName;
    private String timeZoneName;
    private String status;
    private String mappableStatus;
    private int durationInMinutes;
    private String distanceInMiles;
    private int previousStopDistance;
    private String loadingTime;
    private String drivingTime;
    private String restTime;
    private String travelBanWaitTime;
    private String holdTime;
    private int businessStartWaitTime;
    private String deletedAt;
    private String updatedAt;
    private String createdAt;
    private TrackingStatus.ETAStatusDetails etaStatusDetails;
    private String etaSource;
    private boolean recoveryLogicExecuted;
    private long addressId;
    private String createdBy;
    private String deletedBy;
    private long id;
    private int sequence;
    private String shipTo;
    private String stopType;
    private String trackingNumber;
    private long addressVersionId;
    private String stopReferenceId;
    private List<String> referenceNumbers;
    private List<Pallets> pallets;
    private Customer customer;
    private boolean deleted;
    private boolean timezoneProvidedByCustomer;
    private boolean timeInUtc;
    private boolean useLocalLatLng;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String county;
    private String district;
    private String latitude;
    private String longitude;
    private String state;
    private String postalCode;
    private String name;
    private int geofenceRadius;
    private List<Coordinates> geofencePoints;
    private String locationType;
    private boolean addressOutsidePolygonGeofence;
    private String externalAddressId;
    private String completeAddress;
    private int actualTimeElapsedBetweenArrivalAndDeparture;
    private GeofenceArrivalDeparture geofenceArrival;
    private GeofenceArrivalDeparture geofenceDeparture;
    private String notifyEtaOutsideBusinessHours;
    private String isAutoDeparted;
    private boolean isBusinessCloseDetected;
    private String minDistanceCheckCallAt;
    private int minDistance;
    private Coordinates minDistanceCoordinates;
    private int unloadTimeInMinutes;
    private int actualDwellTimeInMinutes;
    private String liveDwellStartAt;
    private String arrivalTime;
    private String departureTime;
    private boolean intermodalPlacement;
    private boolean completed;
    private boolean arrivalDetectedByFourKites;
    private boolean delayAlertSent;
    private boolean earlyAlertSent;
    private int defaultGeofenceRadius;
    private int geofenceRadiusToUse;
    private String odometerReadingAtArrival;
    private boolean rescheduled;
    private boolean canReschedule;
    private boolean behindSchedule;
    private boolean missedAppointment;
    private boolean onSchedule;
    private boolean aheadOfSchedule;
    private long linkedStopId;
    private String transportationMode;
    private boolean markedAsIncomplete;
    private boolean manuallyCompleted;
    private boolean manuallyArrived;
    private Extension extension;

    @Data
    public static class EtaAttributes{
        private String betaEta1;
        private String betaEta2;
        private String inferredRelay;
        private int medianMins;
        private int betaRestTimeInSeconds;
        private int betaWaitingTimeForBusinessStartInSeconds;
    }
    @Data
    public static class Coordinates{
        private double latitude;
        private double longitude;
    }
    @Data
    public static class Extension{
        private String carrierEta;
        private String carrierEtaSource;
        private boolean carrierEtaDateOnly;
        private String customerNumber;
        private Fketa fkEta;
        private String fkEtaLastUpdatedAt;
        private String fkEtaLastUpdatedSource;
        private String carrierEtaLastUpdatedAt;
    }
    @Data
    public static class GeofenceArrivalDeparture{
        private String checkCallAt;
    }
    @Data
    public static class Fketa{
        private String last_updated_fk_eta_source;
        private String last_updated_at;
        private String ccxl;
        private String here_eta;
        private Mleta ml_eta;
    }
    @Data
    public static class Mleta{
        private String eta;
        private String eta_earliest;
        private String eta_latest;
        private String eta_confidence;
    }
}