package com.scqzy.friend.service;

import com.scqzy.friend.dao.FriendDao;
import com.scqzy.friend.dao.NoFriendDao;
import com.scqzy.friend.feign.UserClient;
import com.scqzy.friend.pojo.Friend;
import com.scqzy.friend.pojo.NoFriend;
import com.scqzy.entity.Result;
import com.scqzy.entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/17 18:17
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Autowired
    private UserClient userClient;

    public Result addFriend(String friendid, String type) {

        Claims claims = (Claims) request.getAttribute("claims_user");
        if (Objects.isNull(claims)) {
            return new Result(false, StatusCode.ERROR, "权限不足");
        }
        String userId = claims.getId();
        if (StringUtils.isNotBlank(type)) {
            if ("1".equals(type)) {
                /*
                已添加单向好友
                 */
                Friend friend = friendDao.findByUseridAndAndFriendid(userId, friendid);
                if (Objects.nonNull(friend)) {
                    return new Result(false, StatusCode.ERROR, "重复添加");
                }
                friendDao.save(new Friend(userId, friendid, "0"));
                /*
                已被对方添加单向好友
                 */
                Friend byFriend = friendDao.findByUseridAndAndFriendid(friendid, userId);
                if (Objects.nonNull(byFriend)) {
                    friendDao.updateIslike("1", userId, friendid);
                    friendDao.updateIslike("1", friendid, userId);
                }
                userClient.updateFanscountAndFollowcount(userId,friendid,1);
                return new Result("添加成功");
            } else if ("2".equals(type)) {
                NoFriend noFriend = noFriendDao.findByUseridAndAndFriendid(userId, friendid);
                if (Objects.nonNull(noFriend)) {
                    return new Result(false, StatusCode.ERROR, "重复添加");
                }
                noFriendDao.save(new NoFriend(userId,friendid));
                return new Result("添加成功");
            }
            return new Result(false, StatusCode.ERROR, "参数异常");
        } else {
            return new Result(false, StatusCode.ERROR, "参数异常");
        }
    }

    public Result deleteFriend(String friendid) {
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (Objects.isNull(claims)) {
            return new Result(false, StatusCode.ERROR, "权限不足");
        }
        String userId = claims.getId();
        friendDao.deleteFriend(userId,friendid);
        friendDao.updateIslike("0",friendid,userId);
        noFriendDao.save(new NoFriend(userId,friendid));
        userClient.updateFanscountAndFollowcount(userId,friendid,-1);
        return new Result("删除成功");
    }
}
