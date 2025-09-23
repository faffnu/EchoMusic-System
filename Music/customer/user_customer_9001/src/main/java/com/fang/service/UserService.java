package com.fang.service;

import com.fang.entity.vo.MusicUserVo;
import com.fang.entity.vo.PasswordChangeVo;
import com.fang.entity.vo.UserRegisterVo;
import com.fang.entity.vo.UserUpdateVo;
import com.fang.response.ResultData;

import java.util.Map;

public interface UserService {
    ResultData<Map<Object, Object>> selectMusicUser(MusicUserVo musicUserVo);

    ResultData<String> registerUser(UserRegisterVo registerVo);

    ResultData<String> updateUserInfo(String userId, UserUpdateVo updateVo);

    ResultData<String> changePassword(String userId, PasswordChangeVo passwordVo);
}
