package com.jeiker.crawl.service;

import com.jeiker.crawl.model.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author : xiao
 * @date : 2018/2/27 下午1:59
 * @description :
 */
@Service
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);


    public Article getArticle(String url) {

        Article article = new Article();
        try {
            // 解析html
            Document doc = Jsoup.connect(url).get();

            Element titleElement = doc.selectFirst("h2#activity-name");
            String title = "";
            if (titleElement != null) {
                title = titleElement.text();
            }

            Element contentElement = doc.selectFirst("div#js_content");
            String content = "";
            String imageUrl = "";
            if (contentElement != null) {
                content = contentElement.text();

                Element image = contentElement.selectFirst("img");
                if (image != null) {
                    imageUrl = image.attr("data-src");
                }
            }
            String summary = getSummary(content);

            // 填充数据模型
            article.setTitle(title);
            article.setUrl(url);
            article.setImageUrl(imageUrl);
            article.setSummary(summary);
        } catch (IOException e) {
            logger.error("html analysis error: {}", e);
        }
        return article;
    }

    /**
     * 获取文章摘要
     *
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
        Article article = new ArticleService().getArticle("https://mp.weixin.qq.com/s/gTUTjsq5DZ6-cP-f_xTMhQ");
        System.out.print(article.getTitle());
    }
}
