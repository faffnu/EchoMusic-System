package com.fang;

import com.fang.mapper.TopMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TopMapperTest {
    @Resource
    private TopMapper topMapper;

    @Test
    void selectTop() {
        System.out.println(topMapper.selectTop());
    }
}
