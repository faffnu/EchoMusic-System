package com.fang.entity.vo;

import lombok.Data;

/**
 * 用户登录 vo
 */
@Data
public class MusicUserVo {
    private String userName;
    private String passwordHash;
}
