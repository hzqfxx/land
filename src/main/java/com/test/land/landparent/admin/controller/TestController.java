package com.test.land.landparent.admin.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.test.land.landparent.admin.entity.User;
import com.test.land.landparent.admin.service.JobService;
import com.test.land.landparent.admin.service.RedisService;
import com.test.land.landparent.admin.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {

    private final Long REDIS_TIME=3600L;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;
    @Autowired
    private JobService jobService;

    /**
     * 测试redis
     * @return
     */
    @GetMapping(value = "/")
    public String getUser(){
        EntityWrapper<User> ew = new EntityWrapper<>();
        ew.eq("id",1);

        User user = userService.selectOne(ew);
        System.out.println(user);

        redisService.setValue("1",user,REDIS_TIME);
        User objectByKey = (User)redisService.getObjectByKey("1");
        log.info("取出redis成功=================");
        System.out.println(objectByKey.getName());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("123","321");
        return "index.html";

    }
    /**
     * 测试获取bean
     */
    @GetMapping(value = "/q")
    public void getuser2(){
       // jobService.addCar();
    }




}

