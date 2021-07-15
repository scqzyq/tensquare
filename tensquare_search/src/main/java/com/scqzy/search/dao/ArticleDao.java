package com.scqzy.search.dao;

import com.scqzy.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/15 10:59
 */
public interface ArticleDao extends ElasticsearchRepository<Article, String> {

    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
