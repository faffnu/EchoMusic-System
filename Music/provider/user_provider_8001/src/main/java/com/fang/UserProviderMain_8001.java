package com.fang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.fang.mapper")
@EnableCaching // 开启缓存
@EnableDiscoveryClient // 开启服务注册与发现
public class UserProviderMain_8001 {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderMain_8001.class, args);
    }
}
