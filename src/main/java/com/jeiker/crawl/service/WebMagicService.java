package com.jeiker.crawl.service;

import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

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
                .addUrl("https://mp.weixin.qq.com/s/gTUTjsq5DZ6-cP-f_xTMhQ")
                .addPipeline(new ConsolePipeline())
                .run();
    }
}
