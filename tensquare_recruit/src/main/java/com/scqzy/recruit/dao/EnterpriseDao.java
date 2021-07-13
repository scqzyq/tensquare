package com.scqzy.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.scqzy.recruit.pojo.Enterprise;

import java.util.List;

/**
 * enterprise数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{
	List<Enterprise> findByIshot(String ishot);
}
