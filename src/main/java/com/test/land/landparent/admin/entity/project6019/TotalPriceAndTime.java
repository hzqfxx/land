package com.test.land.landparent.admin.entity.project6019;

import lombok.Data;

/**
 * 临时工合计和金额
 */
@Data
public class TotalPriceAndTime {

    //姓名
    private String name;

    //合计工时
    private Double totalTime;

    //合计金额
    private Double totalPrice;

    public TotalPriceAndTime(String name, Double totalTime, Double totalPrice) {
        this.name = name;
        this.totalTime = totalTime;
        this.totalPrice = totalPrice;
    }
}
