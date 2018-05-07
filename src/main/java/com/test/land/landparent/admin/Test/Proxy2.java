package com.test.land.landparent.admin.Test;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.web.client.RestTemplate;


public class Proxy2 {
    public static void main(String[] args) throws IOException {

        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Integer> ints = new ArrayList<>();
        ArrayList<String> strings1 = new ArrayList<>();
        strings.add("78.160.160.132");
        strings.add("172.247.251.63");
        strings.add("58.97.81.11");
        strings.add("80.211.169.36");
        ints.add(80);
        ints.add(80);
        ints.add(80);
        ints.add(80);
        strings1.add("https://www.hackhome.com/InfoView/Article_394692.html");
        strings1.add("https://www.hackhome.com/InfoView/Article_444663.html");
        strings1.add("https://www.hackhome.com/InfoView/Article_427259.html");
        strings1.add("https://www.hackhome.com/InfoView/Article_444744.html");
        for (int i = 0; i < strings.size(); i++) {

            try {
                String ip = strings.get(i);
                int prot = ints.get(i);
                String url = strings1.get(i);
                RestTemplate restTemplate = CilkThread.getRestTemplate(ip, prot);
                String forObject = restTemplate.getForObject(url, String.class);

                System.out.println("ip"+ip+"发送成功");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
