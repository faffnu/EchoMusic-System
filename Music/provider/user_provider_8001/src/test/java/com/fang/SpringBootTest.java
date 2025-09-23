package com.fang;

import cn.hutool.core.util.HashUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {
    @Test
    public void test() {
        System.out.println(HashUtil.apHash("user"));
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }

    @Test
    public void test2() {
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
    }

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void testRedis(){
        System.out.println(redisTemplate.keys("*").size());
    }
}
