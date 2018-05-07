package com.test.land.landparent.admin.entity.project6019;

import java.util.Date;

import lombok.Data;

/**
 * 临时工
 */
@Data
public class Temporary {
    //姓名
    private String name;

    //时间x月x日
    private Date day;

    //工作时间
    private Double workTime;


    //单价
    private Double price;

    public Temporary(String name, Date day, Double workTime, Double price) {
        this.name = name;
        this.day = day;
        this.workTime = workTime;
        this.price = price;
    }
}
