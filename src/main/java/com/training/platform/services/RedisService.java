package com.training.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

public interface RedisService {

    void set(String key, Map<String, Object> body);

    void update(String key, Map<String, Object> body);

    String get(String key);

    Long expire(Map<String, Object> body);

    Long ttl(String key);

    Set<String> keys(String patternKey);

    Long exists(String[] keys);

    Long del(String keys);
}
