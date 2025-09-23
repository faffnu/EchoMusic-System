package com.fang.controller;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.service.impl.NewMusicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "新歌推荐")
@RequestMapping("/provider/music")
@Slf4j
public class NewMusicController {
    @Resource
    private NewMusicServiceImpl newMusicServiceImpl;

    @GetMapping("/newMusic")
    @Operation(summary = "获取新歌", description = "返回6条数据")
    public List<SongMusicDTO> newMusic() {
        return newMusicServiceImpl.selectNewMusic();
    }

    @PostMapping("/newMusicByType")
    @Operation(summary = "根据类型获取歌曲", description = "歌曲类型：ZH：华语、EA：欧美、KR：韩国")
    public List<SongMusicDTO> newMusicByType(@RequestParam("type") String type) {
        return newMusicServiceImpl.selectMusicByType(type);
    }

    @GetMapping("/getType")
    @Operation(summary = "获取歌曲类型", description = "歌曲类型")
    public List<SongMusicDTO> getType() {
        return newMusicServiceImpl.selectMusicType();
    }
}
