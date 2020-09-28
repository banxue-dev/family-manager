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
* GoodsInStorageRecord实体层 
* Auther:feng
* Date:2020-09-24 09:35:31
*/ 

@Table(name = "gold_goods_in_storage_record")
@ApiModel("")
public class GoodsInStorageRecord {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="goods_in_storage_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer goodsInStorageId;

	@Column(name="goods_storage_id")
	@ApiModelProperty(value = "商品id",example="1")
	private Integer goodsStorageId;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@Column(name="buy_count")
	@ApiModelProperty(value = "购买数量",example="1")
	private Integer buyCount;

	@Column(name="org_code")
	@ApiModelProperty(value = "组织code")
	private String orgCode;
	@Column(name="buy_price")
	@ApiModelProperty(value = "购买价")
	private BigDecimal buyPrice;

	@Column(name="buy_remark")
	@ApiModelProperty(value = "备注")
	private String buyRemark;


	
	
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	/**
	 *主键
	 */ 
	public void setGoodsInStorageId(Integer goodsInStorageId){
		this.goodsInStorageId=goodsInStorageId;
	}
	/**
	 *主键
	 */ 
	public Integer getGoodsInStorageId(){
		return goodsInStorageId;
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
	 *购买数量
	 */ 
	public void setBuyCount(Integer buyCount){
		this.buyCount=buyCount;
	}
	/**
	 *购买数量
	 */ 
	public Integer getBuyCount(){
		return buyCount;
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
	/**
	 *备注
	 */ 
	public void setBuyRemark(String buyRemark){
		this.buyRemark=buyRemark;
	}
	/**
	 *备注
	 */ 
	public String getBuyRemark(){
		return buyRemark;
	}
}

