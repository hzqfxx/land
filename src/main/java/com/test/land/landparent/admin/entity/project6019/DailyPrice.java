package com.test.land.landparent.admin.entity.project6019;

import java.util.Date;

import lombok.Data;

/**
 * 每日价格统计
 */
@Data
public class DailyPrice {

    /**
     * 统计时间
     */
    private Date date;

    /**
     *每日总价
     */
    private Double price;

    public DailyPrice(Date date, Double price) {
        this.date = date;
        this.price = price;
    }
}
