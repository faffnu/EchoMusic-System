package com.fang.enums;

import lombok.Getter;

/**
 * 用户状态枚举类
 *
 * @author Fang
 */
@Getter
public enum AccountStatus {
    active("正常"),
    suspended("冻结"),
    banned("封禁");

    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }
}