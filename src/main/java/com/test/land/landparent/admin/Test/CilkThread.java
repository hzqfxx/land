package com.test.land.landparent.admin.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.test.land.landparent.admin.entity.Ip;
import com.test.land.landparent.admin.entity.Url;
import com.test.land.landparent.admin.service.IpService;
import com.test.land.landparent.admin.service.UrlService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CilkThread implements Runnable {

    @Autowired
    private UrlService urlService;

    @Autowired
    private IpService ipService;

    @Override
    public void run() {

        System.out.println("jinlai le ");
        //获取所有ip
        List<Ip> availableIP = ipService.getAvailableIP();

        //获取所有链接
        EntityWrapper<Url> urlEntityWrapper = new EntityWrapper<>();
        urlEntityWrapper.eq("author","dxq");
        List<Url> url = urlService.selectList(urlEntityWrapper);

        if (Thread.currentThread().getName().equals("t1")){
            click(url, availableIP,1,availableIP.size()/4);
        }
        if (Thread.currentThread().getName().equals("t2")){
            click(url,availableIP,availableIP.size()/4,availableIP.size()/2);
        }
        if (Thread.currentThread().getName().equals("t3")){
            click(url,availableIP,availableIP.size()/2,availableIP.size()/3);
        }
        if (Thread.currentThread().getName().equals("t4")) {
            click(url,availableIP,availableIP.size()/3,availableIP.size());
        }

    }

    private void click(List<Url> url, List<Ip> availableIP,int min,int max) {
        for (int i = 0; i < 1500; i++) {

            //间隔时间
            int randomNumberInRange = getRandomNumberInRange(2000, 10000);
            //文章
            int click = getRandomNumberInRange(1, 720);
            int ip = getRandomNumberInRange(min, max);

            Ip ip1 = availableIP.get(ip);
            String ip2 = ip1.getIp();
            int prot = Integer.parseInt(ip1.getProt());

            Url url1 = url.get(click);
            String url2 = url1.getUrl();
            //发送请求
            RestTemplate restTemplate = getRestTemplate(ip2, prot);
            try {
            restTemplate.getForObject(url2, String.class);
            System.out.println(Thread.currentThread().getName() + "线程===>IP=" + ip2+":"+prot+ "访问地址:" + url.get(click).getUrl());


                Thread.sleep(randomNumberInRange);
            } catch (Exception e) {
                log.info("==================>bug:"+e.toString());
            }
        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();

    }

    public static RestTemplate getRestTemplate(String host, int prot) {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();

        SocketAddress address = new InetSocketAddress(host,prot);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
        httpRequestFactory.setProxy(proxy);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        return restTemplate;
    }
}
