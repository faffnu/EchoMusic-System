package com.fang.controller;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.service.impl.NewMusicTypeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provider/music")
@Slf4j
@Tag(name = "根据歌曲类型获得歌曲")
public class NewMusicTypeController {

    @Resource
    private NewMusicTypeServiceImpl newMusicTypeService;

    @PostMapping("/getMusicByTag")
    @Operation(summary = "获取歌曲", description = "需要传入歌曲类型，ZH：华语，ER：欧美，KR：韩国")
    public List<SongMusicDTO> getMusicByTag(@RequestParam("tag") String tag) {
        return newMusicTypeService.selectByType(tag);
    }
}
