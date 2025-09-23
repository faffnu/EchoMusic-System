package com.fang.controller;

import com.fang.entity.dto.LikeMusicDTO;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.impl.LikeMusicServiceImpl;
import com.fang.utils.ParseToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 添加喜欢的音乐，查询喜欢的音乐
 */
@Slf4j
@RestController
@RequestMapping("/provider/music")
@Tag(name = "喜欢的音乐CRUD")
public class LikeMusicController {

    @Resource
    private LikeMusicServiceImpl likeMusicServiceImpl;

    @PostMapping("/insertLikeMusic")
    @Operation(summary = "添加喜欢的音乐", description = "需要传入用户token，和音乐的id")
    public ResultData<Map<String, Object>> insertLikeMusic(
            @RequestParam(name = "token") String token,
            @RequestParam(name = "song_id") String song_id) {
        String user_id = ParseToken.parseToken(token);
        if (user_id == null) {
            return ResultData.fail(ResultDataEnum.RC500.getCode(), "非法token!");
        }
        return likeMusicServiceImpl.insertLikeMusic(user_id, song_id);
    }

    @PostMapping("/selectLikeMusicByUserId")
    @Operation(summary = "根据用户token查询用户喜欢的音乐", description = "展示用户喜欢的音乐和歌手信息")
    public List<LikeMusicDTO> selectLikeMusicByUserId(@RequestParam(name = "token") String token) {
        String user_id = ParseToken.parseToken(token);
        return likeMusicServiceImpl.selectLikeMusicByUserId(user_id);
    }

    @DeleteMapping("/deleteMusic")
    @Operation(summary = "根据用户id和歌曲id删除一条记录", description = "需要传入用户token和歌曲id")
    public ResultData<Map<String, Object>> deleteLikeMusic(@RequestParam("token") String token,
                                                           @RequestParam("song_id") String songId) {
        String user_id = ParseToken.parseToken(token);
        if (user_id == null) {
            return ResultData.fail(ResultDataEnum.RC500.getCode(), "非法token!");
        }
        Boolean result = likeMusicServiceImpl.deleteLikeMusicByUserId(user_id, songId);
        if (result) {
            return ResultData.success(ResultDataEnum.RC2002.getCode(), ResultDataEnum.RC2002.getMessage());
        }
        return ResultData.fail(ResultDataEnum.DE404.getCode(), ResultDataEnum.DE404.getMessage());
    }
}
