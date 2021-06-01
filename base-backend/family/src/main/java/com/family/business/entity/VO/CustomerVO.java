package com.family.business.entity.VO; 

import java.math.BigDecimal;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* Customer实体层 
* Auther:feng
* Date:2020-09-28 15:54:03
*/ 

@ApiModel("业务的客户")
public class CustomerVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer customerId;

	@ApiModelProperty(value = "名称")
	private String customerName;

	@ApiModelProperty(value = "地址")
	private String customerAddr;

	@ApiModelProperty(value = "联系人")
	private String customerContactPersonal;

	@ApiModelProperty(value = "联系电话")
	private String customerContactPhone;

	@ApiModelProperty(value = "邮箱")
	private String customerMail;

	@ApiModelProperty(value = "有效期止")
	private String customerValidate;
	@ApiModelProperty(value = "成交金额",example="1")
	private BigDecimal customerPrice;

	@ApiModelProperty(value = "组织代码-唯一，用来做数据隔离,自动生成")
	private String orgCode;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "备注")
	private String customerDesc;


	/**
	 *主键
	 */ 
	public void setCustomerId(Integer customerId){
		this.customerId=customerId;
	}
	/**
	 *主键
	 */ 
	public Integer getCustomerId(){
		return customerId;
	}
	
	public String getCustomerValidate() {
		return customerValidate;
	}
	public void setCustomerValidate(String customerValidate) {
		this.customerValidate = customerValidate;
	}
	/**
	 *名称
	 */ 
	public void setCustomerName(String customerName){
		this.customerName=customerName;
	}
	/**
	 *名称
	 */ 
	public String getCustomerName(){
		return customerName;
	}
	/**
	 *地址
	 */ 
	public void setCustomerAddr(String customerAddr){
		this.customerAddr=customerAddr;
	}
	/**
	 *地址
	 */ 
	public String getCustomerAddr(){
		return customerAddr;
	}
	/**
	 *联系人
	 */ 
	public void setCustomerContactPersonal(String customerContactPersonal){
		this.customerContactPersonal=customerContactPersonal;
	}
	/**
	 *联系人
	 */ 
	public String getCustomerContactPersonal(){
		return customerContactPersonal;
	}
	/**
	 *联系电话
	 */ 
	public void setCustomerContactPhone(String customerContactPhone){
		this.customerContactPhone=customerContactPhone;
	}
	/**
	 *联系电话
	 */ 
	public String getCustomerContactPhone(){
		return customerContactPhone;
	}
	/**
	 *邮箱
	 */ 
	public void setCustomerMail(String customerMail){
		this.customerMail=customerMail;
	}
	/**
	 *邮箱
	 */ 
	public String getCustomerMail(){
		return customerMail;
	}
	/**
	 *成交金额
	 */ 
	public void setCustomerPrice(BigDecimal customerPrice){
		this.customerPrice=customerPrice;
	}
	/**
	 *成交金额
	 */ 
	public BigDecimal getCustomerPrice(){
		return customerPrice;
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
		return com.family.utils.OrgCodeGreater.encode(orgCode);
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
	 *备注
	 */ 
	public void setCustomerDesc(String customerDesc){
		this.customerDesc=customerDesc;
	}
	/**
	 *备注
	 */ 
	public String getCustomerDesc(){
		return customerDesc;
	}
}

