package com.fang.entity.vo;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateVo {
    @Size(max = 200, message = "简介长度不能超过200字符")
    private String bio;

    private String avatar;

    private Boolean isVip;
}
