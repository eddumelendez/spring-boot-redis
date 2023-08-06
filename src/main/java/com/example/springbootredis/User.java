package com.example.springbootredis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("users")
public record User(@Id String username, String email) {
}
