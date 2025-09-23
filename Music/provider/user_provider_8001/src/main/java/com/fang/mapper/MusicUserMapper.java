package com.fang.mapper;

import com.fang.entity.dto.MusicUserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户 Mapper
 *
 * @author Fang
 */
@Repository
public interface MusicUserMapper {
    /**
     * 根据用户名和密码查询用户信息
     * @param username 用户名
     * @param password 密码
     * @return MusicUserDTO
     */
    MusicUserDTO selectMusicUser(@Param("username") String username,
                                 @Param("password") String password);

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return MusicUserDTO
     */
    MusicUserDTO selectMusicUserById(@Param("id") String id);

    /**
     * 更新最后登录时间
     * @param username 用户名
     * @return Boolean
     */
    Boolean updateLatLoginMusicUser(@Param("username") String username);

    /**
     * 插入新用户
     */
    Boolean insertMusicUser(MusicUserDTO musicUserDTO);

    /**
     * 更新用户信息
     */
    Boolean updateMusicUser(MusicUserDTO musicUserDTO);

    /**
     * 更新密码
     */
    Boolean updatePassword(@Param("userId") String userId,
                           @Param("newPassword") String newPassword);

    /**
     * 根据用户名查询用户
     */
    MusicUserDTO selectMusicUserByUsername(@Param("username") String username);

    /**
     * 检查用户名是否存在
     */
    Boolean existsByUsername(@Param("username") String username);


}
