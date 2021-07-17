package com.scqzy.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.scqzy.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * user数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    User findByMobile(String mobile);

    @Modifying
    @Query(value = "update tb_user set fanscount = fanscount + ? where id = ?", nativeQuery = true)
    void updateFanscount(int x, String userid);

    @Modifying
    @Query(value = "update tb_user set followcount = followcount + ? where id = ?", nativeQuery = true)
    void updateFollowcount(int x, String friendid);
}
