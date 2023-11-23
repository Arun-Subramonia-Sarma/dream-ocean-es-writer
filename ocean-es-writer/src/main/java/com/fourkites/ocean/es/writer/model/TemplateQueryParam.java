package com.fourkites.ocean.es.writer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemplateQueryParam {
    private String groupingColumnName;
    private String groupingColumnValue;

    private int loadStart;
    private int numberOfLoadsPerBucket;
    private String loadSortField;
    private String loadSortOrder;

    private String bucketSortField;
    private String bucketSortOrder;
    private int pageStart;
    private int pageSize;

    private boolean fetchLoads=true;

}
