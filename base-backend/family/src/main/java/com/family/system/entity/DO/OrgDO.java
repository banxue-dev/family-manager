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
* Org实体层 
* Auther:feng
* Date:2020-09-23 15:01:13
*/ 

@ApiModel("用户所属组织")
public class OrgDO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer orgId;

	@ApiModelProperty(value = "名称")
	private String orgName;

	@ApiModelProperty(value = "组织代码-唯一，用来做数据隔离,自动生成")
	private String orgCode;

	@ApiModelProperty(value = "描述")
	private String orgDesc;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "创建人")
	private String createUser;


	/**
	 *主键
	 */ 
	public void setOrgId(Integer orgId){
		this.orgId=orgId;
	}
	/**
	 *主键
	 */ 
	public Integer getOrgId(){
		return orgId;
	}
	/**
	 *名称
	 */ 
	public void setOrgName(String orgName){
		this.orgName=orgName;
	}
	/**
	 *名称
	 */ 
	public String getOrgName(){
		return orgName;
	}
	/**
	 *组织代码-唯一，用来做数据隔离,自动生成
	 */ 
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	/**
	 *组织代码-唯一，用来做数据隔离,自动生成
	 */ 
	public String getOrgCode(){
		return com.family.utils.OrgCodeGreater.decode(orgCode);
	}
	/**
	 *描述
	 */ 
	public void setOrgDesc(String orgDesc){
		this.orgDesc=orgDesc;
	}
	/**
	 *描述
	 */ 
	public String getOrgDesc(){
		return orgDesc;
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

