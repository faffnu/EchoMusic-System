package com.fang;

import com.fang.mapper.NewMusicTypeMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NewMusicTypeMapperTest {
    @Resource
    private NewMusicTypeMapper newMusicTypeMapper;

    @Test
    public void test() {
    }
}
