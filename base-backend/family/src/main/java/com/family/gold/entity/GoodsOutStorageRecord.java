package com.family.gold.entity; 

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.family.utils.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* GoodsOutStorageRecord实体层 
* Auther:feng
* Date:2020-09-24 09:35:32
*/ 

@Table(name = "gold_goods_out_storage_record")
@ApiModel("")
public class GoodsOutStorageRecord {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="goods_out_storage_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer goodsOutStorageId;

	@Column(name="goods_storage_id")
	@ApiModelProperty(value = "商品id",example="1")
	private Integer goodsStorageId;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@Column(name="sale_price")
	@ApiModelProperty(value = "销售单价",example="1")
	private BigDecimal salePrice;

	@Column(name="buy_user")
	@ApiModelProperty(value = "购买人")
	private String buyUser;

	@Column(name="sale_count")
	@ApiModelProperty(value = "销售数量",example="1")
	private Integer saleCount;

	@Column(name="sale_remark")
	@ApiModelProperty(value = "备注")
	private String saleRemark;

	@Column(name="org_code")
	@ApiModelProperty(value = "组织code")
	private String orgCode;


	/**
	 *主键
	 */ 
	public void setGoodsOutStorageId(Integer goodsOutStorageId){
		this.goodsOutStorageId=goodsOutStorageId;
	}
	/**
	 *主键
	 */ 
	public Integer getGoodsOutStorageId(){
		return goodsOutStorageId;
	}
	/**
	 *商品id
	 */ 
	public void setGoodsStorageId(Integer goodsStorageId){
		this.goodsStorageId=goodsStorageId;
	}
	/**
	 *商品id
	 */ 
	public Integer getGoodsStorageId(){
		return goodsStorageId;
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
	 *销售单价
	 */ 
	public void setSalePrice(BigDecimal salePrice){
		this.salePrice=salePrice;
	}
	/**
	 *销售单价
	 */ 
	public BigDecimal getSalePrice(){
		return salePrice;
	}
	/**
	 *购买人
	 */ 
	public void setBuyUser(String buyUser){
		this.buyUser=buyUser;
	}
	/**
	 *购买人
	 */ 
	public String getBuyUser(){
		return buyUser;
	}
	/**
	 *销售数量
	 */ 
	public void setSaleCount(Integer saleCount){
		this.saleCount=saleCount;
	}
	/**
	 *销售数量
	 */ 
	public Integer getSaleCount(){
		return saleCount;
	}
	/**
	 *备注
	 */ 
	public void setSaleRemark(String saleRemark){
		this.saleRemark=saleRemark;
	}
	/**
	 *备注
	 */ 
	public String getSaleRemark(){
		return saleRemark;
	}
	/**
	 *组织code
	 */ 
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	/**
	 *组织code
	 */ 
	public String getOrgCode(){
		return orgCode;
	}
}

