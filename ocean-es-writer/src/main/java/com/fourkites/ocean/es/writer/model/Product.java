package com.fourkites.ocean.es.writer.model;

import lombok.Data;

@Data
public class Product {
    private String name;
    private long productId;
    private String externalId;
    private int setTemperature;
    private int timeToleranceInMinutes;
    private String status;
    private int lowerTemperatureThreshold;
    private int upperTemperatureThreshold;
    private String temperatureUnit;
}
