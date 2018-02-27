package com.jeiker.crawl.controller;

import com.jeiker.crawl.model.Article;
import com.jeiker.crawl.model.ArticleUrl;
import com.jeiker.crawl.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/**
 * @author : xiao
 * @date : 2018/2/27 上午10:50
 * @description :
 */
@RestController
@RequestMapping("/test")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hi")
    public Map<String, String> getBlog() {
        return Collections.singletonMap("message", "hello");
    }

    @PostMapping("/article")
    public Article getArticle(@RequestBody ArticleUrl url) {
        return articleService.getTitle(url.getUrl());
    }
}
