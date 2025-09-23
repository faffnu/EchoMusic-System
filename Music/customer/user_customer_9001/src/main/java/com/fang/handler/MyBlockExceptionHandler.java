package com.fang.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fang.response.ResultData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

/**
 * 我们设置的保护规则，如果超出保护规则就会走这个类
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s, BlockException e) throws Exception {
        httpServletResponse.setContentType("application/json;charset=utf-8"); //设置响应编码格式，一定要设置在第一行，不然不生效
        PrintWriter writer = httpServletResponse.getWriter();
        ResultData<String> resultData = new ResultData<>();
        resultData.setCode(String.valueOf(500));
        resultData.setMessage("点击太快了，请稍后再试！");
        writer.write(objectMapper.writeValueAsString(resultData));
        writer.flush(); // 刷新数据
        writer.close(); // 关闭 PrintWriter
    }
}