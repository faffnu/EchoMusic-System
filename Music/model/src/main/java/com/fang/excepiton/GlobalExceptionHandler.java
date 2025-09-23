package com.fang.excepiton;

import com.fang.response.ResultData;
import com.fang.response.ResultDataEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 处理全局异常
 *
 * @author fang
 * @version 1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 500异常
     *
     * @param ex Exception
     * @return ResultData<String>
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultData<String> exceptionHandler(Exception ex) {
        log.info("global exception 500=>:{}", ex.getMessage());
        return ResultData.fail(ResultDataEnum.RC500.getCode(), ex.getMessage());
    }

    /**
     * 捕获 PathVariable 参数类型不匹配或格式错误的异常，并返回错误信息
     *
     * @param ex MethodArgumentTypeMismatchException
     * @return ResultData<String>
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultData<String> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        log.info("global exception 400=>:{}", ex.getMessage());
        return ResultData.fail(ResultDataEnum.RC400.getCode(), "请求参数有误: " + ex.getMessage());
    }

    /**
     * 404 异常
     *
     * @param ex Exception
     * @return ResultData<String>
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResultData<String> not_found(Exception ex) {
        log.info("global exception 404=>:{}", ex.getMessage());
        return ResultData.fail(ResultDataEnum.RC404.getCode(), ex.getMessage());
    }


    /**
     * 空指针异常
     *
     * @param ex Exception
     * @return ResultData<String>
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultData<String> nullPointer(Exception ex) {
        log.info("global exception not found=>:{}", ex.getMessage());
        return ResultData.fail(ResultDataEnum.RC400.getCode(), ex.getMessage());
    }

    /**
     * 服务器出错异常
     *
     * @param ex Exception
     * @return ResultData<String>
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResultData<String> runtimeException(Exception ex) {
        log.info("global exception RuntimeException=>:{}", ex.getMessage());
        return ResultData.fail(ResultDataEnum.RC50000.getCode(), ex.getMessage());
    }

    /**
     * 剩余异常
     *
     * @param ex Exception
     * @return ResultData<String>
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultData<String> not_error(Exception ex) {
        log.info("global exception Throwable=>:{}", ex.getMessage());
        return ResultData.fail(ResultDataEnum.RC999.getCode(), ex.getMessage());
    }
}
