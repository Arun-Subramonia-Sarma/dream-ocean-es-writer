package com.fourkites.ocean.es.writer.model;

import lombok.Data;

@Data
public class Shipper {
    private String id;
    private String name;
    private SubdomainConfigurations subdomainConfigurations;
    private boolean loadUpdatesEnabled;
    private String stopLevelView;
    private String showLatestLocationInStopLevel;
    private UserNotificationConfiguration userNotificationConfiguration;
    private LtlConfigurations ltlConfigurations;
    private boolean sfaEnabled;
    private ParentCompany parentCompany;

    @Data
    public static class SubdomainConfigurations{
        private int id;
        private String companyId;
        private String permalink;
        private boolean ssoEnabled;
        private String ssoProtocol;
        private  boolean whiteLabelingEnabled;
        private String theme;
        private String logoUrl;
        private String logoUrlKey;
        private boolean active;
        private SsoConfiguration ssoConfiguration;
    }
    @Data
    public static class SsoConfiguration{
        private String emailAttribute;
        private String firstNameAttribute;
        private String lastNameAttribute;
        private String idpEntityId;
        private String idpSsoUrl;
        private String idpSloUrl;
        private String idpCertificateKey;
        private String userGroupAttribute;
    }
    @Data
    public static class UserNotificationConfiguration{
        private int pickupThreshold;
        private int assignmentThreshold;
        private int simpleEtaThreshold;
        private int dynamicEtaThreshold;
        private int ltlDynamicEtaThreshold;
        private int ltlEtaToDeliveryThreshold;
    }
    @Data
    public static class LtlConfigurations{
        private boolean mlEtaEnabled;
        private boolean showDateOnlyEta;
        private String elcReportMailIds;
        private String endOfDayTimeForLTLETA;
        private int expiryDaysForLtlLoads;
        private boolean ectaStatusUpdateEnabled;
        private String endOfDayTimeForParcelETA;
        private int expiryDaysForParcelLoads;
        private boolean enabledUpdateAfterDelivery;
        private boolean splitShipmentsEnabledForCarrier;
        private boolean splitShipmentsEnabledForShipper;
        private boolean ltlAutoDeliveryAppointmentCalculationEnabled;
    }
    @Data
    public static class ParentCompany{
        private String id;
        private String description;
    }
}