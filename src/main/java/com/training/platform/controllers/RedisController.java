package com.training.platform.controllers;


import com.training.platform.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @PostMapping("/{key}")
    String create(@PathVariable String key, @RequestBody Map<String, Object> body) {
        redisService.set(key, body);
        return "set redis id : " + key + " success";
    }

    @PatchMapping("/{key}")
    String update(@PathVariable String key, @RequestBody Map<String, Object> body) {
        redisService.update(key, body);
        return "set redis id : " + key + " success";
    }

    @GetMapping("/{key}")
    String get(@PathVariable String key) {
        return redisService.get(key);
    }

    @PatchMapping("/expire")
    String expire(@RequestBody Map<String, Object> body) {
        redisService.expire(body);
        return "update expire key : " + body.get("key");
    }

    @GetMapping("/ttl")
    String ttl(@RequestParam String key) {
        Long ttl = redisService.ttl(key);
        return "ttl " + key + " : " + ttl + " (s)";
    }

    @GetMapping("/keys")
    Set<String> keys(@RequestParam String pattern) {
        return redisService.keys(pattern);
    }

    @GetMapping("/exists")
    String exists(@RequestParam String[] keys) {
        Long exists = redisService.exists(keys);
        return "exists : " + exists + " (key)";
    }

    @DeleteMapping("/{key}")
    String del(@PathVariable String key) {
        Long del = redisService.del(key);
        return "delete : " + del + " (key)";
    }
}


