package com.fang.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign 配置类
 *
 * @author Fang
 */
@Configuration
public class OpenFeignConfig {

    /**
     * 开启打印日志
     *
     * @return 日志等级
     */
    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 开启重试
     *
     * @return 默认重试
     */
    @Bean
    public Retryer retryer() {
        // 重试间隔时间、最大重试间隔时间、最大重试次数
        return new Retryer.Default(1000, 1000, 3);
    }
}
