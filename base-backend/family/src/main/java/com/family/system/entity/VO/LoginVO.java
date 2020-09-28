package com.family.system.entity.VO;

import java.util.List;

/**
 * 登录成功后返回的数据
 * @author tWX932595
 *
 */
public class LoginVO {

	private String token;//token
	private Integer userId;//userid
	private String orgCode;//用户所属的组织id
	private List<String> userAuth;//用户拥有的权限，同时在后台和前台都缓存上
	
	
	public List<String> getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(List<String> userAuth) {
		this.userAuth = userAuth;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	
	
	
}
