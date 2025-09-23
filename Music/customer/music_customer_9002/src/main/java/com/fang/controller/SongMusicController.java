package com.fang.controller;

import com.fang.entity.dto.SingerDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.Impl.SongMusicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/music")
@Tag(name = "音乐API")
public class SongMusicController {

    @Resource
    private SongMusicServiceImpl songMusicServiceImpl;

    @GetMapping("/updatePlayCount")
    @Operation(summary = "音乐id播放量+1", description = "需要传入id")
    public ResultData<Object> updatePlayCount(@RequestParam("id") String id) {
        return songMusicServiceImpl.updateSongMusicById(id);
    }

    @GetMapping("/new_music_board")
    @Operation(summary = "新歌榜", description = "新歌榜")
    public ResultData<List<SongMusicDTO>> newMusicBoard() {
        return songMusicServiceImpl.newMusicBoard();
    }

    @GetMapping("/hot_music_board")
    @Operation(summary = "新歌榜", description = "新歌榜")
    public ResultData<List<SongMusicDTO>> hotMusicBoard() {
        return songMusicServiceImpl.hotMusicBoard();
    }

    @GetMapping("/allSingers")
    @Operation(summary = "获取所有歌手", description = "获取系统中所有歌手信息")
    public ResultData<List<SingerDTO>> getAllSingers() {
        return songMusicServiceImpl.getAllSingers();
    }

    @GetMapping("/songs")
    @Operation(summary = "获取歌手歌曲", description = "根据歌手ID获取该歌手的全部歌曲")
    public ResultData<List<SongMusicDTO>> getSongsBySingerId(@RequestParam("singerId") String singerId) {
        return songMusicServiceImpl.getSongsBySingerId(singerId);
    }

}
