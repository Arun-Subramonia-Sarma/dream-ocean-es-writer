package com.fourkites.ocean.es.writer.model;

import lombok.Data;
import java.util.List;

@Data
public class Customer {
    private String originalCustomerId;
    private List<String> poNumbers;
    private Poc pointOfContact;
    @Data
    public static class Poc{
        private String email;
        private String name;
        private String phone;
    }
}