package com.family.system.entity.DO; 

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
public class UserRoleAD {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户id",example="1")
	private Integer userId;

	@ApiModelProperty(value = "角色id",example="1")
	private Integer roleId;
	@ApiModelProperty(value = "获得的角色ids",example="1")
	private String catchRoleIds;


	
	public String getCatchRoleIds() {
		return catchRoleIds;
	}
	public void setCatchRoleIds(String catchRoleIds) {
		this.catchRoleIds = catchRoleIds;
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

