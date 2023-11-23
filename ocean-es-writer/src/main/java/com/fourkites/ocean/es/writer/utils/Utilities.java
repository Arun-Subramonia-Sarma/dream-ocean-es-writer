package com.fourkites.ocean.es.writer.utils;

import com.fourkites.ocean.es.writer.model.PageInfo;

public class Utilities {

    public static PageInfo getPageInfo(int pageNumber, int pageSize, int totalDataCount){
        int pageCount=totalDataCount/pageSize;
        if(totalDataCount%pageSize!=0)
            pageCount++;
        int startIndex=pageSize*(pageNumber-1);
        int endIndex=startIndex+pageSize-1;
        if(endIndex>totalDataCount-1)
            endIndex=totalDataCount-1;
        return PageInfo.builder()
                        .pageCount(pageCount)
                        .currentPageNumber(pageNumber)
                        .pageSize(pageSize)
                        .startIndex(startIndex)
                        .endIndex(endIndex)
                        .build();
    }
}
