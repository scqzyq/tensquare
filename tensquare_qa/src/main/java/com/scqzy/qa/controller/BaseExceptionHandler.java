package com.scqzy.qa.controller;

import com.scqzy.entity.Result;
import com.scqzy.entity.StatusCode;
import com.scqzy.exception.AuthUnsatisfyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 统一异常处理类
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error("捕获异常",e);
        if (e instanceof AuthUnsatisfyException) {
            return new Result(false, StatusCode.ACCESS_ERROR, e.getMessage());
        }
        return new Result(false, StatusCode.ERROR, "执行出错");
    }
}
