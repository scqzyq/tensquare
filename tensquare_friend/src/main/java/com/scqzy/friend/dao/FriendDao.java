package com.scqzy.friend.dao;

import com.scqzy.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {

    Friend findByUseridAndAndFriendid(String userid, String friendid);

    @Modifying
    @Query(value = "update tb_friend set islike = ?1 where userid = ?2 and friendid = ?3",nativeQuery = true)
    void updateIslike(String islike, String userid, String friendid);

    @Modifying
    @Query("delete from Friend f where f.userid=?1 and f.friendid=?2")
    void deleteFriend(String userId, String friendid);
}
