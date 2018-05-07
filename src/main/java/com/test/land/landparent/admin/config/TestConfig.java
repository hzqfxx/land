package com.test.land.landparent.admin.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class TestConfig {
    @PostConstruct
    public void Test(){
        System.out.println("=================Test装载完成执行xx逻辑=================");
    }
}
