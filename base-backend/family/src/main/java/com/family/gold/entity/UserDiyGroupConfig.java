package com.family.gold.entity; 

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

@Table(name = "gold_user_diy_group_config")
@ApiModel("")
public class UserDiyGroupConfig {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="gold_user_diy_group_config_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer goldUserDiyGroupConfigId;

	@Column(name="group_name")
	@ApiModelProperty(value = "名称")
	private String groupName;

	@Column(name="org_code")
	@ApiModelProperty(value = "组织机构名称")
	private String orgCode;
	@Column(name="group_code")
	@ApiModelProperty(value = "分组编码")
	private Integer groupCode;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;


	
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
		return orgCode;
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

