package com.fang.service.Impl;

import cn.hutool.core.util.HashUtil;
import cn.hutool.jwt.JWTUtil;
import com.fang.entity.dto.MusicUserDTO;
import com.fang.entity.vo.MusicUserVo;
import com.fang.entity.vo.PasswordChangeVo;
import com.fang.entity.vo.UserRegisterVo;
import com.fang.entity.vo.UserUpdateVo;
import com.fang.mapper.MusicUserMapper;
import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import com.fang.service.MusicUserService;
import com.fang.system.StaticUrl;
import com.fang.utils.PasswordUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Slf4j
@Service
public class MusicUserServiceImpl implements MusicUserService {

    @Resource
    private MusicUserMapper musicUserMapper;

    private final String uuid = UUID.randomUUID().toString().replace("-", "");
    private final String uri = StaticUrl.getUrl();

    /**
     * 传入账号密码，判断是否登录成功，存储到数据库里面,更新用户最后登录的时间,登录成功向RabbitMQ发送消息
     *
     * @param musicUserVo 用户名和密码
     * @return 返回 ResultData,表示是否登录成功
     */
    @Override
    public ResultData<Map<Object, Object>> selectMusicUser(MusicUserVo musicUserVo) {
        MusicUserDTO musicUserDTO = musicUserMapper.selectMusicUserByUsername(musicUserVo.getUserName());
        if (musicUserDTO == null||!PasswordUtil.verifyPassword(musicUserVo.getPasswordHash(),musicUserDTO.getPasswordHash())) {
            return ResultData.fail(ResultDataEnum.RC40004.getCode(), ResultDataEnum.RC40004.getMessage());
        }
        //创建token，请求的时候必须带上token
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("username", musicUserDTO.getUserName());
        tokenMap.put("id", musicUserDTO.getMusicUserId());
        String token = JWTUtil.createToken(tokenMap, musicUserDTO.getUserName().getBytes());
        //创建返回对象
        ResultData<Map<Object, Object>> resultData = new ResultData<>();
        Map<Object, Object> resultMap = new HashMap<>();
        // 静态资源路径
        musicUserDTO.setAvatar(uri + musicUserDTO.getAvatar());
        resultMap.put("user", musicUserDTO);
        resultMap.put("token", token);
        resultData.setData(resultMap);
        //更新用户最后登录时间
        updateLatLoginMusicUser(musicUserVo.getUserName());
        return ResultData.success(ResultDataEnum.RC200.getCode(), "登录成功", resultMap);
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return MusicUserDTO
     */
    @Override
    public MusicUserDTO selectMusicUserById(String id) {
        MusicUserDTO musicUserDTO = musicUserMapper.selectMusicUserById(id);
        if (musicUserDTO == null) {
            return null;
        }
        musicUserDTO.setAvatar(uri + musicUserDTO.getAvatar());
        return musicUserDTO;
    }

    /**
     * 根据用户名更新用户最后登录时间
     *
     * @param username 用户名
     */
    @Override
    public void updateLatLoginMusicUser(String username) {
        musicUserMapper.updateLatLoginMusicUser(username);
    }

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String USER_CACHE_PREFIX = "user:";

    // 用户注册
    @Override
    @Transactional
    public ResultData<String> registerUser(UserRegisterVo registerVo) {
        // 检查用户名是否存在
        if (musicUserMapper.existsByUsername(registerVo.getUserName())) {
            return ResultData.fail(ResultDataEnum.RC400.getCode(), "用户名已存在");
        }

        // 创建用户DTO
        MusicUserDTO userDTO = new MusicUserDTO()
                .setMusicUserId(uuid)
                .setUserName(registerVo.getUserName())
                .setPasswordHash(PasswordUtil.encryptPassword(registerVo.getPasswordHash()))
                .setBio(registerVo.getBio())
                .setIsVip(false)
                .setRegistrationDate(new Date());

        // 插入数据库
        if (musicUserMapper.insertMusicUser(userDTO)) {
            // 清除缓存
            redisTemplate.delete(USER_CACHE_PREFIX + userDTO.getMusicUserId());
            return ResultData.success(ResultDataEnum.RC200.getCode(), "注册成功", userDTO.getMusicUserId());
        }

        return ResultData.fail(ResultDataEnum.RC500.getCode(), "注册失败");
    }

    // 更新用户信息
    @Override
    @Transactional
    @CachePut(value = "users", key = "#userId")
    public ResultData<String> updateUserInfo(String userId, UserUpdateVo updateVo) {
        MusicUserDTO existingUser = musicUserMapper.selectMusicUserById(userId);
        if (existingUser == null) {
            return ResultData.fail(ResultDataEnum.RC404.getCode(), "用户不存在");
        }

        MusicUserDTO updateDTO = new MusicUserDTO()
                .setMusicUserId(userId)
                .setBio(updateVo.getBio())
                .setAvatar(updateVo.getAvatar())
                .setIsVip(updateVo.getIsVip());

        if (musicUserMapper.updateMusicUser(updateDTO)) {
            return ResultData.success(ResultDataEnum.RC200.getCode(), "更新成功", userId);
        }

        return ResultData.fail(ResultDataEnum.RC500.getCode(), "更新失败");
    }

    // 修改密码
    @Override
    @Transactional
    public ResultData<String> changePassword(String userId, PasswordChangeVo passwordVo) {
        // 验证新密码和确认密码是否一致
        if (!passwordVo.getNewPassword().equals(passwordVo.getConfirmPassword())) {
            return ResultData.fail(ResultDataEnum.RC400.getCode(), "新密码和确认密码不一致");
        }

        // 获取用户信息验证原密码
        MusicUserDTO user = musicUserMapper.selectMusicUserById(userId);
        if (user == null) {
            return ResultData.fail(ResultDataEnum.RC404.getCode(), "用户不存在");
        }

        // 这里需要存储原密码的加密方式
        if (!PasswordUtil.verifyPassword(passwordVo.getOldPassword(), user.getPasswordHash())) {
            return ResultData.fail(ResultDataEnum.RC400.getCode(), "原密码错误");
        }

        // 更新密码
        String newEncryptedPassword = PasswordUtil.encryptPassword(passwordVo.getNewPassword());
        if (musicUserMapper.updatePassword(userId, newEncryptedPassword)) {
            return ResultData.success(ResultDataEnum.RC200.getCode(), "密码修改成功", userId);
        }

        return ResultData.fail(ResultDataEnum.RC500.getCode(), "密码修改失败");
    }

    @Override
    public Boolean checkUsernameExists(String username) {
        return musicUserMapper.existsByUsername(username);
    }
}
