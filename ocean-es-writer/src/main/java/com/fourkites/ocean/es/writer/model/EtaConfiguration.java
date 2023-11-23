package com.fourkites.ocean.es.writer.model;

import lombok.Data;

@Data
public class EtaConfiguration {
    private Configuration configuration;
    @Data
    public static class Configuration{
        private EtaType tl;
        private EtaType ltl;
        private EtaType rail;
        private EtaType ocean;
        private EtaType parcel;
        private EtaType courier;
    }
    @Data
    public static class EtaType{
        private EtaValues late;
        private EtaValues early;
        private EtaValues onTime;
        private EtaValues veryLate;
    }
    @Data
    public static class EtaValues{
        private int low;
        private int high;
    }
}