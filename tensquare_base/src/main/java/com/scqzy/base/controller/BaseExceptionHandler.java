package com.scqzy.base.controller;

import entity.Result;
import entity.StatusCode;
import exception.NoNecessaryFieldException;
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
        log.error(e.toString());
        if (e instanceof NoNecessaryFieldException) {
            return new Result(false, StatusCode.NO_NECESSARY_FIELD_ERROR, e.getMessage());
        }
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
