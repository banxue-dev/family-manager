package com.family.gold.entity.DO; 

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
* UserDiyGroupConfig实体层 
* Auther:feng
* Date:2020-10-21 17:50:47
*/ 

@ApiModel("")
public class UserDiyGroupConfigDO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer goldUserDiyGroupConfigId;

	@ApiModelProperty(value = "名称")
	private String groupName;

	@ApiModelProperty(value = "组织机构名称")
	private String orgCode;

	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "分组编码")
	private Integer groupCode;
	@ApiModelProperty(value = "分组类型0手机1电脑")
	private Integer groupType;
	
	

	public Integer getGroupType() {
		return groupType;
	}
	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
	public Integer getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(Integer groupCode) {
		this.groupCode = groupCode;
	}
	/**
	 *主键
	 */ 
	public void setGoldUserDiyGroupConfigId(Integer goldUserDiyGroupConfigId){
		this.goldUserDiyGroupConfigId=goldUserDiyGroupConfigId;
	}
	/**
	 *主键
	 */ 
	public Integer getGoldUserDiyGroupConfigId(){
		return goldUserDiyGroupConfigId;
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
	 *组织机构名称
	 */ 
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	/**
	 *组织机构名称
	 */ 
	public String getOrgCode(){
		return com.family.utils.OrgCodeGreater.decode(orgCode);
	}
	public String getSourceOrgCode(){
		return this.orgCode;
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

