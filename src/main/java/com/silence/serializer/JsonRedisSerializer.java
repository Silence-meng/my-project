package com.silence.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.nio.charset.StandardCharsets;

/**
 * @author silence
 * @since 2024/11/4 14:09
 **/
@Slf4j
public class JsonRedisSerializer<T> implements RedisSerializer<T> {

    private final Class<T> clazz;

    private final ObjectMapper objectMapper;

    public JsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(T t) {
        if (t == null) {
            return new byte[0];
        }

        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String str = new String(bytes, StandardCharsets.UTF_8);
        try {
            return objectMapper.readValue(str, clazz);
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize object: ", e);
            throw new RuntimeException(e);
        }
    }
}
