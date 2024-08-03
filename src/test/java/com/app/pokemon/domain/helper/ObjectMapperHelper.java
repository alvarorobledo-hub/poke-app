package com.app.pokemon.domain.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectMapperHelper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String writeValue(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new RuntimeException("Error converting value to JSON string: ", e);
        }
    }
}
