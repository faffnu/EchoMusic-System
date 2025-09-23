package com.fang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MusicCustomerMain_9002 {
    public static void main(String[] args) {
        SpringApplication.run(MusicCustomerMain_9002.class, args);
    }
}
