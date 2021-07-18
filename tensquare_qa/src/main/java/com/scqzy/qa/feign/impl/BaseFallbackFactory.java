package com.scqzy.qa.feign.impl;

import com.scqzy.qa.feign.BaseClient;
import com.scqzy.entity.Result;
import com.scqzy.entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/17 20:29
 */
@Slf4j
@Component
public class BaseFallbackFactory implements FallbackFactory<BaseClient> {

    @Override
    public BaseClient create(Throwable cause) {
        return new BaseClient() {
            @Override
            public Result findById(String labelId) {
                log.error("调用异常捕获",cause);
                return new Result(false, StatusCode.ERROR,"调用报错");
            }
        };
    }
}
