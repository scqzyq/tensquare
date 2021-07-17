package com.scqzy.friend.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/17 19:18
 */
@FeignClient(value = "tensquare-user")
public interface UserClient {

    @PutMapping("{userid}/{friendid}/{x}")
    void updateFanscountAndFollowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid,
                                       @PathVariable("x") int x);
}
