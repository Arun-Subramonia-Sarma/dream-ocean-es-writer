package com.fourkites.ocean.es.writer.utils;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Calendar;

@RequiredArgsConstructor
public class TimerUtils {
    @NonNull
    private Long startTime;
    private Long endTime;

    public static TimerUtils startTimer(){
        return new TimerUtils(Calendar.getInstance().getTimeInMillis());
    }


    public void endTimer(){
        endTime=Calendar.getInstance().getTimeInMillis();
    }

    public Long getExecutionTime(){
        if(endTime != null && startTime != null){
            return endTime-startTime;
        }
        return null;
    }
}
