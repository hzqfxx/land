package com.test.land.landparent.admin.entity.project6019;

import lombok.Data;

/**
 * 罚款
 */
@Data
public class Fine {
    //姓名
    private String name;
    //工号
    private String employee_no;
    //项目
    private String project;
    //岗位/部门
    private String post;
    //供应商
    private String supplier;
    //金额
    private Double amount;
    //原因
    private String reason;

    public Fine(String name, String employee_no, String project, String post, String supplier, Double amount, String reason) {
        this.name = name;
        this.employee_no = employee_no;
        this.project = project;
        this.post = post;
        this.supplier = supplier;
        this.amount = amount;
        this.reason = reason;
    }
}
