package com.family.gold.entity; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
/** 
* CustomerMessage实体层 
* Auther:feng
* Date:2020-09-18 16:32:02
*/ 

@Table(name = "gold_customer_message")
@ApiModel("黄金的业务-客户留言表")
public class CustomerMessage {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="customer_message_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer customerMessageId;

	@Column(name="customer_true_name")
	@ApiModelProperty(value = "姓名")
	private String customerTrueName;

	@Column(name="customer_phone")
	@ApiModelProperty(value = "电话")
	private String customerPhone;

	@Column(name="customer_qq")
	@ApiModelProperty(value = "qq")
	private String customerQq;

	@Column(name="customer_mail")
	@ApiModelProperty(value = "客户邮箱")
	private String customerMail;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@Column(name="customer_remark")
	@ApiModelProperty(value = "备注")
	private String customerRemark;

	@Column(name="org_code")
	@ApiModelProperty(value = "组织id，数据隔离")
	private String orgCode;


	/**
	 *主键
	 */ 
	public void setCustomerMessageId(Integer customerMessageId){
		this.customerMessageId=customerMessageId;
	}
	/**
	 *主键
	 */ 
	public Integer getCustomerMessageId(){
		return customerMessageId;
	}
	/**
	 *姓名
	 */ 
	public void setCustomerTrueName(String customerTrueName){
		this.customerTrueName=customerTrueName;
	}
	/**
	 *姓名
	 */ 
	public String getCustomerTrueName(){
		return customerTrueName;
	}
	/**
	 *电话
	 */ 
	public void setCustomerPhone(String customerPhone){
		this.customerPhone=customerPhone;
	}
	/**
	 *电话
	 */ 
	public String getCustomerPhone(){
		return customerPhone;
	}
	/**
	 *qq
	 */ 
	public void setCustomerQq(String customerQq){
		this.customerQq=customerQq;
	}
	/**
	 *qq
	 */ 
	public String getCustomerQq(){
		return customerQq;
	}
	/**
	 *客户邮箱
	 */ 
	public void setCustomerMail(String customerMail){
		this.customerMail=customerMail;
	}
	/**
	 *客户邮箱
	 */ 
	public String getCustomerMail(){
		return customerMail;
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
	public void setCustomerRemark(String customerRemark){
		this.customerRemark=customerRemark;
	}
	/**
	 *备注
	 */ 
	public String getCustomerRemark(){
		return customerRemark;
	}
	/**
	 *组织id，数据隔离
	 */ 
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	/**
	 *组织id，数据隔离
	 */ 
	public String getOrgCode(){
		return this.orgCode;
	}
}

