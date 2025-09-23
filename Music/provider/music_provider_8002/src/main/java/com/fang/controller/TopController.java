package com.fang.controller;

import com.fang.entity.dto.PlayListDTO;
import com.fang.service.impl.TopServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "top榜")
@RequestMapping("/provider/music")
public class TopController {
    @Resource
    private TopServiceImpl topServiceImpl;

    @GetMapping("/selectTop")
    @Operation(summary = "top榜获取10条数据", description = "top榜")
    public PlayListDTO selectTop() {
        return topServiceImpl.selectTop();
    }
}
