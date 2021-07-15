package com.scqzy.search.dao;

import com.scqzy.search.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/15 10:59
 */
public interface ArticleDao extends ElasticsearchRepository<Article, String> {


}
