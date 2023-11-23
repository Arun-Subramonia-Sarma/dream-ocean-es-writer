package com.fourkites.ocean.es.writer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageInfo {
    private int startIndex;
    private int endIndex;
    private int currentPageNumber;
    private int pageSize;
    private int pageCount;
}
