package com.test.land.landparent;

import javax.sound.sampled.Line;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
//扫描包
@ComponentScan(basePackages = {"com.test.land.landparent.admin"})
//扫描serlvet
@ServletComponentScan
@Slf4j
//开启定时任务
@EnableScheduling
public class LandParentApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandParentApplication.class, args);
		log.info("启动成功=======================");
	}
}
