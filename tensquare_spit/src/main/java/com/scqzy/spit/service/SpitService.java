package com.scqzy.spit.service;

import com.scqzy.spit.dao.SpitDao;
import com.scqzy.spit.pojo.Spit;
import com.scqzy.entity.PageResult;
import com.scqzy.entity.Result;
import com.scqzy.entity.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.scqzy.util.IdWorker;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/14 19:27
 */
@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    public void save(Spit spit) {
        spit.setId(String.valueOf(idWorker.nextId()));
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if (StringUtils.isNotBlank(spit.getParentid())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    public void update(String spitId, Spit spit) {
        spit.setId(spitId);
        spitDao.save(spit);
    }

    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    public PageResult<Spit> findByParentid(String parentid, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Spit> spits = spitDao.findByParentidOrderByPublishtimeDesc(parentid, pageable);
        return new PageResult<>(spits.getTotalElements(), spits.getContent(), spits.getTotalPages());
    }

    public Result thumbup(String spitid) {
//        Spit spit = spitDao.findById(spitid).get();
//        spit.setThumbup((Objects.isNull(spit.getThumbup()) ? 0 : spit.getThumbup()) + 1);
//        spitDao.save(spit);

        String userid = "111";
        if (Objects.nonNull(redisTemplate.opsForValue().get("thumbup:"+spitid+":"+userid))) {
            return new Result(false, StatusCode.ERROR,"不能重复点赞");
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitid));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
        redisTemplate.opsForValue().set("thumbup:"+spitid+":"+userid,1);
        return new Result("点赞成功");
    }
}
