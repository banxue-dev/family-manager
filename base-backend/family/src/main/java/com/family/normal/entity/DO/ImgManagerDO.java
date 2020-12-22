package com.family.normal.entity.DO; 

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
* ImgManager实体层 
* Auther:feng
* Date:2020-12-16 15:47:27
*/ 

@ApiModel("图片管理")
public class ImgManagerDO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer normalImgManagerId;

	@ApiModelProperty(value = "文件名")
	private String fileName;

	@ApiModelProperty(value = "原图访问地址")
	private String link;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "缩略图地址")
	private String thumbnailLink;


	/**
	 *主键
	 */ 
	public void setNormalImgManagerId(Integer normalImgManagerId){
		this.normalImgManagerId=normalImgManagerId;
	}
	/**
	 *主键
	 */ 
	public Integer getNormalImgManagerId(){
		return normalImgManagerId;
	}
	/**
	 *文件名
	 */ 
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	/**
	 *文件名
	 */ 
	public String getFileName(){
		return fileName;
	}
	/**
	 *原图访问地址
	 */ 
	public void setLink(String link){
		this.link=link;
	}
	/**
	 *原图访问地址
	 */ 
	public String getLink(){
		return link;
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
	 *缩略图地址
	 */ 
	public void setThumbnailLink(String thumbnailLink){
		this.thumbnailLink=thumbnailLink;
	}
	/**
	 *缩略图地址
	 */ 
	public String getThumbnailLink(){
		return thumbnailLink;
	}
}

