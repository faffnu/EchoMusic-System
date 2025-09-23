package com.fang.controller;

import com.fang.entity.vo.MusicUserVo;
import com.fang.entity.vo.PasswordChangeVo;
import com.fang.entity.vo.UserRegisterVo;
import com.fang.entity.vo.UserUpdateVo;
import com.fang.response.ResultData;
import com.fang.service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user")
@Tag(name = "用户模块")
public class UserController {

    @Resource
    private UserServiceImpl userServiceImpl;

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "需要传入账号和密码")
    public ResultData<Map<Object, Object>> login(@RequestBody MusicUserVo musicUserVo) {
        log.info("debug=>{}", musicUserVo.toString());
        return userServiceImpl.selectMusicUser(musicUserVo);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "需要传入账号、密码和简介")
    public ResultData<String> registerUser(@RequestBody @Validated  UserRegisterVo registerVo){
        log.info("debug=>{}", registerVo.toString());
        return userServiceImpl.registerUser(registerVo);
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户信息")
    public ResultData<String> updateUserInfo(@RequestParam(name = "token") String token, @RequestBody @Validated UserUpdateVo updateVo){
        log.info("debug=>{}", updateVo.toString());
        return userServiceImpl.updateUserInfo(token, updateVo);
    }

    @PutMapping("/changePassword")
    @Operation(summary = "修改密码")
    public ResultData<String> changePassword(@RequestParam(name = "token") String token, @RequestBody @Validated PasswordChangeVo passwordVo){
        log.info("debug=>{}", passwordVo.toString());
        return userServiceImpl.changePassword(token, passwordVo);
    }

}
