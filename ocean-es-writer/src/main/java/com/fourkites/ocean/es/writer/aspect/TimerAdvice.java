package com.fourkites.ocean.es.writer.aspect;

import com.fourkites.ocean.es.writer.annotation.TrackExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.logging.Logger;

@Aspect
@Component
@Slf4j
public class TimerAdvice {

    @Around("@annotation(com.fourkites.ocean.es.writer.annotation.TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        TrackExecutionTime myAnnotation = method.getAnnotation(TrackExecutionTime.class);


            long startTime = System.currentTimeMillis();
            Object object = point.proceed();
            long endtime = System.currentTimeMillis();
            Logger logger = Logger.getLogger(point.getSignature().getDeclaringTypeName());
        if(myAnnotation!=null) {
            logger.info("[" + point.getSignature().getName() + "] Time taken to " + myAnnotation.value() + ": " + (endtime - startTime) + " ms");
        }
        return object;
    }
}
