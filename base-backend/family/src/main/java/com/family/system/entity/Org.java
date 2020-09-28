package com.family.system.entity; 

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

@Table(name = "sys_org")
@ApiModel("用户所属组织")
public class Org {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="org_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer orgId;

	@Column(name="org_name")
	@ApiModelProperty(value = "名称")
	private String orgName;

	@Column(name="org_code")
	@ApiModelProperty(value = "组织代码-唯一，用来做数据隔离,自动生成")
	private String orgCode;

	@Column(name="org_desc")
	@ApiModelProperty(value = "描述")
	private String orgDesc;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@Column(name="create_user")
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
		return this.orgCode;
	}
	public String getSourceOrgCode(){
		return this.orgCode;
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

