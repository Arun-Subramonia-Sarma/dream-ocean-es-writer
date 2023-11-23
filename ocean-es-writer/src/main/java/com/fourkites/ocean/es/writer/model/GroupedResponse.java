package com.fourkites.ocean.es.writer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupedResponse {
    private String groupBy;
    private Map<String, List<Load>> groups;
    private Pagination pagination;

    public List<Load> addGroup(String groupKey){
        if(groups==null){
            groups=new LinkedHashMap<>();
        }
        List loads=groups.get(groupKey);
        if(loads==null){
            loads=new ArrayList<>();
            groups.put(groupKey,loads);
        }
        return loads;
    }
    public void addGroup(String groupKey, Load load){
        List loads=addGroup(groupKey);
        loads.add(load);
    }
}
