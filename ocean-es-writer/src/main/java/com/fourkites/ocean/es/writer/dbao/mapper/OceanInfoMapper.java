package com.fourkites.ocean.es.writer.dbao.mapper;

import com.fourkites.ocean.es.writer.model.OceanInfo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class OceanInfoMapper extends AbstractOceanInfoMapper {
    Map<Long, OceanInfo> oceanInfoMap=new HashMap<>();

    public OceanInfoMapper clear(){
        oceanInfoMap=new HashMap<>();
        return this;
    }
    @Override
    public OceanInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long trackingId=rs.getLong("tracking_id");
        OceanInfo oceanInfo=oceanInfoMap.get(trackingId);
        if(oceanInfo==null){
            oceanInfo = mapOceanInfoFromRS(rs,rowNum);
            oceanInfoMap.put(trackingId,oceanInfo);
        }
        oceanInfo.addStopInfo(mapStopInfoFromRS(rs,rowNum));
        return oceanInfo;
    }
}
