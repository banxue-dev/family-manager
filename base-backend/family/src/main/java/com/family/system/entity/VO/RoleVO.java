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
* Role实体层 
* Auther:feng
* Date:2020-09-17 15:13:11
*/ 

@ApiModel("")
public class RoleVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "id",example="1")
	private Integer roleId;

	@ApiModelProperty(value = "名称")
	private String roleName;

	@ApiModelProperty(value = "描述")
	private String roleDesc;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "创建人")
	private String createUser;


	/**
	 *id
	 */ 
	public void setRoleId(Integer roleId){
		this.roleId=roleId;
	}
	/**
	 *id
	 */ 
	public Integer getRoleId(){
		return roleId;
	}
	/**
	 *名称
	 */ 
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}
	/**
	 *名称
	 */ 
	public String getRoleName(){
		return roleName;
	}
	/**
	 *描述
	 */ 
	public void setRoleDesc(String roleDesc){
		this.roleDesc=roleDesc;
	}
	/**
	 *描述
	 */ 
	public String getRoleDesc(){
		return roleDesc;
	}
	/**
	 *创建时间
	 */ 
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}
	/**
	 *创建时间
	 */ 
	public String getCreateTime(){
		  if(StringUtils.isNotNull(createTime)) {if(createTime.contains(".0")) {return createTime.replace(".0", "");}}return createTime;
	}
	/**
	 *创建人
	 */ 
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	/**
	 *创建人
	 */ 
	public String getCreateUser(){
		return createUser;
	}
}

