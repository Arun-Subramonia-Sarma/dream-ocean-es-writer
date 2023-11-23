package com.fourkites.ocean.es.writer.model.enums;

public enum GroupingColumn {
    BOOKING("oceanInfo.bookingNumber.keyword"),
    BOL("oceanInfo.billOfLading.keyword");

    private String groupingColumn;
    private GroupingColumn(String groupingColumn){
        this.groupingColumn=groupingColumn;
    }

    public String getGroupingColumn(){
        return groupingColumn;
    }

}
