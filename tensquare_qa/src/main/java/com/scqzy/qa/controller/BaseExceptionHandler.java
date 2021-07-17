package com.scqzy.qa.controller;

import entity.Result;
import entity.StatusCode;
import exception.AuthUnsatisfyException;
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
        log.error(e.toString());
        if (e instanceof AuthUnsatisfyException) {
            return new Result(false, StatusCode.ACCESS_ERROR, e.getMessage());
        }
        return new Result(false, StatusCode.ERROR, "执行出错");
    }
}
