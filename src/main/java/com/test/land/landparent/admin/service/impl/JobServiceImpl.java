package com.test.land.landparent.admin.service.impl;


import static com.test.land.landparent.admin.job.CarReptile.count;

import org.springframework.stereotype.Service;

import com.test.land.landparent.admin.job.CarReptile;
import com.test.land.landparent.admin.service.JobService;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

@Service
public class JobServiceImpl implements JobService {



    @Override
    public void addCar() {
        String BjUrlAll="" +
                "https://www.autohome.com.cn/grade/carhtml/A.html," +
                "https://www.autohome.com.cn/grade/carhtml/B.html," +
                "https://www.autohome.com.cn/grade/carhtml/C.html," +
                "https://www.autohome.com.cn/grade/carhtml/D.html," +
                "https://www.autohome.com.cn/grade/carhtml/E.html," +
                "https://www.autohome.com.cn/grade/carhtml/F.html," +
                "https://www.autohome.com.cn/grade/carhtml/G.html," +
                "https://www.autohome.com.cn/grade/carhtml/H.html," +
                "https://www.autohome.com.cn/grade/carhtml/I.html," +
                "https://www.autohome.com.cn/grade/carhtml/J.html," +
                "https://www.autohome.com.cn/grade/carhtml/K.html," +
                "https://www.autohome.com.cn/grade/carhtml/L.html," +
                "https://www.autohome.com.cn/grade/carhtml/M.html," +
                "https://www.autohome.com.cn/grade/carhtml/N.html," +
                "https://www.autohome.com.cn/grade/carhtml/O.html," +
                "https://www.autohome.com.cn/grade/carhtml/P.html," +
                "https://www.autohome.com.cn/grade/carhtml/Q.html," +
                "https://www.autohome.com.cn/grade/carhtml/R.html," +
                "https://www.autohome.com.cn/grade/carhtml/S.html," +
                "https://www.autohome.com.cn/grade/carhtml/T.html," +
                "https://www.autohome.com.cn/grade/carhtml/V.html," +
                "https://www.autohome.com.cn/grade/carhtml/W.html," +
                "https://www.autohome.com.cn/grade/carhtml/X.html," +
                "https://www.autohome.com.cn/grade/carhtml/Y.html," +
                "https://www.autohome.com.cn/grade/carhtml/Z.html," +
                "";

        String[] urls = BjUrlAll.split(",");
        for (String url : urls) {
            long startTime, endTime;
            System.out.println("开始爬取...=====>"+url);
            startTime = System.currentTimeMillis();

            //汽车之家
            Spider.create(new CarReptile()).addUrl(url).thread(5).run();

            endTime = System.currentTimeMillis();
            System.out.println("爬取结束，耗时约" + ((endTime - startTime) / 1000) + "秒，抓取了"+ count+"条记录");
        }

    }
}
