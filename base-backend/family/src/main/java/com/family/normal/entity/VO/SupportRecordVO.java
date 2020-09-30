package com.family.normal.entity.VO; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
/** 
* SupportRecord实体层 
* Auther:feng
* Date:2020-09-29 17:04:10
*/ 

@ApiModel("辅助记录表")
public class SupportRecordVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer supportRecordId;

	@ApiModelProperty(value = "记录名称")
	private String recordName;

	@ApiModelProperty(value = "具体事项")
	private String recordDesc;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "组织机构")
	private String orgCode;


	/**
	 *主键
	 */ 
	public void setSupportRecordId(Integer supportRecordId){
		this.supportRecordId=supportRecordId;
	}
	/**
	 *主键
	 */ 
	public Integer getSupportRecordId(){
		return supportRecordId;
	}
	/**
	 *记录名称
	 */ 
	public void setRecordName(String recordName){
		this.recordName=recordName;
	}
	/**
	 *记录名称
	 */ 
	public String getRecordName(){
		return recordName;
	}
	/**
	 *具体事项
	 */ 
	public void setRecordDesc(String recordDesc){
		this.recordDesc=recordDesc;
	}
	/**
	 *具体事项
	 */ 
	public String getRecordDesc(){
		return recordDesc;
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
	 *组织机构
	 */ 
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	/**
	 *组织机构
	 */ 
	public String getOrgCode(){
		return com.family.utils.OrgCodeGreater.encode(orgCode);
	}
}

