package com.scqzy.search.controller;

import com.scqzy.search.pojo.Article;
import com.scqzy.search.service.ArticleService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author 盛春强
 * @Date 2021/7/15 11:04
 */
@RestController
@CrossOrigin
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result("保存成功");
    }

    @GetMapping("/{key}/{page}/{size}")
    public Result findByKey(@PathVariable String key, @PathVariable int page, @PathVariable int size) {
        return new Result(articleService.findByKey(key,page,size));
    }
}
