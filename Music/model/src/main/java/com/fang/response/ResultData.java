package com.fang.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fang.entity.dto.LikeMusicDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL) //如果属性值不为空序列化
    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> result = new ResultData<T>();
        result.setCode(ResultDataEnum.RC200.getCode());
        result.setMessage(ResultDataEnum.RC200.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> ResultData<T> success(String code, String message, T data) {
        ResultData<T> result = new ResultData<T>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> ResultData<T> success(String code, String message) {
        return fail(code, message);
    }

    public static <T> ResultData<T> fail(String code, String message) {
        ResultData<T> resultData = new ResultData<T>();
        resultData.setCode(code);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T> ResultData<T> fail(String code, String message, T data) {
        ResultData<T> resultData = new ResultData<T>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(String code, String message, String data) {
        ResultData<T> resultData = new ResultData<T>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData((T) data);
        return resultData;
    }


    public static ResultData<List<LikeMusicDTO>> fail(String text) {
        ResultData<List<LikeMusicDTO>> resultData = new ResultData<List<LikeMusicDTO>>();
        resultData.setCode(ResultDataEnum.RC500.getCode());
        resultData.setMessage(text);
        return resultData;
    }
}
