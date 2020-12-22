package com.family.wb.entity.VO; 

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
* LeavingMessageInfo实体层 
* Auther:feng
* Date:2020-12-21 11:22:18
*/ 

@ApiModel("官网的留言")
public class LeavingMessageInfoVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer leavingMessageId;

	@ApiModelProperty(value = "客户名称")
	private String customerName;

	@ApiModelProperty(value = "客户电话")
	private String customerPhone;

	@ApiModelProperty(value = "客户邮箱")
	private String customerEmail;

	@ApiModelProperty(value = "留言内容")
	private String customerContent;

	@ApiModelProperty(value = "orgCode")
	private String orgCode;

	@ApiModelProperty(value = "时间")
	private String createTime;


	/**
	 *主键
	 */ 
	public void setLeavingMessageId(Integer leavingMessageId){
		this.leavingMessageId=leavingMessageId;
	}
	/**
	 *主键
	 */ 
	public Integer getLeavingMessageId(){
		return leavingMessageId;
	}
	/**
	 *客户名称
	 */ 
	public void setCustomerName(String customerName){
		this.customerName=customerName;
	}
	/**
	 *客户名称
	 */ 
	public String getCustomerName(){
		return customerName;
	}
	/**
	 *客户电话
	 */ 
	public void setCustomerPhone(String customerPhone){
		this.customerPhone=customerPhone;
	}
	/**
	 *客户电话
	 */ 
	public String getCustomerPhone(){
		return customerPhone;
	}
	/**
	 *客户邮箱
	 */ 
	public void setCustomerEmail(String customerEmail){
		this.customerEmail=customerEmail;
	}
	/**
	 *客户邮箱
	 */ 
	public String getCustomerEmail(){
		return customerEmail;
	}
	/**
	 *留言内容
	 */ 
	public void setCustomerContent(String customerContent){
		this.customerContent=customerContent;
	}
	/**
	 *留言内容
	 */ 
	public String getCustomerContent(){
		return customerContent;
	}
	/**
	 *orgCode
	 */ 
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	/**
	 *orgCode
	 */ 
	public String getOrgCode(){
		return com.family.utils.OrgCodeGreater.encode(orgCode);
	}
	/**
	 *时间
	 */ 
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}
	/**
	 *时间
	 */ 
	public String getCreateTime(){
		  if(StringUtils.isNotNull(createTime)) {if(createTime.contains(".0")) {return createTime.replace(".0", "");}}return createTime;
	}
}

