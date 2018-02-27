package com.jeiker.crawl.service;

import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author : xiao
 * @date : 2018/2/27 下午4:44
 * @description :
 */
@Service
public class WebMagicService implements PageProcessor{

    private Site site = Site.me().setDomain("mp.weixin.qq.com");

    @Override
    public void process(Page page) {

        page.putField("title", page.getHtml().xpath("//h2[@id='activity-name']/text()").toString());
        page.putField("content", page.getHtml().xpath("//div[@id='js_content']/*/allText()").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new WebMagicService())
                .addUrl("https://mp.weixin.qq.com/s?src=11&timestamp=1519723067&ver=724&signature=TXwSeOOehBEOy*kjcAZr5GQGNimYCRluuIBIRlB3DMulssP-MrIPXJERm*OTiUhmAsDUq0CC0u1jA9WojuJWU8iJo21YZmpoLQJNXvA9KgANWhT6jOxSJT0tTp1r2gdW&new=1")
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
