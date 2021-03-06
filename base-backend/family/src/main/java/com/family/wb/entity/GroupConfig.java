package com.family.wb.entity; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* GroupConfig实体层 
* Auther:feng
* Date:2020-12-17 16:21:51
*/ 

@Table(name = "wb_group_config")
@ApiModel("里面是官网的所有的分组数据")
public class GroupConfig {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="group_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer groupId;

	@Column(name="group_name")
	@ApiModelProperty(value = "名称")
	private String groupName;

	@Column(name="group_desc")
	@ApiModelProperty(value = "描述")
	private String groupDesc;

	@Column(name="group_type")
	@ApiModelProperty(value = "0:案例，1：新闻，3服务范畴",example="1")
	private Integer groupType;

	@Column(name="group_img")
	@ApiModelProperty(value = "分组的图片")
	private String groupImg;


	/**
	 *主键
	 */ 
	public void setGroupId(Integer groupId){
		this.groupId=groupId;
	}
	/**
	 *主键
	 */ 
	public Integer getGroupId(){
		return groupId;
	}
	/**
	 *名称
	 */ 
	public void setGroupName(String groupName){
		this.groupName=groupName;
	}
	/**
	 *名称
	 */ 
	public String getGroupName(){
		return groupName;
	}
	/**
	 *描述
	 */ 
	public void setGroupDesc(String groupDesc){
		this.groupDesc=groupDesc;
	}
	/**
	 *描述
	 */ 
	public String getGroupDesc(){
		return groupDesc;
	}
	/**
	 *0:案例，1：新闻，3服务范畴
	 */ 
	public void setGroupType(Integer groupType){
		this.groupType=groupType;
	}
	/**
	 *0:案例，1：新闻，3服务范畴
	 */ 
	public Integer getGroupType(){
		return groupType;
	}
	/**
	 *分组的图片
	 */ 
	public void setGroupImg(String groupImg){
		this.groupImg=groupImg;
	}
	/**
	 *分组的图片
	 */ 
	public String getGroupImg(){
		return groupImg;
	}
}

