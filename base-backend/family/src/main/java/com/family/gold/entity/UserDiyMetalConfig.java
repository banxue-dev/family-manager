package com.family.gold.entity; 

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
* Date:2020-10-22 09:49:03
*/ 

@Table(name = "gold_user_diy_metal_config")
@ApiModel("小心一测试")
public class UserDiyMetalConfig {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="gold_user_diy_metal_config_id")
	@ApiModelProperty(value = "主键",example="1")
	private Long goldUserDiyMetalConfigId;

	@Column(name="source_meta_id")
	@ApiModelProperty(value = "引用的那个源数据",example="1")
	private Long sourceMetaId;

	@Column(name="up_down_rate")
	@ApiModelProperty(value = "是引用的源数据价格的%几，eg:0.88",example="1")
	private BigDecimal upDownRate;

	@Column(name="new_name")
	@ApiModelProperty(value = "新名称，默认是引用的源数据名称")
	private String newName;

	@Column(name="org_code")
	@ApiModelProperty(value = "组织机构")
	private String orgCode;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@Column(name="constraint_len")
	@ApiModelProperty(value = "小数点后几位",example="1")
	private Integer constraintLen;

	@Column(name="group_id")
	@ApiModelProperty(value = "是那个组的",example="1")
	private Integer groupId;

	@Column(name="buy_back_water")
	@ApiModelProperty(value = "回购调价",example="1")
	private BigDecimal buyBackWater;

	@Column(name="sale_water")
	@ApiModelProperty(value = "销售调价",example="1")
	private BigDecimal saleWater;

	@ApiModelProperty(value = "排序顺序越大，越靠后",example="1")
	private Integer sort;

	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 *主键
	 */ 
	public void setGoldUserDiyMetalConfigId(Long goldUserDiyMetalConfigId){
		this.goldUserDiyMetalConfigId=goldUserDiyMetalConfigId;
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
	/**
	 *回购调价
	 */ 
	public void setBuyBackWater(BigDecimal buyBackWater){
		this.buyBackWater=buyBackWater;
	}
	/**
	 *回购调价
	 */ 
	public BigDecimal getBuyBackWater(){
		return buyBackWater;
	}
	/**
	 *销售调价
	 */ 
	public void setSaleWater(BigDecimal saleWater){
		this.saleWater=saleWater;
	}
	/**
	 *销售调价
	 */ 
	public BigDecimal getSaleWater(){
		return saleWater;
	}
}

