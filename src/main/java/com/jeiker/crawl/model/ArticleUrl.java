package com.jeiker.crawl.model;

/**
 * @author : xiao
 * @date : 2018/2/27 下午3:48
 * @description :
 */
public class ArticleUrl {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ArticleUrl{" +
                "url='" + url + '\'' +
                '}';
    }
}
