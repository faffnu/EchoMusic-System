package com.fang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fang.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * @author fang
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MusicUser implements Serializable {
    private String musicUserId; // 用户id
    private String userName; // 用户名称
    private String passwordHash; // 用户密码
    private String avatar; // 用户的头像
    private String bio; // 用户的简介
    private Boolean isVip;// 该用户是否是vip
    private AccountStatus accountStatus; // 用户状态
    private String likeMusicId; // 用户喜欢音乐的id
    @JsonFormat(pattern = "yyyy年MM月dd日",timezone = "GMT+8")
    private Date registrationDate;// 用户注册的时间
    @JsonFormat(pattern = "yyyy年MM月dd日",timezone = "GMT+8")
    private Date lastLoginDate; // 用户最后登录的时间
}
