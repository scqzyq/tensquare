package com.scqzy.spit.dao;

import com.scqzy.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit, String> {

    Page<Spit> findByParentidOrderByPublishtimeDesc(String parentid, Pageable pageable);
}
