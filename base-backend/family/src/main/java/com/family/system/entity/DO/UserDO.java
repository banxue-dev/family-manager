package com.family.system.entity.DO; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
/** 
* User实体层 
* Auther:feng
* Date:2020-09-17 15:13:13
*/ 

@ApiModel("系统用户表")
public class UserDO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户id",example="1")
	private Integer userId;

	@ApiModelProperty(value = "登录名")
	private String userName;

	@ApiModelProperty(value = "密码")
	private String userPwd;

	@ApiModelProperty(value = "昵称")
	private String userNickName;

	@ApiModelProperty(value = "头像")
	private String userHeadUrl;

	@ApiModelProperty(value = "电话")
	private String userPhone;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "创建人")
	private String createUser;

	@ApiModelProperty(value = "邮箱地址")
	private String userMail;


	/**
	 *用户id
	 */ 
	public void setUserId(Integer userId){
		this.userId=userId;
	}
	/**
	 *用户id
	 */ 
	public Integer getUserId(){
		return userId;
	}
	/**
	 *登录名
	 */ 
	public void setUserName(String userName){
		this.userName=userName;
	}
	/**
	 *登录名
	 */ 
	public String getUserName(){
		return userName;
	}
	/**
	 *密码
	 */ 
	public void setUserPwd(String userPwd){
		this.userPwd=userPwd;
	}
	/**
	 *密码
	 */ 
	public String getUserPwd(){
		return userPwd;
	}
	/**
	 *昵称
	 */ 
	public void setUserNickName(String userNickName){
		this.userNickName=userNickName;
	}
	/**
	 *昵称
	 */ 
	public String getUserNickName(){
		return userNickName;
	}
	/**
	 *头像
	 */ 
	public void setUserHeadUrl(String userHeadUrl){
		this.userHeadUrl=userHeadUrl;
	}
	/**
	 *头像
	 */ 
	public String getUserHeadUrl(){
		return userHeadUrl;
	}
	/**
	 *电话
	 */ 
	public void setUserPhone(String userPhone){
		this.userPhone=userPhone;
	}
	/**
	 *电话
	 */ 
	public String getUserPhone(){
		return userPhone;
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
	 *创建人
	 */ 
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	/**
	 *创建人
	 */ 
	public String getCreateUser(){
		return createUser;
	}
	/**
	 *邮箱地址
	 */ 
	public void setUserMail(String userMail){
		this.userMail=userMail;
	}
	/**
	 *邮箱地址
	 */ 
	public String getUserMail(){
		return userMail;
	}
}

