package com.fang.controller;

import com.fang.entity.dto.PlayListDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.impl.PlayListServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider/music")
@Tag(name = "获取热门推荐歌单")
@Slf4j
public class PlayListController {

    @Resource
    private PlayListServiceImpl playListServiceImpl;

    @GetMapping("/playlist")
    @Operation(summary = "获取热门歌单", description = "根据播放量获取5个热门歌单")
    public List<PlayListDTO> getPlayList() {
        return playListServiceImpl.selectPlayList();
    }

    @PostMapping("/selectPlayListBySongMusicId")
    @Operation(summary = "根据歌单id获取歌曲和歌手信息", description = "post方法，需要传入对应的歌单id")
    public List<SongMusicDTO> getPlayListBySongMusicId(@RequestParam("play_list_id") String playListId) {
        return playListServiceImpl.selectSongMusicByPlayListId(playListId);
    }

    @GetMapping("/getPlayListType")
    @Operation(summary = "获取歌单类型", description = "获取歌单的类型")
    public List<String> getPlayListType() {
        return playListServiceImpl.selectPlayListByPlayListType();
    }

    @PostMapping("/playListByTag")
    @Operation(summary = "根据类型获得歌单", description = "传入类型:LX：流行，MY：民谣，HY：华语")
    public List<PlayListDTO> getPlayListByTag(@RequestParam("tag") String tag) {
        return playListServiceImpl.selectPlayListByTag(tag);
    }

    @GetMapping("/playListAll")
    @Operation(summary = "获得所有歌单", description = "获得所有歌单")
    public List<PlayListDTO> getAllPlayList() {
        return playListServiceImpl.selectPlayListAll();
    }

    @GetMapping("/updatePlayCountById")
    @Operation(summary = "歌单播放量+1", description = "需要传入歌单id")
    public ResultData<Object> updatePlayListByPlayCount(@RequestParam("play_list_id") String playListId) {
        Boolean result = playListServiceImpl.updatePlayCountById(playListId);
        if (result) {
            return ResultData.success(true);
        }
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC50000.getMessage());
    }
}
