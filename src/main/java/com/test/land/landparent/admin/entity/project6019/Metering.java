package com.test.land.landparent.admin.entity.project6019;

import lombok.Data;

/**
 * 计量
 */
@Data
public class Metering {
    /**
     *姓名
     */
    private String name;
    /**
     *工号
     */
    private String employee_no;
    /**
     *项目id
     */
    private String project_id;
    /**
     *岗位名称
     */
    private String post_name;
    /**
     *供应商id
     */
    private String supplier_id;
    /**
     *计件数据
     */
    private double pieces;
    /**
     *计时数据
     */
    private double hours;
    /**
     *计时转计件数
     */
    private double hours_to_pieces;
    /**
     *合计件数
     */
    private double total_pieces;
    /**
     *金额，单位元
     */
    private double amount;

    public Metering(String name, String employee_no, String project_id, String post_name, String supplier_id, double pieces, double hours, double hours_to_pieces, double total_pieces, double amount) {
        this.name = name;
        this.employee_no = employee_no;
        this.project_id = project_id;
        this.post_name = post_name;
        this.supplier_id = supplier_id;
        this.pieces = pieces;
        this.hours = hours;
        this.hours_to_pieces = hours_to_pieces;
        this.total_pieces = total_pieces;
        this.amount = amount;
    }
}
