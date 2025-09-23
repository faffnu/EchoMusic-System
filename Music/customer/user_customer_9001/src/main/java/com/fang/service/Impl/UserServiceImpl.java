package com.fang.service.Impl;

import com.fang.entity.dto.MusicUserDTO;
import com.fang.entity.vo.MusicUserVo;
import com.fang.entity.vo.PasswordChangeVo;
import com.fang.entity.vo.UserRegisterVo;
import com.fang.entity.vo.UserUpdateVo;
import com.fang.feign.MusicUserFeignClient;
import com.fang.response.ResultData;
import com.fang.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private MusicUserFeignClient musicUserFeignClient;

    @Override
    public ResultData<Map<Object, Object>> selectMusicUser(MusicUserVo musicUserVo) {
        return musicUserFeignClient.selectMusicUser(musicUserVo);
    }

    @Override
    public ResultData<String> registerUser(UserRegisterVo registerVo){
        return musicUserFeignClient.registerUser(registerVo);
    }

    @Override
    public ResultData<String> updateUserInfo(String token, UserUpdateVo updateVo){
        return musicUserFeignClient.updateUserInfo(token,updateVo);
    }

    @Override
    public ResultData<String> changePassword(String token, PasswordChangeVo passwordVo){
        return musicUserFeignClient.changePassword(token,passwordVo);
    }

}
