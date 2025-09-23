package com.fang;

import cn.hutool.core.util.HashUtil;

import java.util.UUID;

public class UUIDDemo {
    public static void main(String[] args) {
        System.out.println(HashUtil.apHash("admin"));
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}
