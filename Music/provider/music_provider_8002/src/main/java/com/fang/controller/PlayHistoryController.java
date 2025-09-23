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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/provider/music/history")
@Tag(name = "用户播放历史")
public class PlayHistoryController {

    @Resource
    private PlayHistoryService playHistoryService;

    @PostMapping("/selectPlayHistoryByUserToken")
    @Operation(summary = "根据用户token查询用户播放历史", description = "需要用户token")
    public ResultData<List<SongMusicDTO>> selectPlayHistoryByUserId(@RequestParam String token) {
        return playHistoryService.selectPlayHistoryByUserId(token);
    }

    @PostMapping("/insert")
    @Operation(summary = "添加一条播放历史", description = "需要传入PlayHistory对象")
    public ResultData<String> insertPlayHistory(@RequestBody @Validated PlayHistoryDTO playHistoryDTO) {
        return playHistoryService.insertPlayHistory(playHistoryDTO);
    }

    @PostMapping("/delete")
    @Operation(summary = "删除一条播放历史", description = "需要传入PlayHistoryVo")
    public ResultData<String> deletePlayHistory(@RequestBody @Validated PlayHistoryVo playHistoryVo) {
        return playHistoryService.deletePlayHistoryByPlayHistoryVo(playHistoryVo);
    }
}
