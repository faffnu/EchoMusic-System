package com.fang.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author fang
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MusicUserDTO implements Serializable {
    private String musicUserId;//用户id
    private String userName; // 用户名称
    private String PasswordHash;//用户密码
    private String avatar; // 用户的头像
    private String bio; // 用户的简介
    private Boolean isVip;// 该用户是否是vip
    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    private Date registrationDate;//用户注册时间
}
