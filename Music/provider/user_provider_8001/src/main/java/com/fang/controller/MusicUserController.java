package com.fang.controller;

import com.fang.entity.dto.MusicUserDTO;
import com.fang.entity.vo.MusicUserVo;
import com.fang.entity.vo.PasswordChangeVo;
import com.fang.entity.vo.UserRegisterVo;
import com.fang.entity.vo.UserUpdateVo;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.Impl.MusicUserServiceImpl;
import com.fang.utils.ParseToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/provider/user")
@Slf4j
@Tag(name = "用户登录", description = "用户登录模块")
public class MusicUserController {

    @Resource
    private MusicUserServiceImpl musicUserServiceImpl;

    /**
     * 根据用户名和密码查找用户
     *
     * @param musicUserVo MusicUserVo
     * @return ResultData<Map < Object, Object>>
     */
    @Operation(method = "post方法", summary = "用户登录"
            , description = "需要传入用户名和密码，用户名是username,密码是passwordHash，返回用户的信息和token，请求用户相关的时候必须带上token")
    @PostMapping("/login")
    public ResultData<Map<Object, Object>> selectMusicUser(@RequestBody @Validated MusicUserVo musicUserVo) {
        return musicUserServiceImpl.selectMusicUser(musicUserVo);
    }

    @Operation(method = "get方法", summary = "根据用户id查询", description = "需要传入用户id")
    @GetMapping("/selectUserById")
    public MusicUserDTO selectUserById(@RequestParam("id") String id) {
        log.info("debug=>{}", id);
        return musicUserServiceImpl.selectMusicUserById(id);
    }

    @Operation(summary = "用户注册", description = "新用户注册接口")
    @PostMapping("/register")
    public ResultData<String> registerUser(@RequestBody @Validated UserRegisterVo registerVo) {
        return musicUserServiceImpl.registerUser(registerVo);
    }

    @Operation(summary = "更新用户信息", description = "更新用户基本信息")
    @PutMapping("/update")
    public ResultData<String> updateUserInfo(@RequestParam(name = "token") String token,
                                             @RequestBody @Validated UserUpdateVo updateVo) {
        String userId = ParseToken.parseToken(token);
        if (userId == null) {
            return ResultData.fail(ResultDataEnum.RC500.getCode(), "非法token!");
        }
        return musicUserServiceImpl.updateUserInfo(userId, updateVo);
    }

    @Operation(summary = "修改密码", description = "用户修改密码")
    @PutMapping("/changePassword")
    public ResultData<String> changePassword(@RequestParam(name = "token") String token,
                                             @RequestBody @Validated PasswordChangeVo passwordVo) {
        String userId = ParseToken.parseToken(token);
        if (userId == null) {
            return ResultData.fail(ResultDataEnum.RC500.getCode(), "非法token!");
        }
        return musicUserServiceImpl.changePassword(userId, passwordVo);
    }

    @Operation(summary = "检查用户名是否存在", description = "检查用户名是否已被注册")
    @GetMapping("/checkUsername")
    public ResultData<Boolean> checkUsername(@RequestParam String username) {
        // 实现用户名检查逻辑
        return ResultData.success(ResultDataEnum.RC200.getCode(), "检查成功",
                musicUserServiceImpl.checkUsernameExists(username));
    }
}
