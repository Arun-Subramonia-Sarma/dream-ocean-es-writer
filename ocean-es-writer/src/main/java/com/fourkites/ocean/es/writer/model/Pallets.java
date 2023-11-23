package com.fourkites.ocean.es.writer.model;

import lombok.Data;

import java.util.List;

@Data
public class Pallets {
    private String billOfLading;
    private String number;
    private String shipTo;
    private List<Parts> parts;

    @Data
    public static class Parts{
        private String customerPartNumber;
        private  String description;
        private String orderNumber;
        private String quantity;
        private String shipperPartNumber;
        private String weight;
    }
}
