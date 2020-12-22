package com.family.wb.entity.VO; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* ContactInfo实体层 
* Auther:feng
* Date:2020-12-17 09:05:08
*/ 

@ApiModel("官网联系方式")
public class ContactInfoVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer contactId;

	@ApiModelProperty(value = "联系电话")
	private String contactPhone;

	@ApiModelProperty(value = "联系qq")
	private String contactQq;

	@ApiModelProperty(value = "微信公众号图片")
	private String contactWxPublicImg;

	@ApiModelProperty(value = "公司logo图片")
	private String companyLogo;

	@ApiModelProperty(value = "公司名称")
	private String companyName;

	@ApiModelProperty(value = "公司地址")
	private String companyAddr;

	@ApiModelProperty(value = "公司邮箱")
	private String companyEmail;

	@ApiModelProperty(value = "工作时间")
	private String companyWorkTime;

	@ApiModelProperty(value = "网站备案号")
	private String webRecordCode;

	@ApiModelProperty(value = "网站的标题")
	private String webTitle;

	@ApiModelProperty(value = "组织机构")
	private String orgCode;

	@ApiModelProperty(value = "公司理念")
	private String companyDreams;


	/**
	 *主键
	 */ 
	public void setContactId(Integer contactId){
		this.contactId=contactId;
	}
	/**
	 *主键
	 */ 
	public Integer getContactId(){
		return contactId;
	}
	/**
	 *联系电话
	 */ 
	public void setContactPhone(String contactPhone){
		this.contactPhone=contactPhone;
	}
	/**
	 *联系电话
	 */ 
	public String getContactPhone(){
		return contactPhone;
	}
	/**
	 *联系qq
	 */ 
	public void setContactQq(String contactQq){
		this.contactQq=contactQq;
	}
	/**
	 *联系qq
	 */ 
	public String getContactQq(){
		return contactQq;
	}
	/**
	 *微信公众号图片
	 */ 
	public void setContactWxPublicImg(String contactWxPublicImg){
		this.contactWxPublicImg=contactWxPublicImg;
	}
	/**
	 *微信公众号图片
	 */ 
	public String getContactWxPublicImg(){
		return contactWxPublicImg;
	}
	/**
	 *公司logo图片
	 */ 
	public void setCompanyLogo(String companyLogo){
		this.companyLogo=companyLogo;
	}
	/**
	 *公司logo图片
	 */ 
	public String getCompanyLogo(){
		return companyLogo;
	}
	/**
	 *公司名称
	 */ 
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}
	/**
	 *公司名称
	 */ 
	public String getCompanyName(){
		return companyName;
	}
	/**
	 *公司地址
	 */ 
	public void setCompanyAddr(String companyAddr){
		this.companyAddr=companyAddr;
	}
	/**
	 *公司地址
	 */ 
	public String getCompanyAddr(){
		return companyAddr;
	}
	/**
	 *公司邮箱
	 */ 
	public void setCompanyEmail(String companyEmail){
		this.companyEmail=companyEmail;
	}
	/**
	 *公司邮箱
	 */ 
	public String getCompanyEmail(){
		return companyEmail;
	}
	/**
	 *工作时间
	 */ 
	public void setCompanyWorkTime(String companyWorkTime){
		this.companyWorkTime=companyWorkTime;
	}
	/**
	 *工作时间
	 */ 
	public String getCompanyWorkTime(){
		  if(StringUtils.isNotNull(companyWorkTime)) {if(companyWorkTime.contains(".0")) {return companyWorkTime.replace(".0", "");}}return companyWorkTime;
	}
	/**
	 *网站备案号
	 */ 
	public void setWebRecordCode(String webRecordCode){
		this.webRecordCode=webRecordCode;
	}
	/**
	 *网站备案号
	 */ 
	public String getWebRecordCode(){
		return webRecordCode;
	}
	/**
	 *网站的标题
	 */ 
	public void setWebTitle(String webTitle){
		this.webTitle=webTitle;
	}
	/**
	 *网站的标题
	 */ 
	public String getWebTitle(){
		return webTitle;
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
	/**
	 *公司理念
	 */ 
	public void setCompanyDreams(String companyDreams){
		this.companyDreams=companyDreams;
	}
	/**
	 *公司理念
	 */ 
	public String getCompanyDreams(){
		return companyDreams;
	}
}

