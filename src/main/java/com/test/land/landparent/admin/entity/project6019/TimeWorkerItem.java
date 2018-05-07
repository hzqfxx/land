package com.test.land.landparent.admin.entity.project6019;

import lombok.Data;

/**
 * 计时工对账单明细
 */
@Data
public class TimeWorkerItem {

    /**
     *姓名
     */
    private String name;

    /**
     *工号
     */
    private String employee_no;
    /**
     *项目
     */
    private String project_name;
    /**
     *岗位名称
     */
    private String post_name;
    /**
     *供应商
     */
    private String supplier_name;
    /**
     *实际出勤天数（天）
     */
    private Integer days;
    /**
     *实际出勤（小时）
     */
    private Double hours;
    /**
     *金额
     */
    private Double amount;

    public TimeWorkerItem(String name, String employee_no, String project_name, String post_name, String supplier_name, Integer days, Double hours, Double amount) {
        this.name = name;
        this.employee_no = employee_no;
        this.project_name = project_name;
        this.post_name = post_name;
        this.supplier_name = supplier_name;
        this.days = days;
        this.hours = hours;
        this.amount = amount;
    }
}
