package com.test.land.landparent.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-02-26
 */
@TableName("t_customer")
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("customer_name")
	private String customerName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Customer{" +
			"id=" + id +
			", customerName=" + customerName +
			"}";
	}
}
