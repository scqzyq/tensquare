package com.scqzy.exception;

/**
 * @Description: 登录权限不足异常类
 * @Author 盛春强
 * @Date 2021/7/13 17:07
 */
public class AuthUnsatisfyException extends RuntimeException {

    public AuthUnsatisfyException() {
        super();
    }

    public AuthUnsatisfyException(String message) {
        super(message);
    }
}
