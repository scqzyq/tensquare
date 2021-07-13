package com.scqzy.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.scqzy.article.pojo.Enterprise;
/**
 * enterprise数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{
	
}
