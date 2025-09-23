package com.fang.feign;

import com.fang.entity.dto.MusicUserDTO;
import com.fang.entity.vo.MusicUserVo;
import com.fang.entity.vo.PasswordChangeVo;
import com.fang.entity.vo.UserRegisterVo;
import com.fang.entity.vo.UserUpdateVo;
import com.fang.feign.fallBack.MusicUserFeignClientFallBack;
import com.fang.response.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "user-provider", fallback = MusicUserFeignClientFallBack.class)
public interface MusicUserFeignClient {
    @PostMapping("/provider/user/login")
    public ResultData<Map<Object, Object>> selectMusicUser(@RequestBody @Validated MusicUserVo musicUserVo);

    @PostMapping("/provider/user/register")
    public ResultData<String> registerUser(@RequestBody @Validated UserRegisterVo registerVo);

    @PutMapping("/provider/user/update")
    public ResultData<String> updateUserInfo(@RequestParam(name = "token") String token, @RequestBody @Validated UserUpdateVo updateVo);

    @PutMapping("/provider/user/changePassword")
    public ResultData<String> changePassword(@RequestParam(name = "token") String token, @RequestBody @Validated PasswordChangeVo passwordVo);

}
