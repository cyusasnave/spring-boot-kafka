package com.cyusasnave.learningkafka.config.kafka;

import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.Map;

public class CustomDeserializer implements Deserializer<Map<String, Object>> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Map<String, Object> deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            String jsonString = new String(data);
            return readJsonAsMap(jsonString);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing value", e);
        }
    }

    private Map<String, Object> readJsonAsMap(final String json) {
        try {
            final TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
            };
            return objectMapper.readValue(json, typeRef);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Error deserializing value", ex);
        }
    }
}
