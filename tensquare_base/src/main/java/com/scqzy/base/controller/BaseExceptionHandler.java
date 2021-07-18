package com.scqzy.base.controller;

import com.scqzy.entity.Result;
import com.scqzy.entity.StatusCode;
import com.scqzy.exception.NoNecessaryFieldException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/13 16:28
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {
    @ExceptionHandler
    public Result exception(Exception e) {
        log.error("捕获异常",e);
        if (e instanceof NoNecessaryFieldException) {
            return new Result(false, StatusCode.NO_NECESSARY_FIELD_ERROR, e.getMessage());
        }
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
