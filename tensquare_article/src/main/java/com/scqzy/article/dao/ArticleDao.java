package com.scqzy.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.scqzy.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * article数据访问接口
 *
 * @author Administrator
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

    @Modifying
    @Query(value = "update tb_article ta set ta.state = 1 where ta.id = ?", nativeQuery = true)
    void updateState(String id);

    @Modifying
    @Query(value = "update tb_article ta set ta.thumbup = ta.thumbup + 1 where ta.id = ?", nativeQuery = true)
    void addThumbup(String id);
}
