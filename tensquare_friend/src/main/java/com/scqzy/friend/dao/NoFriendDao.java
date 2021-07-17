package com.scqzy.friend.dao;

import com.scqzy.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoFriendDao extends JpaRepository<NoFriend,String> {
    NoFriend findByUseridAndAndFriendid(String userid, String friendid);
}
