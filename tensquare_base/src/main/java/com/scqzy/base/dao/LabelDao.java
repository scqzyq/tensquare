package com.scqzy.base.dao;

import com.scqzy.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
}
