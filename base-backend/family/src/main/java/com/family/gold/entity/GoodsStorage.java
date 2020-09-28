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
* GoodsStorage实体层 
* Auther:feng
* Date:2020-09-24 09:35:33
*/ 

@Table(name = "gold_goods_storage")
@ApiModel("黄金库存管理")
public class GoodsStorage {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="goods_storage_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer goodsStorageId;

	@Column(name="goods_name")
	@ApiModelProperty(value = "商品名称")
	private String goodsName;

	@Column(name="goods_remark")
	@ApiModelProperty(value = "备注")
	private String goodsRemark;

	@Column(name="goods_surplus_storage")
	@ApiModelProperty(value = "商品剩余库存",example="1")
	private Long goodsSurplusStorage;

	@Column(name="goods_unit")
	@ApiModelProperty(value = "商品单位")
	private String goodsUnit;

	@Column(name="goods_price")
	@ApiModelProperty(value = "商品单价",example="1")
	private BigDecimal goodsPrice;

	@Column(name="create_user")
	@ApiModelProperty(value = "创建人")
	private String createUser;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@Column(name="org_code")
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
		return orgCode;
	}
}

