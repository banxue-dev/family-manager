package com.family.gold.entity.DO; 

import java.math.BigDecimal;

import com.family.utils.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* GoodsStorage实体层 
* Auther:feng
* Date:2020-09-24 09:35:33
*/ 

@ApiModel("黄金库存管理")
public class GoodsStorageDO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer goodsStorageId;

	@ApiModelProperty(value = "商品名称")
	private String goodsName;

	@ApiModelProperty(value = "备注")
	private String goodsRemark;

	@ApiModelProperty(value = "商品剩余库存",example="1")
	private Long goodsSurplusStorage;

	@ApiModelProperty(value = "商品单位")
	private String goodsUnit;

	@ApiModelProperty(value = "商品单价",example="1")
	private BigDecimal goodsPrice;

	@ApiModelProperty(value = "创建人")
	private String createUser;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "组织机构")
	private String orgCode;


	/**
	 *主键
	 */ 
	public void setGoodsStorageId(Integer goodsStorageId){
		this.goodsStorageId=goodsStorageId;
	}
	/**
	 *主键
	 */ 
	public Integer getGoodsStorageId(){
		return goodsStorageId;
	}
	/**
	 *商品名称
	 */ 
	public void setGoodsName(String goodsName){
		this.goodsName=goodsName;
	}
	/**
	 *商品名称
	 */ 
	public String getGoodsName(){
		return goodsName;
	}
	/**
	 *备注
	 */ 
	public void setGoodsRemark(String goodsRemark){
		this.goodsRemark=goodsRemark;
	}
	/**
	 *备注
	 */ 
	public String getGoodsRemark(){
		return goodsRemark;
	}
	/**
	 *商品剩余库存
	 */ 
	public void setGoodsSurplusStorage(Long goodsSurplusStorage){
		this.goodsSurplusStorage=goodsSurplusStorage;
	}
	/**
	 *商品剩余库存
	 */ 
	public Long getGoodsSurplusStorage(){
		return goodsSurplusStorage;
	}
	/**
	 *商品单位
	 */ 
	public void setGoodsUnit(String goodsUnit){
		this.goodsUnit=goodsUnit;
	}
	/**
	 *商品单位
	 */ 
	public String getGoodsUnit(){
		return goodsUnit;
	}
	/**
	 *商品单价
	 */ 
	public void setGoodsPrice(BigDecimal goodsPrice){
		this.goodsPrice=goodsPrice;
	}
	/**
	 *商品单价
	 */ 
	public BigDecimal getGoodsPrice(){
		return goodsPrice;
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
		return com.family.utils.OrgCodeGreater.decode(orgCode);
	}
	public String getSourceOrgCode(){
		return this.orgCode;
	}
}

