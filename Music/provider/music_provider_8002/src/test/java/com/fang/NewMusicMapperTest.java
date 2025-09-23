package com.fang;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.mapper.NewMusicMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NewMusicMapperTest {
    @Resource
    private NewMusicMapper newMusicMapper;

    @Test
    void selectNewMusic() {
        List<SongMusicDTO> songMusicDTOS = newMusicMapper.selectNewMusic();
        songMusicDTOS.forEach(System.out::println);
    }
}
