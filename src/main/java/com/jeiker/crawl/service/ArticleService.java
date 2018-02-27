package com.jeiker.crawl.service;

import com.jeiker.crawl.model.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author : xiao
 * @date : 2018/2/27 下午1:59
 * @description :
 */
@Service
public class ArticleService {


    public Article getArticle(String url) {

        Article article = new Article();
        try {
            // 解析html
            Document doc = Jsoup.connect(url).get();
            Element titleElement = doc.select("h2#activity-name").first();
            String title = titleElement.text();
            Element contentElement = doc.selectFirst("div#js_content");
            Element image = contentElement.selectFirst("img");
            String imageUrl = image.attr("data-src");
            String summary = getSummary(contentElement.text());
            // 填充数据模型
            article.setTitle(title);
            article.setUrl(url);
            article.setImageUrl(imageUrl);
            article.setSummary(summary);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 获取文章摘要
     * @param content
     * @return
     */
    private String getSummary(String content) {

        String summary = null;
        if (content == null) {
            summary = "";
        } else if (content.length() <= 30) {
            summary = content;
        } else {
            summary = content.substring(0, 30);
            summary += "...";
        }
        return summary;
    }

    public static void main(String[] args) {
        Article article = new ArticleService().getArticle("https://mp.weixin.qq.com/s?src=3&timestamp=1519715236&ver=1&signature=63P0vex38iAL8VU5A*-eCiczaINMvykUFFV9Ks4lY0hRi45uYOYS9sNU0hUvNf2zEaYbi34Lkrk1uUq0845B-jtu-3p*kAFCbTOX3sD802YAD8TMfWkf-JcWautXMsq8CnHGz5Qt*NKAtCuDuivaCw==");
        System.out.print(article.getTitle());
    }
}
