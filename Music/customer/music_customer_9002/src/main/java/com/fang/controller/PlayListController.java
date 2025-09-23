package com.fang.controller;

import com.fang.entity.dto.PlayListDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.Impl.PlayListServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/music")
@Tag(name = "歌单模块")
public class PlayListController {

    @Resource
    private PlayListServiceImpl playListServiceImpl;

    @GetMapping("/selectPlayListByMusicId/{playListId}")
    @Operation(summary = "根据歌单id获得歌单里面的音乐和歌手，REST风格", description = "需要传入歌单id")
    public ResultData<List<SongMusicDTO>> getPlayListBySongMusicId(@PathVariable("playListId") String playListId) {
        List<SongMusicDTO> playList = playListServiceImpl.getPlayListBySongMusicId(playListId);
        if (playList.isEmpty()) {
            return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
        }
        return ResultData.success(playList);
    }

    @PostMapping("/selectPlayListByTag")
    @Operation(summary = "根据歌单类型获得数据，返回5条数据", description = "需传入歌单类型:LX：流行，MY：民谣，HY：华语")
    public ResultData<List<PlayListDTO>> getPlayListByTag(@RequestParam("tag") String tag) {
        List<PlayListDTO> playListByType = playListServiceImpl.getPlayListByType(tag);
        if (playListByType.isEmpty()) {
            return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
        }
        return ResultData.success(playListByType);
    }

    @GetMapping("/getPlayListAll")
    @Operation(summary = "获得所有歌单", description = "获得所有歌单")
    public ResultData<List<PlayListDTO>> getPlayListAll() {
        List<PlayListDTO> playListAll = playListServiceImpl.getPlayListAll();
        if (playListAll.isEmpty()) {
            return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage());
        }
        return ResultData.success(playListAll);
    }

    @GetMapping("/updatePlayListByPlayCount")
    @Operation(summary = "歌单播放量+1", description = "需要传入歌单id")
    public ResultData<Object> addPlayListById(@RequestParam("play_list_id") String playListId) {
        return playListServiceImpl.updatePlayCountById(playListId);
    }
}
