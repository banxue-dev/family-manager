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
* FriendshipLinkInfo实体层 
* Auther:feng
* Date:2020-12-23 09:39:59
*/ 

@ApiModel("友情链接")
public class FriendshipLinkInfoAD {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer friendshipLinkId;

	@ApiModelProperty(value = "名称")
	private String linkName;

	@ApiModelProperty(value = "地址")
	private String linkUrl;

	@ApiModelProperty(value = "组织机构")
	private String orgCode;


	/**
	 *主键
	 */ 
	public void setFriendshipLinkId(Integer friendshipLinkId){
		this.friendshipLinkId=friendshipLinkId;
	}
	/**
	 *主键
	 */ 
	public Integer getFriendshipLinkId(){
		return friendshipLinkId;
	}
	/**
	 *名称
	 */ 
	public void setLinkName(String linkName){
		this.linkName=linkName;
	}
	/**
	 *名称
	 */ 
	public String getLinkName(){
		return linkName;
	}
	/**
	 *地址
	 */ 
	public void setLinkUrl(String linkUrl){
		this.linkUrl=linkUrl;
	}
	/**
	 *地址
	 */ 
	public String getLinkUrl(){
		return linkUrl;
	}
	/**
	 *组织机构
	 */ 
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	/**
	 *组织机构
	 */ 
	public String getOrgCode(){
		return com.family.utils.OrgCodeGreater.decode(orgCode);
	}
	public String getSourceOrgCode(){
		return this.orgCode;
	}
}

