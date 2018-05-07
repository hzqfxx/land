package com.test.land.landparent.admin.job;

import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 根据作者爬取url
 */
@Component
public class URLReptile implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、重试次数、抓取间隔、超时时间、请求消息头、UA信息等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(500).setTimeOut(10000).setRetrySleepTime(3).setCycleRetryTimes(3);
    public static int count =0;

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return null;
    }

    public static void main(String[] args) {
        long startTime, endTime;
        System.out.println("开始爬取...=====>");
        startTime = System.currentTimeMillis();

        //汽车之家
        Spider.create(new URLReptile()).addUrl().thread(5).run();

        endTime = System.currentTimeMillis();
        System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+ count+"条记录");
    }
}
