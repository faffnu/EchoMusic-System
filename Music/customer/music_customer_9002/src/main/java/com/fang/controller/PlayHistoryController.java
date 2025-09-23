package com.fang.controller;

import com.fang.entity.dto.PlayHistoryDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.entity.vo.PlayHistoryVo;
import com.fang.response.ResultData;
import com.fang.service.PlayHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/music/playHistory")
@Tag(name = "调用者播放历史")
public class PlayHistoryController {

    @Resource
    private PlayHistoryService playHistoryService;

    @PostMapping("/selectByToken")
    @Operation(summary = "查询用户的播放历史记录", description = "需要传入用户token")
    public ResultData<List<SongMusicDTO>> selectByUserId(@RequestParam("token") String token) {
        return playHistoryService.selectPlayHistoryByUserId(token);
    }

    @PostMapping("/insert")
    @Operation(summary = "添加一条播放记录", description = "需要PlayHistoryDTO")
    public ResultData<String> insertPlayHistory(@RequestBody PlayHistoryDTO playHistoryDTO) {
        return playHistoryService.insertPlayHistory(playHistoryDTO);
    }

    @PostMapping("/delete")
    @Operation(summary = "删除一条播放记录", description = "需要PlayHistoryVo")
    public ResultData<String> deletePlayHistory(@RequestBody PlayHistoryVo playHistoryVo) {
        return playHistoryService.deletePlayHistory(playHistoryVo);
    }
}
