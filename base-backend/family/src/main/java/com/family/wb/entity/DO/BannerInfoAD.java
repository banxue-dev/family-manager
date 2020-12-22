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
* Date:2020-12-16 15:41:17
*/ 

@ApiModel("")
public class BannerInfoAD {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer bannerId;

	@ApiModelProperty(value = "banner图的url")
	private String bannerLink;

	@ApiModelProperty(value = "描述")
	private String bannerDesc;

	@ApiModelProperty(value = "要跳转的链接")
	private String bannerOutUrl;


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
}

