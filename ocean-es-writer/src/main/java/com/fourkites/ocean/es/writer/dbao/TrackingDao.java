package com.fourkites.ocean.es.writer.dbao;


import com.fourkites.ocean.es.writer.dbao.mapper.OceanInfoMapper;
import com.fourkites.ocean.es.writer.model.OceanInfo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TrackingDao {

    @NonNull
    private JdbcTemplate jdbcTemplate;

    public Optional<OceanInfo> fetchOceanInfoFromDB(Long trackingId){
        return fetchOceanInfoFromDB(trackingId, new OceanInfoMapper());
    }

    public Optional<OceanInfo> fetchOceanInfoFromDB(Long trackingId, OceanInfoMapper oceanInfoMapper){
        String sql=getTrackingQuery(trackingId);
        log.debug("Executing the query {}",sql);
        log.debug("Params: tracking id {}",trackingId);
        jdbcTemplate.query(sql, oceanInfoMapper.clear(),trackingId);
        Map<Long, OceanInfo> oceanInfoMap= oceanInfoMapper.getOceanInfoMap();
        if(oceanInfoMap.size()==0)
            return Optional.empty();
        return Optional.of(oceanInfoMap.values().iterator().next());
    }

    public Map<Long, OceanInfo> fetchOceanInfoFromDb(List<Long> trackingIds ){
        return fetchOceanInfoFromDb(trackingIds, new OceanInfoMapper());
    }
    public Map<Long, OceanInfo> fetchOceanInfoFromDb(List<Long> trackingIds, OceanInfoMapper oceanInfoMapper){
        String sql=getTrackingQuery(trackingIds);
        log.debug("Executing the query {}",sql);
        log.debug("Params: tracking id {}",trackingIds);
        jdbcTemplate.query(sql, oceanInfoMapper.clear(),trackingIds.toArray());
        return oceanInfoMapper.getOceanInfoMap();
    }
    private String getTrackingQuery(Long trackingId){
        return new StringBuilder()
                .append("SELECT ")
                .append("   t.*, ")
                .append("   eti.failure_code, ")
                .append("   ts.* ")
                .append("FROM ")
                .append("   tracking t ")
                .append("left outer join tracking_stops ts on ts.tracking_id=t.tracking_id ")
                .append("left outer join extended_tracking_info eti on t.tracking_id=eti.tracking_id ")
                .append("WHERE ")
                .append("   t.tracking_id = ? ")
                .toString();
    }

    private String getTrackingQuery( List<Long> trackingIds){
        String queryTemplate = new StringBuilder()
                .append("SELECT ")
                .append("   t.*, ")
                .append("   eti.failure_code, ")
                .append("   ts.* ")
                .append("FROM ")
                .append("   tracking t ")
                .append("left outer join tracking_stops ts on ts.tracking_id=t.tracking_id ")
                .append("left outer join extended_tracking_info eti on t.tracking_id=eti.tracking_id ")
                .append("WHERE ")
                .append("   t.tracking_id in (%s) ")
                .toString();
        String inSql = String.join(",", Collections.nCopies(trackingIds.size(), "?"));
        String query = String.format(queryTemplate, inSql);
        return query;
    }




}
