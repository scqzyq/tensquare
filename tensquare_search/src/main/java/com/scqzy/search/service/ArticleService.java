package com.scqzy.search.service;

import com.scqzy.search.dao.ArticleDao;
import com.scqzy.search.pojo.Article;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/15 11:00
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;


    public void save(Article article) {
        article.setId(String.valueOf(idWorker.nextId()));
        articleDao.save(article);
    }

    public PageResult<Article> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> articles = articleDao.findByTitleOrContentLike(key, key, pageable);
        return new PageResult<>(articles.getTotalElements(),articles.getContent(),articles.getTotalPages());
    }
}
