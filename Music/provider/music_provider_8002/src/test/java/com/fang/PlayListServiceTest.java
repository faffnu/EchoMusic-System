package com.fang;

import com.fang.entity.dto.PlayListDTO;
import com.fang.service.impl.PlayListServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PlayListServiceTest {

    @Resource
    private PlayListServiceImpl playListServiceImpl;

    @Test
    void test(){
        List<PlayListDTO> playListDTOS = playListServiceImpl.selectPlayListByTag("HY");
        playListDTOS.forEach(System.out::println);
    }
}
