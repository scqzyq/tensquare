package com.scqzy.friend.controller;

import com.scqzy.friend.service.FriendService;
import com.scqzy.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/17 18:14
 */
@RestController
@RequestMapping("friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PutMapping("like/{friendid}/{type}")
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        return friendService.addFriend(friendid,type);
    }

    @DeleteMapping("{friendid}")
    public Result deleteFriend(@PathVariable String friendid) {
        return friendService.deleteFriend(friendid);
    }
}
