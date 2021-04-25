package com.training.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Map;
import java.util.Set;

@Service
public class RedisServiceImp implements RedisService {
    @Autowired
    private Jedis jedis;

    @Override
    public void set(String key, Map<String, Object> body) {
        SetParams setParams = new SetParams();
        setParams.ex(Long.parseLong("3600"));
        setParams.nx();

        jedis.set(key, body.toString(), setParams);
    }

    @Override
    public void update(String key, Map<String, Object> body) {
        SetParams setParams = new SetParams();
        setParams.ex(Long.parseLong("3600"));
        setParams.xx();

        jedis.set(key, body.toString(), setParams);
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

    @Override
    public Long expire(Map<String, Object> body) {
        return jedis.expire(body.get("key").toString(), Long.parseLong(body.get("timeToExpire").toString()));
    }

    @Override
    public Long ttl(String key) {
        return jedis.ttl(key);
    }

    @Override
    public Set<String> keys(String patternKey) {
        System.out.println("keys=" + jedis.keys(patternKey));
        return jedis.keys(patternKey);
    }


    @Override
    public Long exists(String[] keys) {
        return jedis.exists(keys);
    }

    @Override
    public Long del(String keys) {
        return jedis.del(keys);
    }
}
