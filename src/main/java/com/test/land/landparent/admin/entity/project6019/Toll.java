package com.test.land.landparent.admin.entity.project6019;

import lombok.Data;

/**
 * 费用
 */
@Data
public class Toll {

    private String tollProject;

    private Double price;

    private Double total;

    private String remarks;

    public Toll(String tollProject, Double price, Double total, String remarks) {
        this.tollProject = tollProject;
        this.price = price;
        this.total = total;
        this.remarks = remarks;
    }
}
