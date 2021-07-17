package com.scqzy.friend.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
 * @Date 2021/7/17 18:31
 */
@Entity
@Table(name = "tb_friend")
@Getter
@Setter
@ToString
@NoArgsConstructor
@IdClass(FriendAndUserId.class)
public class Friend implements Serializable {

    @Id
    private String userid;

    @Id
    private String friendid;

    private String idlike;

    public Friend(String userid, String friendid, String idlike) {
        this.userid = userid;
        this.friendid = friendid;
        this.idlike = idlike;
    }
}
