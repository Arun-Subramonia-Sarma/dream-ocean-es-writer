package com.fourkites.ocean.es.writer.sao;

import com.fourkites.ocean.es.writer.annotation.TrackExecutionTime;
import com.fourkites.ocean.es.writer.config.properties.TrackingServiceProperties;
import com.fourkites.ocean.es.writer.model.Load;
import com.fourkites.ocean.es.writer.model.TrackingRequest;
import com.fourkites.ocean.es.writer.model.TrackingResponse;
import com.fourkites.ocean.es.writer.utils.ExternalClientUtils;
import com.fourkites.ocean.es.writer.utils.TimerUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrackingService {
    @NonNull
    private RestTemplate restTemplate;
    @NonNull
    private TrackingServiceProperties trackingServiceProperties;

    @TrackExecutionTime("Fetch load from tracking service")
    public Optional<Load> fetchLoadFromTrackingServiceByTrackingId(Long trackingId) throws NoSuchAlgorithmException, InvalidKeyException {
        Map<Long, Load> loads=fetchLoadFromTrackingServiceByTrackingId(List.of(trackingId));
        if(loads.size()==0)
            return Optional.empty();
        return Optional.of(loads.values().iterator().next());
    }
    @TrackExecutionTime("Fetch loads from tracking service")
    public Map<Long, Load> fetchLoadFromTrackingServiceByTrackingId(List<Long> trackingIds) throws NoSuchAlgorithmException, InvalidKeyException {
        String path=trackingServiceProperties.getTrackingPath()+"search";
        TrackingRequest trackingRequest=getDefaultTrackingRequest(trackingIds);
        HttpEntity<TrackingRequest> entity=new HttpEntity<>(trackingRequest);
        String signedUrl = ExternalClientUtils.getSharedSecretSignedUrl(trackingServiceProperties.getBaseUrl(),path,trackingServiceProperties.getClientId(), trackingServiceProperties.getTrackingSecret());
        log.debug("Calling the tracking url {}",signedUrl);
        TrackingResponse response = restTemplate.exchange(signedUrl, HttpMethod.POST, entity, TrackingResponse.class, (Object) null).getBody();
        log.debug("Load from tracking service: {}",response);
        return response.getLoads().stream().collect(Collectors.toMap(Load::getId, Function.identity(), (t1, t2)->t1));
    }

    private TrackingRequest getDefaultTrackingRequest(List<Long> trackingIds){

        return TrackingRequest.builder()
                .allTestLoads(false)
                .autoRefresh(false)
                .duration(365)
                .etaTime("appointment_time")
                .trackingIds(StringUtils.join(trackingIds,","))
                .page(1)
                .perPage(100)
                .show("tracking,recentMessages,lateReasonCodes,consignmentsCount,transitDays,driverDetail,carrierDeliveryEta,simultaneousTrackingReferenceNumber,latestEvent,stops")
                .sortBy("created_at")
                .sortOrder("desc")
                .timezoneOffset(19800L)
                .trackingDwellStatusOption("appointment_time")
                .build();
    }
}
