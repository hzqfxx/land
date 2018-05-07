package com.test.land.landparent.com.test.land.landparent.admin.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoxiang
 * @since 2018-01-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="t_id", type= IdType.AUTO)
	private Long tId;
	@TableField("t_name")
	private String tName;
	@TableField("t_username")
	private String tUsername;
	@TableField("t_password")
	private String tPassword;
	@TableField("t_create_time")
	private Date tCreateTime;
	@TableField("t_lastlogin_time")
	private Date tLastloginTime;


	@Override
	protected Serializable pkVal() {
		return this.tId;
	}

}
