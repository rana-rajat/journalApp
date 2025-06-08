package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void redisTest(){
        redisTemplate.opsForValue().set("email","rajatrana2309@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        int a= 1;
    }

}
