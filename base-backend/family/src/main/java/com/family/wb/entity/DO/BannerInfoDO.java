package com.family.wb.entity.DO; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* BannerInfo实体层 
* Auther:feng
* Date:2020-12-24 10:39:22
*/ 

@ApiModel("官网的banner表,包含多个")
public class BannerInfoDO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer bannerId;

	@ApiModelProperty(value = "banner图的url")
	private String bannerLink;

	@ApiModelProperty(value = "描述")
	private String bannerDesc;

	@ApiModelProperty(value = "要跳转的链接")
	private String bannerOutUrl;

	@ApiModelProperty(value = "所属类型-来自于groupTyoe",example="1")
	private Integer bannerGroupTypeId;


	/**
	 *主键
	 */ 
	public void setBannerId(Integer bannerId){
		this.bannerId=bannerId;
	}
	/**
	 *主键
	 */ 
	public Integer getBannerId(){
		return bannerId;
	}
	/**
	 *banner图的url
	 */ 
	public void setBannerLink(String bannerLink){
		this.bannerLink=bannerLink;
	}
	/**
	 *banner图的url
	 */ 
	public String getBannerLink(){
		return bannerLink;
	}
	/**
	 *描述
	 */ 
	public void setBannerDesc(String bannerDesc){
		this.bannerDesc=bannerDesc;
	}
	/**
	 *描述
	 */ 
	public String getBannerDesc(){
		return bannerDesc;
	}
	/**
	 *要跳转的链接
	 */ 
	public void setBannerOutUrl(String bannerOutUrl){
		this.bannerOutUrl=bannerOutUrl;
	}
	/**
	 *要跳转的链接
	 */ 
	public String getBannerOutUrl(){
		return bannerOutUrl;
	}
	/**
	 *所属类型-来自于groupTyoe
	 */ 
	public void setBannerGroupTypeId(Integer bannerGroupTypeId){
		this.bannerGroupTypeId=bannerGroupTypeId;
	}
	/**
	 *所属类型-来自于groupTyoe
	 */ 
	public Integer getBannerGroupTypeId(){
		return bannerGroupTypeId;
	}
}

