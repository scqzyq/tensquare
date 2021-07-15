package com.scqzy.search.service;

import com.scqzy.search.dao.ArticleDao;
import com.scqzy.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
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


    public void save(Article article){
        article.setId(String.valueOf(idWorker.nextId()));
        articleDao.save(article);
    }
}
