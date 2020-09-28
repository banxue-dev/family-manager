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
* RoleMenu实体层 
* Auther:feng
* Date:2020-09-17 15:13:12
*/ 

@ApiModel("角色菜单关联表")
public class RoleMenuVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer roleMenuId;

	@ApiModelProperty(value = "角色id",example="1")
	private Integer roleId;

	@ApiModelProperty(value = "菜单id",example="1")
	private Integer menuId;


	/**
	 *主键
	 */ 
	public void setRoleMenuId(Integer roleMenuId){
		this.roleMenuId=roleMenuId;
	}
	/**
	 *主键
	 */ 
	public Integer getRoleMenuId(){
		return roleMenuId;
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
	/**
	 *菜单id
	 */ 
	public void setMenuId(Integer menuId){
		this.menuId=menuId;
	}
	/**
	 *菜单id
	 */ 
	public Integer getMenuId(){
		return menuId;
	}
}

