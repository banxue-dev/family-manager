package com.family.gold.entity.DO; 

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
* PersonalInfo实体层 
* Auther:feng
* Date:2020-09-28 15:37:24
*/ 

@ApiModel("业务下的客户")
public class PersonalInfoDO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer customerId;

	@ApiModelProperty(value = "名称")
	private String customerName;

	@ApiModelProperty(value = "组织代码-唯一，用来做数据隔离,自动生成")
	private String orgCode;

	@ApiModelProperty(value = "描述")
	private String customerDesc;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "创建人")
	private String createUser;

	@ApiModelProperty(value = "地址")
	private String customerAddr;

	@ApiModelProperty(value = "联系人")
	private String customerContactPersonal;

	@ApiModelProperty(value = "联系电话")
	private String customerContactPhone;

	@ApiModelProperty(value = "地址图片")
	private String customerAddrImg;

	@ApiModelProperty(value = "邮编")
	private String customerMailCode;

	@ApiModelProperty(value = "组织的logo")
	private String customerLogo;
	@ApiModelProperty(value="默认的分列字段颜色---回购和销售的")
	private String defaultTextColor;
	@ApiModelProperty(value="边框样式")
	private String cellBorder;
	@ApiModelProperty(value="模板开始")
	private String tmpStartHtml;
	@ApiModelProperty(value="模板中列")
	private String tmpCellHtml;
	@ApiModelProperty(value="模板结束")
	private String tmpEndHtml;
	@ApiModelProperty(value="模板标题头")
	private String tmpHeadHtml;
	@ApiModelProperty(value="模板调价题头")
	private String tmpChangeHtml;
	
	@ApiModelProperty(value="pc的json")
	private String pcHtmlJson;
	
	

	
	
	public String getPcHtmlJson() {
		return pcHtmlJson;
	}
	public void setPcHtmlJson(String pcHtmlJson) {
		this.pcHtmlJson = pcHtmlJson;
	}

	
	
	public String getTmpChangeHtml() {
		return tmpChangeHtml;
	}
	public void setTmpChangeHtml(String tmpChangeHtml) {
		this.tmpChangeHtml = tmpChangeHtml;
	}
	public String getTmpStartHtml() {
		return tmpStartHtml;
	}
	public void setTmpStartHtml(String tmpStartHtml) {
		this.tmpStartHtml = tmpStartHtml;
	}
	public String getTmpCellHtml() {
		return tmpCellHtml;
	}
	public void setTmpCellHtml(String tmpCellHtml) {
		this.tmpCellHtml = tmpCellHtml;
	}
	public String getTmpEndHtml() {
		return tmpEndHtml;
	}
	public void setTmpEndHtml(String tmpEndHtml) {
		this.tmpEndHtml = tmpEndHtml;
	}
	public String getTmpHeadHtml() {
		return tmpHeadHtml;
	}
	public void setTmpHeadHtml(String tmpHeadHtml) {
		this.tmpHeadHtml = tmpHeadHtml;
	}
	public String getCellBorder() {
		return cellBorder;
	}
	public void setCellBorder(String cellBorder) {
		this.cellBorder = cellBorder;
	}
	
	public String getDefaultTextColor() {
		return defaultTextColor;
	}
	public void setDefaultTextColor(String defaultTextColor) {
		this.defaultTextColor = defaultTextColor;
	}

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
	public String getSourceOrgCode(){
		return this.orgCode;
	}
	/**
	 *描述
	 */ 
	public void setCustomerDesc(String customerDesc){
		this.customerDesc=customerDesc;
	}
	/**
	 *描述
	 */ 
	public String getCustomerDesc(){
		return customerDesc;
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
	 *地址图片
	 */ 
	public void setCustomerAddrImg(String customerAddrImg){
		this.customerAddrImg=customerAddrImg;
	}
	/**
	 *地址图片
	 */ 
	public String getCustomerAddrImg(){
		return customerAddrImg;
	}
	/**
	 *邮编
	 */ 
	public void setCustomerMailCode(String customerMailCode){
		this.customerMailCode=customerMailCode;
	}
	/**
	 *邮编
	 */ 
	public String getCustomerMailCode(){
		return customerMailCode;
	}
	/**
	 *组织的logo
	 */ 
	public void setCustomerLogo(String customerLogo){
		this.customerLogo=customerLogo;
	}
	/**
	 *组织的logo
	 */ 
	public String getCustomerLogo(){
		return customerLogo;
	}
}

