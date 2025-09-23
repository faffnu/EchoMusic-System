package com.fang.entity.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserRegisterVo {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度在3-20个字符")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 5, message = "密码长度至少5位")
    private String passwordHash;

    private String bio;
}
