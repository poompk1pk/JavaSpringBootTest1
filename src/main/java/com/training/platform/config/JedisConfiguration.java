package com.training.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisConfiguration {
    @Bean
    public Jedis jedisCreateConnection(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        return jedis;
    }
}
