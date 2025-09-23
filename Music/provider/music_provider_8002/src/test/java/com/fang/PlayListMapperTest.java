package com.fang;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.mapper.PlayListMapper;
import com.fang.service.PlayListService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PlayListMapperTest {
    @Resource
    private PlayListMapper playListMapper;

    @Test
    public void test() {
        List<SongMusicDTO> songAndPlayListDTOS = playListMapper.selectSongMusicByPlayListId("2b148085292844409d5f6544ce7d1839");
        songAndPlayListDTOS.forEach(System.out::println);
    }

    @Resource
    private PlayListService playListService;
    @Test
    public void test1() {
        List<SongMusicDTO> songMusicDTOS = playListService.selectSongMusicByPlayListId("2b148085292844409d5f6544ce7d1839");
        songMusicDTOS.forEach(System.out::println);
    }
}
