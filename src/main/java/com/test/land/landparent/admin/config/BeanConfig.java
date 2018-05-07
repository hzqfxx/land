package com.test.land.landparent.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.land.landparent.admin.service.CarService;
import com.test.land.landparent.admin.service.impl.CarServiceImpl;

@Configuration
public class BeanConfig {


    @Bean(name = "carService2")
    public CarService getCarService(){
        CarServiceImpl carService = new CarServiceImpl();
        return carService;
    }
}
