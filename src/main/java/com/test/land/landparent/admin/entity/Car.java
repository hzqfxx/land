package com.test.land.landparent.admin.entity;

import java.io.Serializable;

import java.util.Date;
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
@TableName("t_car")
public class Car extends Model<Car> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 汽车品牌
     */
	@TableField("car_brand")
	private String carBrand;
    /**
     * 汽车型号(名称)
     */
	@TableField("car_name")
	private String carName;
    /**
     * 汽车类型-如suv...
     */
	@TableField("car_type")
	private String carType;
    /**
     * 新车指导价 单位:万元  用-隔开
     */
	@TableField("car_new_price")
	private String carNewPrice;
    /**
     * 车商城报价 单位:万元  用-隔开
     */
	@TableField("car_mall_price")
	private String carMallPrice;
    /**
     * 二手车报价 单位:万元  用-隔开
     */
	@TableField("car_old_price")
	private String carOldPrice;
    /**
     * 口碑 满分5分
     */
	@TableField("car_evaluation")
	private Double carEvaluation;
    /**
     * 信息对应的城市
     */
	@TableField("car_city_id")
	private Long carCityId;
    /**
     * 城市名称
     */
	@TableField("car_city_name")
	private String carCityName;
    /**
     * 创建时间
     */
	@TableField("car_createtime")
	private Date carCreatetime;
    /**
     * 来源url
     */
	@TableField("car_url")
	private String carUrl;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarNewPrice() {
		return carNewPrice;
	}

	public void setCarNewPrice(String carNewPrice) {
		this.carNewPrice = carNewPrice;
	}

	public String getCarMallPrice() {
		return carMallPrice;
	}

	public void setCarMallPrice(String carMallPrice) {
		this.carMallPrice = carMallPrice;
	}

	public String getCarOldPrice() {
		return carOldPrice;
	}

	public void setCarOldPrice(String carOldPrice) {
		this.carOldPrice = carOldPrice;
	}

	public Double getCarEvaluation() {
		return carEvaluation;
	}

	public void setCarEvaluation(Double carEvaluation) {
		this.carEvaluation = carEvaluation;
	}

	public Long getCarCityId() {
		return carCityId;
	}

	public void setCarCityId(Long carCityId) {
		this.carCityId = carCityId;
	}

	public String getCarCityName() {
		return carCityName;
	}

	public void setCarCityName(String carCityName) {
		this.carCityName = carCityName;
	}

	public Date getCarCreatetime() {
		return carCreatetime;
	}

	public void setCarCreatetime(Date carCreatetime) {
		this.carCreatetime = carCreatetime;
	}

	public String getCarUrl() {
		return carUrl;
	}

	public void setCarUrl(String carUrl) {
		this.carUrl = carUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Car{" +
			"id=" + id +
			", carBrand=" + carBrand +
			", carName=" + carName +
			", carType=" + carType +
			", carNewPrice=" + carNewPrice +
			", carMallPrice=" + carMallPrice +
			", carOldPrice=" + carOldPrice +
			", carEvaluation=" + carEvaluation +
			", carCityId=" + carCityId +
			", carCityName=" + carCityName +
			", carCreatetime=" + carCreatetime +
			", carUrl=" + carUrl +
			"}";
	}
}
