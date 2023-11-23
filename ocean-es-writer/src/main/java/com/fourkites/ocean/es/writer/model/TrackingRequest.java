package com.fourkites.ocean.es.writer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourkites.ocean.es.writer.model.enums.Mode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(name="Tracking Request", description = "Parameters to send to the Tracking services",
        requiredProperties={"modes","sort_by","sort_order","duration"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackingRequest {
    @Schema(name="per_page", example="100", description="Page size to send to tracking")
    @JsonProperty("per_page")
    private int perPage;

    @Schema(name="page",  description="Page number to return from tracking. This property will be ignored when sent from UI to Ocean Service for grouping since multiple page request will be sent to the Tracking")
    private int page;

    @Schema(name="tracking_ids",  description="List of tracking ids. Generally set by the Ocean Service and mostly ignored by the UI")
    @JsonProperty("tracking_ids")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trackingIds;

    @Schema(name="load_ids",  description="List of loads ids. This field is used when the Search field is selected on the UI")
    @JsonProperty("load_ids")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String loadIds;

    @Schema(name="modes",  description="Defaults to Ocean. Should not be override")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Mode modes;

    @Schema(name="sort_by",  example="created_at", required=true)
    @JsonProperty("sort_by")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sortBy;

    @Schema(name="sort_order",  example="desc")
    @JsonProperty("sort_order")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sortOrder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("auto_refresh")
    private Boolean autoRefresh;

    @Schema(name="duration",  example="31")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer duration;

    @Schema(name="eta_time",  example="appointment_time")
    @JsonProperty("eta_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String etaTime;


    @JsonProperty("load_status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String loadStatus;

    @Schema(name="show",  example="recentMessages,lateReasonCodes,consignmentsCount,transitDays,driverDetail,carrierDeliveryEta,simultaneousTrackingReferenceNumber,latestEvent")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String show;

    @Schema(name="tracking_dwell_status_option",  example="appointment_time")
    @JsonProperty("tracking_dwell_status_option")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trackingDwellStatusOption;

    @JsonProperty("timezone_offset")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long timezoneOffset;

    @JsonProperty("pickup_at_risk")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickupAtRisk;

    @JsonProperty("delivery_at_risk")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deliveryAtRisk;

    @JsonProperty("tracking_info_unassigned")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trackingInfoUnassigned;


    @JsonProperty("tracking_status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trackingStatus;

    @JsonProperty("delivery_stops_count")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer deliveryStopsCount;

    @JsonProperty("tracking_quality")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trackingQuality;

    @JsonProperty("tracking_failure_reason_codes")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trackingFailureReasonCodes;

    @JsonProperty("temperature_tracking_started")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean temperatureTrackingStarted;

    @JsonProperty("tags_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tagsCondition;

    @JsonProperty("tags")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tags;

    @JsonProperty("stop_loading_types")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stopLoadingTypes;

    @JsonProperty("split_shipments_enabled")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String splitShipmentsEnabled;

    @JsonProperty("stop_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stopCondition;

    @JsonProperty("skip_test_loads")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean skipTestLoads;

    @JsonProperty("shipper_part_numbers")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean shipperPartNumbers;

    @JsonProperty("shipper_part_numbers_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shipperPartNumbersCondition;

    @JsonProperty("shipper_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shipperCondition;

    @JsonProperty("ship_tos_delivery_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shipTosDeliveryCondition;

    @JsonProperty("ship_froms_pickup_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shipFromsPickupCondition;

    @JsonProperty("requires_attention_time_period")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requiresAttentionTimePeriod;

    @JsonProperty("at_risk_reasons")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String atRiskReasons;

    @JsonProperty("at_risk_duration")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String atRiskDuration;

    @JsonProperty("at_risk_time_period")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String atRiskTimePeriod;

    @JsonProperty("at_risk")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String atRisk;

    @JsonProperty("all_test_loads")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean allTestLoads;

    @JsonProperty("carrier")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String carrier;

    @JsonProperty("carrier_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String carrierCondition;

    @JsonProperty("container_numbers")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String containerNumbers;

    @JsonProperty("vessel_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String vesselNames;

    @JsonProperty("vessel_numbers")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String vesselNumbers;

    @JsonProperty("container_validation")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String containerValidation;

    @JsonProperty("customer_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerCondition;

    @JsonProperty("customer_part_numbers")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerPartNumbers;

    @JsonProperty("customer_part_numbers_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerPartNumbersCondition;

    @JsonProperty("scac")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String scac;

    @JsonProperty("scac_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String scacCondition;

    @JsonProperty("dashboard_filter")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dashboardFilter;

    @JsonProperty("delivery_stops_count_max")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deliveryStopsCountMax;

    @JsonProperty("delivery_stops_count_min")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deliveryStopsCountMin;

    @JsonProperty("delivery_stops_operator")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deliveryStopsOperator;

    @JsonProperty("ecta_status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ectaStatus;

    @JsonProperty("ecta_status_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ectaStatusCondition;

    @JsonProperty("inferred_tags")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String inferredTags;

    @JsonProperty("ocean_exception_days")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String oceanExceptionDays;

    @JsonProperty("ocean_exceptions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String oceanExceptions;

    @JsonProperty("priority")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String priority;

    @JsonProperty("remaining_miles")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String remainingMiles;

    @JsonProperty("stale_location_threshold")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String staleLocationThreshold;

    @Schema(name="pickup_date_type", example="lessThanRange")
    @JsonProperty("pickup_date_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickupDateType;

    @Schema(name="delivery_date_type", example="lessThanRange")
    @JsonProperty("delivery_date_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deliveryDateType;

    @Schema(name="created_date_type", example="lessThanRange")
    @JsonProperty("created_date_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createdDateType;

    @JsonProperty("pickup_address_ids")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickupAddressIds;

    @JsonProperty("pickup_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickupCondition;

    @JsonProperty("pickup_country_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickupCountryNames;

    @JsonProperty("pickup_country_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickupCountryNameCondition;

    @JsonProperty("pickup_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickupEndDate;

    @JsonProperty("pickup_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickupStartDate;

    @JsonProperty("pickup_stop_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickupStopNameCondition;

    @JsonProperty("pickup_stop_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickupStopNames;

    @JsonProperty("port_of_discharge")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischarge;

    @JsonProperty("port_of_discharge_cities")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeCities;

    @JsonProperty("port_of_discharge_regions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeRegions;

    @JsonProperty("port_of_discharge_zip")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeZip;

    @JsonProperty("port_of_discharge_address_ids")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeAddressIds;

    @JsonProperty("port_of_discharge_country_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeCountryNames;

    @JsonProperty("port_of_discharge_country_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeCountryNameCondition;

    @JsonProperty("port_of_discharge_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeCondition;

    @JsonProperty("port_of_discharge_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeEndDate;

    @JsonProperty("port_of_discharge_end_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeEndTime;

    @JsonProperty("port_of_discharge_arrival_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeArrivalStartDate;

    @JsonProperty("port_of_discharge_arrival_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeArrivalEndDate;

    @JsonProperty("port_of_discharge_eta_date_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeEtaDateType;

    @JsonProperty("port_of_discharge_eta_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeEtaStartDate;

    @JsonProperty("port_of_discharge_eta_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeEtaEndDate;

    @JsonProperty("port_of_discharge_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeStartDate;

    @JsonProperty("port_of_discharge_stops_count")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeStopsCount;

    @JsonProperty("port_of_discharge_start_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeStartTime;

    @JsonProperty("port_of_discharge_stop_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeStopNameCondition;

    @JsonProperty("port_of_discharge_stop_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfDischargeStopNames;

    @JsonProperty("port_of_loading")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoading;

    @JsonProperty("port_of_loading_cities")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingCities;

    @JsonProperty("port_of_loading_regions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingRegions;

    @JsonProperty("port_of_loading_zip")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingZip;

    @JsonProperty("port_of_loading_address_ids")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingAddressIds;

    @JsonProperty("port_of_loading_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingCondition;

    @JsonProperty("port_of_loading_country_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingCountryNames;

    @JsonProperty("port_of_loading_country_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingCountryNameCondition;

    @JsonProperty("port_of_loading_departure_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingDepartureStartDate;

    @JsonProperty("port_of_loading_departure_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingDepartureEndDate;

    @JsonProperty("port_of_loading_etd_date_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingEtdDateType;

    @JsonProperty("port_of_loading_etd_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingEtdStartDate;

    @JsonProperty("port_of_loading_etd_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingEtdEndDate;

    @JsonProperty("port_of_loading_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingEndDate;

    @JsonProperty("port_of_loading_end_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingEndTime;

    @JsonProperty("port_of_loading_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingStartDate;

    @JsonProperty("port_of_loading_stops_count")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingStopsCount;

    @JsonProperty("port_of_loading_start_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingStartTime;

    @JsonProperty("port_of_loading_stop_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingStopNameCondition;

    @JsonProperty("port_of_loading_stop_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portOfLoadingStopNames;

    @JsonProperty("port")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String port;

    @JsonProperty("port_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portCondition;

    @JsonProperty("port_unlocode")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portUnlocode;

    @JsonProperty("port_unlocode_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String portUnlocodeCondition;

    @JsonProperty("rail_exceptions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String railExceptions;

    @JsonProperty("with_messages")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String withMessages;

    @JsonProperty("without_messages")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String withoutMessages;

    @JsonProperty("with_files")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String withFiles;

    @JsonProperty("shared_with")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sharedWith;

    @JsonProperty("onboarding_status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String onboardingStatus;

    @JsonProperty("driver_phones")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String driverPhones;

    @JsonProperty("trailer_numbers")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String trailerNumbers;

    @JsonProperty("truck_numbers")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String truckNumbers;

    @JsonProperty("device_ids")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deviceIds;

    @JsonProperty("rail_equipments")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String railEquipments;

    @JsonProperty("wagon_numbers")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String wagonNumbers;

    @JsonProperty("upcoming_vessel")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String upcomingVessel;

    @JsonProperty("flight_number")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String flightNumber;

    @JsonProperty("usage_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String usageEndDate;

    @JsonProperty("usage_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String usageStartDate;

    @JsonProperty("origin")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String origin;

    @JsonProperty("origin_stops")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originStops;

    @JsonProperty("original_customer_ids")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originalCustomerIds;

    @JsonProperty("origin_airport")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirport;

    @JsonProperty("origin_airport_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportCondition;

    @JsonProperty("origin_airport_country_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportCountryNames;

    @JsonProperty("origin_airport_country_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportCountryNameCondition;

    @JsonProperty("origin_airport_cities")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportCities;

    @JsonProperty("origin_airport_regions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportRegions;

    @JsonProperty("origin_airport_stops")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportStops;

    @JsonProperty("origin_airport_zip")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportZip;

    @JsonProperty("origin_airport_stop_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportStopNameCondition;

    @JsonProperty("origin_airport_stop_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportStopNames;

    @JsonProperty("origin_airport_date_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportDateType;

    @JsonProperty("origin_airport_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportEndDate;

    @JsonProperty("origin_airport_end_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportEndTime;

    @JsonProperty("origin_airport_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportStartDate;

    @JsonProperty("origin_airport_stops_count")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportStopsCount;

    @JsonProperty("origin_airport_start_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String originAirportStartTime;

    @JsonProperty("hub_airport")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirport;

    @JsonProperty("hub_airport_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportCondition;

    @JsonProperty("hub_airport_country_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportCountryNames;

    @JsonProperty("hub_airport_country_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportCountryNameCondition;

    @JsonProperty("hub_airport_cities")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportCities;

    @JsonProperty("hub_airport_regions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportRegions;

    @JsonProperty("hub_airport_stops")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportStops;

    @JsonProperty("hub_airport_zip")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportZip;

    @JsonProperty("hub_airport_stop_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportStopNameCondition;

    @JsonProperty("hub_airport_stop_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportStopNames;

    @JsonProperty("hub_airport_date_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportDateType;

    @JsonProperty("hub_airport_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportEndDate;

    @JsonProperty("hub_airport_end_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportEndTime;

    @JsonProperty("hub_airport_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportStartDate;

    @JsonProperty("hub_airport_stops_count")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportStopsCount;

    @JsonProperty("hub_airport_start_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hubAirportStartTime;

    @JsonProperty("destination_airport")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirport;

    @JsonProperty("destination_airport_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportCondition;

    @JsonProperty("destination_airport_country_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportCountryNames;

    @JsonProperty("destination_airport_country_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportCountryNameCondition;

    @JsonProperty("destination_airport_cities")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportCities;

    @JsonProperty("destination_airport_regions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportRegions;

    @JsonProperty("destination_airport_stops")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportStops;

    @JsonProperty("destination_airport_zip")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportZip;

    @JsonProperty("destination_airport_stop_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportStopNameCondition;

    @JsonProperty("destination_airport_stop_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportStopNames;

    @JsonProperty("destination_airport_date_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportDateType;

    @JsonProperty("destination_airport_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportEndDate;

    @JsonProperty("destination_airport_end_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportEndTime;

    @JsonProperty("destination_airport_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportStartDate;

    @JsonProperty("destination_airport_stops_count")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportStopsCount;

    @JsonProperty("destination_airport_start_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String destinationAirportStartTime;

    @JsonProperty("transshipment")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipment;

    @JsonProperty("transshipment_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentCondition;

    @JsonProperty("transshipment_country_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentCountryNames;

    @JsonProperty("transshipment_country_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentCountryNameCondition;

    @JsonProperty("transshipment_cities")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentCities;

    @JsonProperty("transshipment_regions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentRegions;

    @JsonProperty("transshipment_stops")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentStops;

    @JsonProperty("transshipment_zip")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentZip;

    @JsonProperty("transshipment_stop_name_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentStopNameCondition;

    @JsonProperty("transshipment_stop_names")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentStopNames;

    @JsonProperty("transshipment_date_type")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentDateType;

    @JsonProperty("transshipment_end_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentEndDate;

    @JsonProperty("transshipment_end_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentEndTime;

    @JsonProperty("transshipment_start_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentStartDate;

    @JsonProperty("transshipment_stops_count")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentStopsCount;

    @JsonProperty("transshipment_start_time")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String transshipmentStartTime;

    @JsonProperty("customer")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customer;

    @JsonProperty("shipper")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shipper;

    @JsonProperty("all_events")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String allEvents;

    @JsonProperty("latest_events")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String latestEvents;

    @JsonProperty("redesign_all_events")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String redesignAllEvents;

    @JsonProperty("redesign_latest_events")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String redesignLatestEvents;

    @JsonProperty("all_events_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String allEventsCondition;

    @JsonProperty("latest_events_condition")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String latestEventsCondition;

    @JsonProperty("ocean_events_condition_any_not")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String oceanEventsConditionAnyNot;

    @JsonIgnore
    public TrackingRequest getSimilar(){
        return new TrackingRequest(
                this.perPage,
                this.page,
                this.trackingIds,
                this.loadIds,
                this.modes,
                this.sortBy,
                this.sortOrder,
                this.autoRefresh,
                this.duration,
                this.etaTime,
                this.loadStatus,
                this.show,
                this.trackingDwellStatusOption,
                this.timezoneOffset,
                this.pickupAtRisk,
                this.deliveryAtRisk,
                this.trackingInfoUnassigned,
                this.trackingStatus,
                this.deliveryStopsCount,
                this.trackingQuality,
                this.trackingFailureReasonCodes,
                this.temperatureTrackingStarted,
                this.tagsCondition,
                this.tags,
                this.stopLoadingTypes,
                this.splitShipmentsEnabled,
                this.stopCondition,
                this.skipTestLoads,
                this.shipperPartNumbers,
                this.shipperPartNumbersCondition,
                this.shipperCondition,
                this.shipTosDeliveryCondition,
                this.shipFromsPickupCondition,
                this.requiresAttentionTimePeriod,
                this.atRiskReasons,
                this.atRiskDuration,
                this.atRiskTimePeriod,
                this.atRisk,
                this.allTestLoads,
                this.carrier,
                this.carrierCondition,
                this.containerNumbers,
                this.vesselNames,
                this.vesselNumbers,
                this.containerValidation,
                this.customerCondition,
                this.customerPartNumbers,
                this.customerPartNumbersCondition,
                this.scac,
                this.scacCondition,
                this.dashboardFilter,
                this.deliveryStopsCountMax,
                this.deliveryStopsCountMin,
                this.deliveryStopsOperator,
                this.ectaStatus,
                this.ectaStatusCondition,
                this.inferredTags,
                this.oceanExceptionDays,
                this.oceanExceptions,
                this.priority,
                this.remainingMiles,
                this.staleLocationThreshold,
                this.pickupDateType,
                this.deliveryDateType,
                this.createdDateType,
                this.pickupAddressIds,
                this.pickupCondition,
                this.pickupCountryNames,
                this.pickupCountryNameCondition,
                this.pickupEndDate,
                this.pickupStartDate,
                this.pickupStopNameCondition,
                this.pickupStopNames,
                this.portOfDischarge,
                this.portOfDischargeCities,
                this.portOfDischargeRegions,
                this.portOfDischargeZip,
                this.portOfDischargeAddressIds,
                this.portOfDischargeCountryNames,
                this.portOfDischargeCountryNameCondition,
                this.portOfDischargeCondition,
                this.portOfDischargeEndDate,
                this.portOfDischargeEndTime,
                this.portOfDischargeArrivalStartDate,
                this.portOfDischargeArrivalEndDate,
                this.portOfDischargeEtaDateType,
                this.portOfDischargeEtaStartDate,
                this.portOfDischargeEtaEndDate,
                this.portOfDischargeStartDate,
                this.portOfDischargeStopsCount,
                this.portOfDischargeStartTime,
                this.portOfDischargeStopNameCondition,
                this.portOfDischargeStopNames,
                this.portOfLoading,
                this.portOfLoadingCities,
                this.portOfLoadingRegions,
                this.portOfLoadingZip,
                this.portOfLoadingAddressIds,
                this.portOfLoadingCondition,
                this.portOfLoadingCountryNames,
                this.portOfLoadingCountryNameCondition,
                this.portOfLoadingDepartureStartDate,
                this.portOfLoadingDepartureEndDate,
                this.portOfLoadingEtdDateType,
                this.portOfLoadingEtdStartDate,
                this.portOfLoadingEtdEndDate,
                this.portOfLoadingEndDate,
                this.portOfLoadingEndTime,
                this.portOfLoadingStartDate,
                this.portOfLoadingStopsCount,
                this.portOfLoadingStartTime,
                this.portOfLoadingStopNameCondition,
                this.portOfLoadingStopNames,
                this.port,
                this.portCondition,
                this.portUnlocode,
                this.portUnlocodeCondition,
                this.railExceptions,
                this.withMessages,
                this.withoutMessages,
                this.withFiles,
                this.sharedWith,
                this.onboardingStatus,
                this.driverPhones,
                this.trailerNumbers,
                this.truckNumbers,
                this.deviceIds,
                this.railEquipments,
                this.wagonNumbers,
                this.upcomingVessel,
                this.flightNumber,
                this.usageEndDate,
                this.usageStartDate,
                this.origin,
                this.originStops,
                this.originalCustomerIds,
                this.originAirport,
                this.originAirportCondition,
                this.originAirportCountryNames,
                this.originAirportCountryNameCondition,
                this.originAirportCities,
                this.originAirportRegions,
                this.originAirportStops,
                this.originAirportZip,
                this.originAirportStopNameCondition,
                this.originAirportStopNames,
                this.originAirportDateType,
                this.originAirportEndDate,
                this.originAirportEndTime,
                this.originAirportStartDate,
                this.originAirportStopsCount,
                this.originAirportStartTime,
                this.hubAirport,
                this.hubAirportCondition,
                this.hubAirportCountryNames,
                this.hubAirportCountryNameCondition,
                this.hubAirportCities,
                this.hubAirportRegions,
                this.hubAirportStops,
                this.hubAirportZip,
                this.hubAirportStopNameCondition,
                this.hubAirportStopNames,
                this.hubAirportDateType,
                this.hubAirportEndDate,
                this.hubAirportEndTime,
                this.hubAirportStartDate,
                this.hubAirportStopsCount,
                this.hubAirportStartTime,
                this.destinationAirport,
                this.destinationAirportCondition,
                this.destinationAirportCountryNames,
                this.destinationAirportCountryNameCondition,
                this.destinationAirportCities,
                this.destinationAirportRegions,
                this.destinationAirportStops,
                this.destinationAirportZip,
                this.destinationAirportStopNameCondition,
                this.destinationAirportStopNames,
                this.destinationAirportDateType,
                this.destinationAirportEndDate,
                this.destinationAirportEndTime,
                this.destinationAirportStartDate,
                this.destinationAirportStopsCount,
                this.destinationAirportStartTime,
                this.transshipment,
                this.transshipmentCondition,
                this.transshipmentCountryNames,
                this.transshipmentCountryNameCondition,
                this.transshipmentCities,
                this.transshipmentRegions,
                this.transshipmentStops,
                this.transshipmentZip,
                this.transshipmentStopNameCondition,
                this.transshipmentStopNames,
                this.transshipmentDateType,
                this.transshipmentEndDate,
                this.transshipmentEndTime,
                this.transshipmentStartDate,
                this.transshipmentStopsCount,
                this.transshipmentStartTime,
                this.customer,
                this.shipper,
                this.allEvents,
                this.latestEvents,
                this.redesignAllEvents,
                this.redesignLatestEvents,
                this.allEventsCondition,
                this.latestEventsCondition,
                this.oceanEventsConditionAnyNot
        );
    }

    public String toString(){
        ObjectMapper mapper=new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
