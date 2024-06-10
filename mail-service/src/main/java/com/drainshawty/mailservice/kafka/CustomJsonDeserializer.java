package com.drainshawty.mailservice.kafka;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.common.serialization.Deserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomJsonDeserializer<T> implements Deserializer<T> {
    ObjectMapper objectMapper = new ObjectMapper();
    Class<T> targetType;

    public CustomJsonDeserializer(Class<T> targetType) {this.targetType = targetType;}

    @Override
    public T deserialize(String s, byte[] bytes) {
        try {return objectMapper.readValue(bytes, targetType);}
        catch (Exception e) {throw new RuntimeException("Failed to deserialize", e);}
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}
    @Override
    public void close() {}
}
