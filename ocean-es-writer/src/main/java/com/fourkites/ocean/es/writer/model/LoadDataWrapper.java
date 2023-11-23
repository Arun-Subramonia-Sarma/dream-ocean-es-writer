package com.fourkites.ocean.es.writer.model;

import lombok.Data;
import java.util.Map;

@Data
public class LoadDataWrapper {
    Pagination pagination;
    Map<Long, Load> loadsFromTrackingService;
    int startTrackingPage=1;
    int endTrackingPage=5;
}
