package com.test.land.landparent.admin.web;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.test.land.landparent.admin.Test.CilkThread;
import com.test.land.landparent.admin.entity.Url;
import com.test.land.landparent.admin.service.IpService;
import com.test.land.landparent.admin.service.UrlService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-02-26
 */
@Controller
@RequestMapping("/admin/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private IpService ipService;

    @Autowired
    private  CilkThread cilkThread;

    @GetMapping("/url")
    public void getUrl() throws UnsupportedEncodingException {
        ArrayList<String> strings = new ArrayList<>();
        for (int j = 0; j < 75; j++) {
            RestTemplate restTemplate = new RestTemplate();
            String forObject = restTemplate.getForObject("http://s.hackhome.com/cse/search?q=ljx&p="+j+"&s=4128018865199176543&entry=1", String.class);
            String gbk = new String(forObject.getBytes("iso-8859-1"), "utf-8");
            String[] split2 = gbk.split(" <em>dxq</em>");

            if (StringUtils.isNotBlank(split2[0])){
                String[] split = gbk.split("<a rpos=\"\" cpos=\"title\" href=\"");
                for (int i = 1; i < split.length; i++) {
                    String s = split[i];
                    String[] split1 = s.split("\"");
                    strings.add(split1[0]);
                }
            }
            continue;
        }

        for (String string : strings) {
            Url url = new Url();
            url.setUrl(string);
            url.setAuthor("dxq");
            urlService.insert(url);
        }

    }

    @GetMapping("/click")
    public void brushClick(){

        Thread t1 = new Thread(cilkThread, "t1");
        Thread t2 = new Thread(cilkThread, "t2");
        Thread t3 = new Thread(cilkThread, "t3");
        Thread t4 = new Thread(cilkThread, "t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();


    }

}

