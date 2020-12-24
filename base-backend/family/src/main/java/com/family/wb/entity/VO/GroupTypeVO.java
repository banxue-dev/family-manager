package com.family.wb.entity.VO; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* GroupType实体层 
* Auther:feng
* Date:2020-12-24 10:35:26
*/ 

@ApiModel("所属的类型")
public class GroupTypeVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer groupTypeId;

	@ApiModelProperty(value = "类型名称")
	private String typeName;

	@ApiModelProperty(value = "描述")
	private String typeDesc;

	@ApiModelProperty(value = "类型code")
	private String typeCode;

	@ApiModelProperty(value = "值",example="1")
	private Integer typeValue;


	/**
	 *主键
	 */ 
	public void setGroupTypeId(Integer groupTypeId){
		this.groupTypeId=groupTypeId;
	}
	/**
	 *主键
	 */ 
	public Integer getGroupTypeId(){
		return groupTypeId;
	}
	/**
	 *类型名称
	 */ 
	public void setTypeName(String typeName){
		this.typeName=typeName;
	}
	/**
	 *类型名称
	 */ 
	public String getTypeName(){
		return typeName;
	}
	/**
	 *描述
	 */ 
	public void setTypeDesc(String typeDesc){
		this.typeDesc=typeDesc;
	}
	/**
	 *描述
	 */ 
	public String getTypeDesc(){
		return typeDesc;
	}
	/**
	 *类型code
	 */ 
	public void setTypeCode(String typeCode){
		this.typeCode=typeCode;
	}
	/**
	 *类型code
	 */ 
	public String getTypeCode(){
		return typeCode;
	}
	/**
	 *值
	 */ 
	public void setTypeValue(Integer typeValue){
		this.typeValue=typeValue;
	}
	/**
	 *值
	 */ 
	public Integer getTypeValue(){
		return typeValue;
	}
}

