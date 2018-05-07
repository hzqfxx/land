package com.test.land.landparent.admin.web;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.land.landparent.admin.entity.Ip;
import com.test.land.landparent.admin.service.IpService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-02-26
 */
@Controller
@RequestMapping("/admin/ip")
public class IpController {
    private final String fileName="F://ip.txt";

    @Autowired
    private IpService ipService;


    @GetMapping("/get")
    public void getIp(){

        //读取文件
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"GBK")); //这里可以控制编码
            String line = null;
            while((line = br.readLine()) != null) {
                String[] split = line.split(":");
                Ip ip = new Ip();
                ip.setIp(split[0]);
                ip.setProt(split[1]);
                ipService.insert(ip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @GetMapping("/get2")
    public void getIp2(){
        //过滤失效ip
        List<Ip> availableIP = ipService.getAvailableIP();
        System.out.println(availableIP.size());

    }
}

