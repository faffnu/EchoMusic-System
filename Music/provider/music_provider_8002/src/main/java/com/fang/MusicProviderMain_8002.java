package com.fang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients //开启openfeign
@EnableDiscoveryClient //开启服务注册与发现
@SpringBootApplication
@MapperScan("com.fang.mapper") //扫描 mapper
@EnableCaching // 开启缓存
@EnableTransactionManagement // 开启事务
public class MusicProviderMain_8002 {
    public static void main(String[] args) {
        SpringApplication.run(MusicProviderMain_8002.class, args);
    }
}
