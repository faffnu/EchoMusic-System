package com.fang.controller;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.impl.SongMusicServiceImpl;
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
@Tag(name = "搜索歌曲")
@Slf4j
public class SearchMusicController {

    @Resource
    private SongMusicServiceImpl songMusicServiceImpl;

    @PostMapping("/search")
    @Operation(summary = "搜索歌曲", description = "传入歌曲名称")
    public ResultData<List<SongMusicDTO>> search(@RequestParam("music_name") String music_name) {
        List<SongMusicDTO> songMusicList = songMusicServiceImpl.searchMusicByName(music_name);
        if (songMusicList.isEmpty()) {
            return ResultData.fail(ResultDataEnum.RC40044.getCode(), ResultDataEnum.RC40044.getMessage());
        }
        return ResultData.success(ResultDataEnum.RC200.getCode(), ResultDataEnum.RC200.getMessage(), songMusicList);
    }


}
