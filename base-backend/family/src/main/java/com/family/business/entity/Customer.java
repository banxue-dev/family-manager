package com.family.business.entity; 

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
* Customer实体层 
* Auther:feng
* Date:2020-09-28 15:54:03
*/ 

@Table(name = "business_customer")
@ApiModel("业务的客户")
public class Customer {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="customer_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer customerId;

	@Column(name="customer_name")
	@ApiModelProperty(value = "名称")
	private String customerName;

	@Column(name="customer_addr")
	@ApiModelProperty(value = "地址")
	private String customerAddr;

	@Column(name="customer_contact_personal")
	@ApiModelProperty(value = "联系人")
	private String customerContactPersonal;

	@Column(name="customer_contact_phone")
	@ApiModelProperty(value = "联系电话")
	private String customerContactPhone;

	@Column(name="customer_mail")
	@ApiModelProperty(value = "邮箱")
	private String customerMail;

	@Column(name="customer_price")
	@ApiModelProperty(value = "成交金额",example="1")
	private BigDecimal customerPrice;

	@Column(name="org_code")
	@ApiModelProperty(value = "组织代码-唯一，用来做数据隔离,自动生成")
	private String orgCode;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@Column(name="customer_desc")
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
		return orgCode;
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

