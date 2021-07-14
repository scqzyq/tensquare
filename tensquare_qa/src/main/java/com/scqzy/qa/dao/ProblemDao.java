package com.scqzy.qa.dao;

import com.scqzy.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * problem数据访问接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    @Query(value = "select  * from tb_problem tp left join tb_pl tp1 on tp.id = tp1.problemid where tp1.labelid = ? order by tp.replytime" +
            " desc", nativeQuery = true)
    Page<Problem> newList(String labelid, Pageable pageable);

    @Query(value = "select  * from tb_problem tp left join tb_pl tp1 on tp.id = tp1.problemid where tp1.labelid = ? order by tp.reply" +
            " desc", nativeQuery = true)
    Page<Problem> hotList(String labelid, Pageable pageable);

    @Query(value = "select  * from tb_problem tp left join tb_pl tp1 on tp.id = tp1.problemid where tp1.labelid = ? and tp.reply = 0 " +
            "order by tp.createtime desc", nativeQuery = true)
    Page<Problem> waitList(String labelid, Pageable pageable);
}
