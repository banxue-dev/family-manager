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
* FriendshipLinkInfo实体层 
* Auther:feng
* Date:2020-12-16 15:41:19
*/ 

@Table(name = "wb_friendship_link_info")
@ApiModel("")
public class FriendshipLinkInfo {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="friendship_link_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer friendshipLinkId;

	@Column(name="link_name")
	@ApiModelProperty(value = "名称")
	private String linkName;

	@Column(name="link_url")
	@ApiModelProperty(value = "地址")
	private String linkUrl;


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
}

