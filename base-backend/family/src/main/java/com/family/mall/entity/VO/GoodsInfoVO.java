package com.family.mall.entity.VO; 

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
* GoodsInfo实体层 
* Auther:feng
* Date:2021-03-08 18:16:20
*/ 

@ApiModel("商城的商品表")
public class GoodsInfoVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "",example="1")
	private Long goodsId;

	@ApiModelProperty(value = "名称")
	private String goodsName;

	@ApiModelProperty(value = "标签")
	private String goodsTag;

	@ApiModelProperty(value = "划线价",example="1")
	private BigDecimal goodsLinePrice;

	@ApiModelProperty(value = "真实价",example="1")
	private BigDecimal goodsPrice;

	@ApiModelProperty(value = "头图")
	private String goodsHeadImg;

	@ApiModelProperty(value = "详情图片")
	private String goodsDetailImgs;

	@ApiModelProperty(value = "详情描述")
	private String goodsDetailDesc;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "")
	private String goodsMail;


	/**
	 *
	 */ 
	public void setGoodsId(Long goodsId){
		this.goodsId=goodsId;
	}
	/**
	 *
	 */ 
	public Long getGoodsId(){
		return goodsId;
	}
	/**
	 *名称
	 */ 
	public void setGoodsName(String goodsName){
		this.goodsName=goodsName;
	}
	/**
	 *名称
	 */ 
	public String getGoodsName(){
		return goodsName;
	}
	/**
	 *标签
	 */ 
	public void setGoodsTag(String goodsTag){
		this.goodsTag=goodsTag;
	}
	/**
	 *标签
	 */ 
	public String getGoodsTag(){
		return goodsTag;
	}
	/**
	 *划线价
	 */ 
	public void setGoodsLinePrice(BigDecimal goodsLinePrice){
		this.goodsLinePrice=goodsLinePrice;
	}
	/**
	 *划线价
	 */ 
	public BigDecimal getGoodsLinePrice(){
		return goodsLinePrice;
	}
	/**
	 *真实价
	 */ 
	public void setGoodsPrice(BigDecimal goodsPrice){
		this.goodsPrice=goodsPrice;
	}
	/**
	 *真实价
	 */ 
	public BigDecimal getGoodsPrice(){
		return goodsPrice;
	}
	/**
	 *头图
	 */ 
	public void setGoodsHeadImg(String goodsHeadImg){
		this.goodsHeadImg=goodsHeadImg;
	}
	/**
	 *头图
	 */ 
	public String getGoodsHeadImg(){
		return goodsHeadImg;
	}
	/**
	 *详情图片
	 */ 
	public void setGoodsDetailImgs(String goodsDetailImgs){
		this.goodsDetailImgs=goodsDetailImgs;
	}
	/**
	 *详情图片
	 */ 
	public String getGoodsDetailImgs(){
		return goodsDetailImgs;
	}
	/**
	 *详情描述
	 */ 
	public void setGoodsDetailDesc(String goodsDetailDesc){
		this.goodsDetailDesc=goodsDetailDesc;
	}
	/**
	 *详情描述
	 */ 
	public String getGoodsDetailDesc(){
		return goodsDetailDesc;
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
	 *
	 */ 
	public void setGoodsMail(String goodsMail){
		this.goodsMail=goodsMail;
	}
	/**
	 *
	 */ 
	public String getGoodsMail(){
		return goodsMail;
	}
}

