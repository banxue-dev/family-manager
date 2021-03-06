package com.family.wb.entity.DO; 

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
* NewsInfo实体层 
* Auther:feng
* Date:2020-12-16 15:41:23
*/ 

@ApiModel("官网新闻表")
public class NewsInfoDO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer newsId;

	@ApiModelProperty(value = "分组id，---group_config表",example="1")
	private Integer newsGroupId;

	@ApiModelProperty(value = "图片")
	private String newsImg;

	@ApiModelProperty(value = "标题")
	private String newsTitle;

	@ApiModelProperty(value = "内容简介")
	private String newsBrief;

	@ApiModelProperty(value = "新闻内容")
	private String newsContent;

	@ApiModelProperty(value = "创建时间")
	private String createTime;


	@ApiModelProperty(value = "作者")
	private String newsAuther;
	
	public String getNewsAuther() {
		return newsAuther;
	}
	public void setNewsAuther(String newsAuther) {
		this.newsAuther = newsAuther;
	}
	/**
	 *主键
	 */ 
	public void setNewsId(Integer newsId){
		this.newsId=newsId;
	}
	/**
	 *主键
	 */ 
	public Integer getNewsId(){
		return newsId;
	}
	/**
	 *分组id，---group_config表
	 */ 
	public void setNewsGroupId(Integer newsGroupId){
		this.newsGroupId=newsGroupId;
	}
	/**
	 *分组id，---group_config表
	 */ 
	public Integer getNewsGroupId(){
		return newsGroupId;
	}
	/**
	 *图片
	 */ 
	public void setNewsImg(String newsImg){
		this.newsImg=newsImg;
	}
	/**
	 *图片
	 */ 
	public String getNewsImg(){
		return newsImg;
	}
	/**
	 *标题
	 */ 
	public void setNewsTitle(String newsTitle){
		this.newsTitle=newsTitle;
	}
	/**
	 *标题
	 */ 
	public String getNewsTitle(){
		return newsTitle;
	}
	/**
	 *内容简介
	 */ 
	public void setNewsBrief(String newsBrief){
		this.newsBrief=newsBrief;
	}
	/**
	 *内容简介
	 */ 
	public String getNewsBrief(){
		return newsBrief;
	}
	/**
	 *新闻内容
	 */ 
	public void setNewsContent(String newsContent){
		this.newsContent=newsContent;
	}
	/**
	 *新闻内容
	 */ 
	public String getNewsContent(){
		return newsContent;
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
}

