package com.family.wb.entity; 

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
* ServiceRangeInfo实体层 
* Auther:feng
* Date:2020-12-16 15:41:24
*/ 

@Table(name = "wb_service_range_info")
@ApiModel("")
public class ServiceRangeInfo {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="service_range_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer serviceRangeId;

	@Column(name="range_group_id")
	@ApiModelProperty(value = "所属分组",example="1")
	private Integer rangeGroupId;

	@Column(name="range_content")
	@ApiModelProperty(value = "内容")
	private String rangeContent;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;


	/**
	 *主键
	 */ 
	public void setServiceRangeId(Integer serviceRangeId){
		this.serviceRangeId=serviceRangeId;
	}
	/**
	 *主键
	 */ 
	public Integer getServiceRangeId(){
		return serviceRangeId;
	}
	/**
	 *所属分组
	 */ 
	public void setRangeGroupId(Integer rangeGroupId){
		this.rangeGroupId=rangeGroupId;
	}
	/**
	 *所属分组
	 */ 
	public Integer getRangeGroupId(){
		return rangeGroupId;
	}
	/**
	 *内容
	 */ 
	public void setRangeContent(String rangeContent){
		this.rangeContent=rangeContent;
	}
	/**
	 *内容
	 */ 
	public String getRangeContent(){
		return rangeContent;
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

