package com.scqzy.friend.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/17 18:33
 */
@Entity
@Table(name = "tb_nofriend")
@Getter
@Setter
@ToString
@IdClass(FriendAndUserId.class)
public class NoFriend implements Serializable {

    @Id
    private String userid;

    @Id
    private String friendid;

    public NoFriend(String userid, String friendid) {
        this.userid = userid;
        this.friendid = friendid;
    }
}
