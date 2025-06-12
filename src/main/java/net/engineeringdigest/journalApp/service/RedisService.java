package net.engineeringdigest.journalApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T get(String key, Class<T> valueType) {
        try {
            String jsonValue = redisTemplate.opsForValue().get(key);
            if (jsonValue != null) {
                return objectMapper.readValue(jsonValue, valueType);
            }
        } catch (Exception e) {
            log.error("Failed to get value from Redis for key: {}", key, e);
        }
        return null;
    }

    public void set(String key, Object value, long ttlInSeconds) {
        try {
            String jsonValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, jsonValue, ttlInSeconds, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize object for Redis key: {}", key, e);
        } catch (Exception e) {
            log.error("Failed to set value in Redis for key: {}", key, e);
        }
    }
}
