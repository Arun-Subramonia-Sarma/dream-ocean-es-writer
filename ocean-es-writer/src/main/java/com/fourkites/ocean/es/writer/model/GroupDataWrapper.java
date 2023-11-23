package com.fourkites.ocean.es.writer.model;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class GroupDataWrapper {
    Map<String, List<Load>> groupedLoads;
    Map<String, List<Load>> unAssignedLoads;
    Boolean endOfPage=false;
    Boolean returnUnAssigned=false;
}
