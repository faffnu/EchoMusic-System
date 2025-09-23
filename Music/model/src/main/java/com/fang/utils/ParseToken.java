package com.fang.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;

public class ParseToken {

    public static String parseToken(String token) {
        final JWT jwt = JWTUtil.parseToken(token);//解析token
        jwt.getHeader(JWTHeader.TYPE);//获得token头部信息
        String user_id;
        try {
            user_id = (String) jwt.getPayload("id");
            return user_id;
        } catch (Exception e) {
            return null;
        }
    }
}
