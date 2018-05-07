package com.test.land.landparent.admin.job;



import java.text.SimpleDateFormat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SimpleJob {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "* * * * * ? ")
    public void print(){
           // log.info("执行时间====>"+dateFormat.format(new Date()));
        }

}
