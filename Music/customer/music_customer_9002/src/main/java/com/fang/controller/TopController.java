package com.fang.controller;

import com.fang.entity.dto.PlayListDTO;
import com.fang.response.ResultData;
import com.fang.service.TopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/music")
@Tag(name = "top榜")
public class TopController {

    @Resource
    private TopService topService;

    @GetMapping("/getTop")
    @Operation(summary = "获得top榜歌单",description = "获得top榜歌单")
    public ResultData<PlayListDTO> getTop() {
        PlayListDTO playListDTO = topService.selectTop();
        return ResultData.success(playListDTO);
    }
}
