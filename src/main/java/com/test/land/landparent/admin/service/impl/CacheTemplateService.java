package com.test.land.landparent.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.test.land.landparent.admin.service.CacheLoader;
import com.test.land.landparent.admin.service.RedisService;


/**
 * 查询缓存模板--
 */
@Repository
public class CacheTemplateService {
    @Autowired
    private RedisService redisService;

    public <T>T  findData(String key, int seconds, TypeReference<T> clazz, CacheLoader<T> cacheLoader){
        String json = redisService.getStrValue(key);
        if (json==null||"".equals(json)||"null".equals(json)){
            synchronized (this){
                json = redisService.getStrValue(key);
                if (json!=null&&!"".equals(json)&&!"null".equals(json)){
                    System.out.println("缓存有数据");
                }
                T data = cacheLoader.load();
                //加入缓存
                redisService.setValue(key,JSON.toJSONString(data),60000);
                return data;
            }

        }

        return JSON.parseObject(json, clazz);
    }
}
