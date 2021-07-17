package com.scqzy.friend.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/17 18:54
 */
@Data
public class FriendAndUserId implements Serializable {
    private String userid;

    private String friendid;
}
