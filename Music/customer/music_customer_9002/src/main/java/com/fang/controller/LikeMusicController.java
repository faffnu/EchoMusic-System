package com.fang.controller;

import com.fang.entity.dto.LikeMusicDTO;
import com.fang.response.ResultData;
import com.fang.service.Impl.LikeMusicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/music")
@Tag(name = "喜欢音乐模块")
@Slf4j
public class LikeMusicController {

    @Resource
    private LikeMusicServiceImpl likeMusicServiceImpl;

    @PostMapping("/insert")
    @Operation(summary = "添加喜欢的音乐", description = "需传入用户token和歌曲id")
    public ResultData<Map<String, Object>> insertLikeMusic(
            @RequestParam(name = "token") String token,
            @RequestParam(name = "song_id") String song_id) {
        return likeMusicServiceImpl.insertLikeMusic(token, song_id);
    }

    @PostMapping("/selectLikeMusicByUserId")
    @Operation(summary = "根据用户token查询喜欢的歌曲", description = "需要传入用户token")
    public ResultData<List<LikeMusicDTO>> selectLikeMusicByUserId(@RequestParam(name = "token") String token) {
        List<LikeMusicDTO> likeMusicList = likeMusicServiceImpl.selectLikeMusicByUserId(token);
        if (!likeMusicList.isEmpty()) {
            return ResultData.success(likeMusicList);
        }
        return ResultData.fail("暂无喜欢的歌曲");
    }

    @DeleteMapping("/deleteMusic")
    @Operation(summary = "根据用户id和歌曲id从我喜欢的歌曲中删除", description = "需要传入用户token和歌曲id")
    public ResultData<Map<String, Object>> deleteLikeMusic(
            @RequestParam("token") String token, @RequestParam("song_id") String songId) {
        return likeMusicServiceImpl.deleteLikeMusic(token, songId);
    }
}
