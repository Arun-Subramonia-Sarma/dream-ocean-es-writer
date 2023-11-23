package com.fourkites.ocean.es.writer.model;

import lombok.Data;

import java.util.List;

@Data
public class TrackingResponse {
    private int statusCode;
    private List<Load> loads;
    private Pagination pagination;
}
