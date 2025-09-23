package com.fang.utils;

import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.digest.DigestUtil;

public class PasswordUtil {

    // 使用BCrypt加密密码
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password);
    }

    // 验证密码
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    // MD5加密（备用）
    public static String md5Encrypt(String password) {
        return DigestUtil.md5Hex(password);
    }
}