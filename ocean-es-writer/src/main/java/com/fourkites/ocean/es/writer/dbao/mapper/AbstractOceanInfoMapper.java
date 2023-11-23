package com.fourkites.ocean.es.writer.dbao.mapper;

import com.fourkites.ocean.es.writer.model.OceanInfo;
import com.fourkites.ocean.es.writer.model.StopInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractOceanInfoMapper implements RowMapper<OceanInfo> {
    public OceanInfo mapOceanInfoFromRS(ResultSet rs, int rowNum ) throws SQLException {
        String billOfLading=rs.getString("bill_of_lading");
        if(billOfLading==null)
            billOfLading="UnAssigned";
        String bookingNumber=rs.getString("booking_number");
        if(bookingNumber==null)
            bookingNumber="UnAssigned";
        OceanInfo oceanInfo=  OceanInfo.builder()
                .trackingId(rs.getLong("tracking_id"))
                .bookingNumber(bookingNumber)
                .bookingNumberKeyword(bookingNumber)
                .billOfLading(billOfLading)
                .billOfLadingKeyword(billOfLading)
                .containerNumber(rs.getString("container_number"))
                .customStatus(rs.getString("custom_status"))
                .exitMode(rs.getString("exit_mode"))
                .lastVessel(rs.getString("last_vessel"))
                .currentVoyage(rs.getString("current_voyage"))

                .containerType(rs.getString("container_type"))
                .contractualTransitTime(rs.getString("contractual_transit_time"))
                .containerValidity(rs.getString("container_validity"))
                .detentionFirstFreeDay(rs.getString("detention_first_free_day"))
                .detentionLastFreeDay(rs.getString("detention_last_free_day"))
                .usdaHold(rs.getBoolean("usda_hold"))
                .availableForDelivery(rs.getBoolean("available_for_delivery"))
                .polDetention(rs.getString("pol_detention"))
                .polDemurrage(rs.getString("pol_demurrage"))
                .podDetention(rs.getString("pod_detention"))
                .podDemurrage(rs.getString("pod_demurrage"))
                .detentionFirstFreeDay(rs.getString("detention_first_free_day"))
                .demurrageFirstFreeDay(rs.getString("demurrage_last_free_day"))
                .curatedContainerType(rs.getString("curated_container_type"))
                .fkEtaPolLoading(rs.getString("fk_eta_pol_loading"))
                .cargoReadyDate(rs.getString("cargo_ready_date"))
                .polStorageCost(rs.getString("pol_storage_cost"))
                .podStorageCost(rs.getString("pod_storage_cost"))
                .polDemurrageChargeableDays(rs.getString("pol_demurrage_chargeable_days"))
                .podDemurrageChargeableDays(rs.getString("pod_demurrage_chargeable_days"))
                .polDetentionChargeableDays(rs.getString("pol_detention_chargeable_days"))
                .podDetentionChargeableDays(rs.getString("pod_detention_chargeable_days"))
                .polStorageChargeableDays(rs.getString("pol_storage_chargeable_days"))
                .podStorageChargeableDays(rs.getString("pod_storage_chargeable_days"))
                .additionalData(rs.getString("additional_data"))
                .preCarriageTransitTime(rs.getString("pre_carriage_transit_time"))
                .transitTime(rs.getString("transit_time"))
                .onCarriageTransitTime(rs.getString("on_carriage_transit_time"))
                .overAllTransitTime(rs.getString("over_all_transit_time"))
                .adjustedTransitTime(rs.getString("adjusted_transit_time"))
                .shippingFrequency(rs.getString("shipping_frequency"))
                .carrierName(rs.getString("carrier_name"))
                .upcomingVessel(rs.getString("upcoming_vessel"))
                .failureCode(mapFailureCode(rs.getString("failure_code")))
                .operatingCarrierScac(rs.getString("operating_carrier_scac"))
                //.stop(mapStopInfoFromRS(rs,rowNum))
//                .adjustedTransitTime(rs.getString(""))
                .build();
                //.addStopInfo(mapStopInfoFromRS(rs,rowNum));
        return oceanInfo;
    }

    public StopInfo mapStopInfoFromRS(ResultSet rs, int rowNum) throws SQLException {
        Long stopId=rs.getLong("stop_id");
        if(stopId==null || stopId == 0)
            return null;
        return StopInfo.builder()
                .stopId(rs.getLong("stop_id"))
                .trackingId(rs.getLong("tracking_id"))
                .terminalName(rs.getString("terminal_name"))
                .lastFreeDay(rs.getString("last_free_day"))
                .calculatedETA(rs.getString("calculated_eta"))
                .carrierETA(rs.getString("carrier_eta"))
                .estimatedDepartureTime(rs.getString("estimated_departure_time"))
                .originalETD(rs.getString("original_etd"))
                .etdSource(rs.getString("etd_source"))
                .dwellTime(rs.getString("dwell_time"))
                .stopType(rs.getString("stop_type"))
                .imo(rs.getString("imo"))
                .mmsi(rs.getString("mmsi"))
                .vesselName(rs.getString("vessel_name"))
                .carrierETD(rs.getString("carrier_etd"))
                .carrierETASource(rs.getString("carrier_eta_source"))
                .carrierETAUpdatedAt(rs.getString("carrier_eta_updated_at"))
                .geofenceArrivalTime(rs.getString("geofence_arrival_time"))
                .geofenceDepartureTime(rs.getString("geofence_departure_time"))
                .build();
    }

    public List<String> mapFailureCode(String failureCode){
        List<String> result = new ArrayList<>();
        if (failureCode != null && !failureCode.equals("{}"))
            result = Arrays.asList(failureCode.replaceAll("([{}])","").split(","));
        return result;
    }
}
