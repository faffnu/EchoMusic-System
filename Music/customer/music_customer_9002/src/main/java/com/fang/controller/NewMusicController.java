package com.fang.controller;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.Impl.NewMusicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/music")
@Tag(name = "新歌模块")
public class NewMusicController {

    @Resource
    private NewMusicServiceImpl newMusicServiceImpl;

    @PostMapping("/getMusicByType")
    @Operation(summary = "根据歌曲类型获得新歌", description = "需要传入歌曲类型")
    public ResultData<List<SongMusicDTO>> getMusicByType(@RequestParam("type") String type) {
        List<SongMusicDTO> songMusicList = newMusicServiceImpl.newMusicByType(type);
        if (songMusicList.isEmpty()) {
            return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
        }
        return ResultData.success(songMusicList);
    }
}
