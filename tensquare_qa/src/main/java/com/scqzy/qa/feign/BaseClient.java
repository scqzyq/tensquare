package com.scqzy.qa.feign;

import com.scqzy.qa.feign.impl.BaseFallbackFactory;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "tensquare-base", fallbackFactory = BaseFallbackFactory.class )
public interface BaseClient {

    @GetMapping("label/{labelId}")
    Result findById(@PathVariable("labelId") String labelId);
}
