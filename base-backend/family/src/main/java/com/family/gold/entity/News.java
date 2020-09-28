package com.family.gold.entity; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
/** 
* News实体层 
* Auther:feng
* Date:2020-09-22 17:59:24
*/ 

@Table(name = "gold_news")
@ApiModel("黄金的新闻内容")
public class News {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="news_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer newsId;

	@Column(name="news_title")
	@ApiModelProperty(value = "标题")
	private String newsTitle;

	@Column(name="news_content")
	@ApiModelProperty(value = "正文")
	private String newsContent;

	@Column(name="create_time")
	@ApiModelProperty(value = "时间")
	private String createTime;

	@Column(name="org_code")
	@ApiModelProperty(value = "组织code")
	private String orgCode;


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
	 *正文
	 */ 
	public void setNewsContent(String newsContent){
		this.newsContent=newsContent;
	}
	/**
	 *正文
	 */ 
	public String getNewsContent(){
		return newsContent;
	}
	/**
	 *时间
	 */ 
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}
	/**
	 *时间
	 */ 
	public String getCreateTime(){
		  if(StringUtils.isNotNull(createTime)) {if(createTime.contains(".0")) {return createTime.replace(".0", "");}}return createTime;
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
		return this.orgCode;
	}
	public String getSourceOrgCode(){
		return this.orgCode;
	}
}

