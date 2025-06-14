package net.engineeringdigest.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfg {

    // why this clas basically when we are setting some value in redis cli we are not getting here and vice versa basically we are setting serializer and deserializer
//    @Bean
//    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
//      RedisTemplate redisTemplate = new RedisTemplate<>();
//      redisTemplate.setConnectionFactory(factory);
//      redisTemplate.setKeySerializer(new StringRedisSerializer());
//      redisTemplate.setValueSerializer(new StringRedisSerializer());
//     return redisTemplate;
//    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // Use String serializer for both keys and values
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);

        redisTemplate.afterPropertiesSet(); // ensures everything is initialized properly
        return redisTemplate;
    }
}
