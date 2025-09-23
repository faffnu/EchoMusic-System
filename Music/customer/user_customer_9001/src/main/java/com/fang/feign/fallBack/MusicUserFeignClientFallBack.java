package com.fang.feign.fallBack;

import com.fang.entity.dto.MusicUserDTO;
import com.fang.entity.vo.MusicUserVo;
import com.fang.entity.vo.PasswordChangeVo;
import com.fang.entity.vo.UserRegisterVo;
import com.fang.entity.vo.UserUpdateVo;
import com.fang.feign.MusicUserFeignClient;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MusicUserFeignClientFallBack implements MusicUserFeignClient {

    @Override
    public ResultData<Map<Object, Object>> selectMusicUser(MusicUserVo musicUserVo) {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage(), "登录失败");
    }


    @Override
    public ResultData<String> registerUser(UserRegisterVo registerVo) {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage(), "注册失败");
    }

    @Override
    public ResultData<String> updateUserInfo(String userId, UserUpdateVo updateVo) {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage(), "更新用户失败");
    }

    @Override
    public ResultData<String> changePassword(String userId, PasswordChangeVo passwordVo) {
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ResultDataEnum.RC500.getMessage(), "修改密码失败");
    }

}
