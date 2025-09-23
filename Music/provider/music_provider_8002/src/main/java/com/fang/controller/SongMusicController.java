package com.fang.controller;

import com.fang.entity.dto.SingerDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.impl.SongMusicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provider/music")
@Tag(name = "音乐")
public class SongMusicController {

    @Resource
    private SongMusicServiceImpl songMusicServiceImpl;

    @GetMapping("/updateSongMusicById")
    @Operation(summary = "音乐播放量+1", description = "需要传入id")
    public ResultData<Object> updateSongMusicById(@RequestParam("id") String id) {
        Boolean result = songMusicServiceImpl.updatePlayCountById(id);
        if (result) {
            return ResultData.success(true);
        }
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
    }

    @GetMapping("/newMusicBoard")
    @Operation(summary = "新歌榜", description = "新歌榜")
    public ResultData<List<SongMusicDTO>> newMusicBoard() {
        List<SongMusicDTO> songMusicDTOList = songMusicServiceImpl.selectMusicByNewMusic();
        return ResultData.success(songMusicDTOList);
    }

    @GetMapping("/hotMusicBoard")
    @Operation(summary = "热歌榜", description = "热歌榜")
    public ResultData<List<SongMusicDTO>> hotMusicBoard() {
        List<SongMusicDTO> songMusicDTOList = songMusicServiceImpl.selectMusicByPlayCount();
        return ResultData.success(songMusicDTOList);
    }

    @GetMapping("/allSingers")
    @Operation(summary = "获取所有歌手", description = "获取系统中所有歌手信息")
    public ResultData<List<SingerDTO>> getAllSingers() {
        List<SingerDTO> singers = songMusicServiceImpl.getAllSingers();
        if (singers.isEmpty()) {
            return ResultData.fail(ResultDataEnum.RC40044.getCode(), "暂无歌手信息");
        }
        return ResultData.success(ResultDataEnum.RC200.getCode(), ResultDataEnum.RC200.getMessage(), singers);
    }

    @GetMapping("/songs")
    @Operation(summary = "获取歌手歌曲", description = "根据歌手ID获取该歌手的全部歌曲")
    public ResultData<List<SongMusicDTO>> getSongsBySingerId(@RequestParam("singerId") String singerId) {
        List<SongMusicDTO> songs = songMusicServiceImpl.getSongsBySingerId(singerId);
        if (songs.isEmpty()) {
            return ResultData.success(ResultDataEnum.RC200.getCode(), "该歌手暂无歌曲");
        }
        return ResultData.success(ResultDataEnum.RC200.getCode(), ResultDataEnum.RC200.getMessage(), songs);
    }
}
