package com.family.system.entity.VO; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
/** 
* UserRole实体层 
* Auther:feng
* Date:2020-09-17 15:13:14
*/ 

@ApiModel("用户角色表")
public class UserRoleVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer userRoleId;

	@ApiModelProperty(value = "用户id",example="1")
	private Integer userId;

	@ApiModelProperty(value = "角色id",example="1")
	private Integer roleId;


	/**
	 *主键
	 */ 
	public void setUserRoleId(Integer userRoleId){
		this.userRoleId=userRoleId;
	}
	/**
	 *主键
	 */ 
	public Integer getUserRoleId(){
		return userRoleId;
	}
	/**
	 *用户id
	 */ 
	public void setUserId(Integer userId){
		this.userId=userId;
	}
	/**
	 *用户id
	 */ 
	public Integer getUserId(){
		return userId;
	}
	/**
	 *角色id
	 */ 
	public void setRoleId(Integer roleId){
		this.roleId=roleId;
	}
	/**
	 *角色id
	 */ 
	public Integer getRoleId(){
		return roleId;
	}
}

