package com.fourkites.ocean.es.writer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class JSONUtils {
    @NonNull
    private ObjectMapper mapper;
    public String toJsonString(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
