package com.family.gold.entity.VO; 

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
* UserDiyMetalConfig实体层 
* Auther:feng
* Date:2020-10-22 09:02:06
*/ 

@ApiModel("小心一测试")
public class UserDiyMetalConfigVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Long goldUserDiyMetalConfigId;

	@ApiModelProperty(value = "引用的那个源数据",example="1")
	private Long sourceMetaId;

	@ApiModelProperty(value = "是引用的源数据价格的%几，eg:0.88",example="1")
	private BigDecimal upDownRate;

	@ApiModelProperty(value = "新名称，默认是引用的源数据名称")
	private String newName;

	@ApiModelProperty(value = "组织机构")
	private String orgCode;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "小数点后几位",example="1")
	private Integer constraintLen;

	@ApiModelProperty(value = "是那个组的",example="1")
	private Integer groupId;
	
	private String metalCode;
	private String metalName;

	@ApiModelProperty(value = "回购调价",example="1")
	private BigDecimal buyBackWater;

	@ApiModelProperty(value = "销售调价",example="1")
	private BigDecimal saleWater;
	
	@ApiModelProperty(value = "是那个组的pc端",example="1")
	private Integer pcGroupId;
	
	
	public BigDecimal getBuyBackWater() {
		return buyBackWater;
	}
	public void setBuyBackWater(BigDecimal buyBackWater) {
		this.buyBackWater = buyBackWater;
	}
	public BigDecimal getSaleWater() {
		return saleWater;
	}
	public void setSaleWater(BigDecimal saleWater) {
		this.saleWater = saleWater;
	}
	public String getMetalCode() {
		return metalCode;
	}
	public void setMetalCode(String metalCode) {
		this.metalCode = metalCode;
	}
	public String getMetalName() {
		return metalName;
	}
	public void setMetalName(String metalName) {
		this.metalName = metalName;
	}
	/**
	 *主键
	 */ 
	public void setGoldUserDiyMetalConfigId(Long goldUserDiyMetalConfigId){
		this.goldUserDiyMetalConfigId=goldUserDiyMetalConfigId;
	}
	
	public Integer getPcGroupId() {
		return pcGroupId;
	}
	public void setPcGroupId(Integer pcGroupId) {
		this.pcGroupId = pcGroupId;
	}
	/**
	 *主键
	 */ 
	public Long getGoldUserDiyMetalConfigId(){
		return goldUserDiyMetalConfigId;
	}
	/**
	 *引用的那个源数据
	 */ 
	public void setSourceMetaId(Long sourceMetaId){
		this.sourceMetaId=sourceMetaId;
	}
	/**
	 *引用的那个源数据
	 */ 
	public Long getSourceMetaId(){
		return sourceMetaId;
	}
	/**
	 *是引用的源数据价格的%几，eg:0.88
	 */ 
	public void setUpDownRate(BigDecimal upDownRate){
		this.upDownRate=upDownRate;
	}
	/**
	 *是引用的源数据价格的%几，eg:0.88
	 */ 
	public BigDecimal getUpDownRate(){
		if(this.upDownRate==null) {
			return BigDecimal.ONE;
		}else if(this.upDownRate.compareTo(BigDecimal.ZERO)==-1) {
			return BigDecimal.ONE;
		}
		
		return upDownRate;
	}
	/**
	 *新名称，默认是引用的源数据名称
	 */ 
	public void setNewName(String newName){
		this.newName=newName;
	}
	/**
	 *新名称，默认是引用的源数据名称
	 */ 
	public String getNewName(){
		return newName;
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
	 *小数点后几位
	 */ 
	public void setConstraintLen(Integer constraintLen){
		this.constraintLen=constraintLen;
	}
	/**
	 *小数点后几位
	 */ 
	public Integer getConstraintLen(){
		return constraintLen;
	}
	/**
	 *是那个组的
	 */ 
	public void setGroupId(Integer groupId){
		this.groupId=groupId;
	}
	/**
	 *是那个组的
	 */ 
	public Integer getGroupId(){
		return groupId;
	}
}

