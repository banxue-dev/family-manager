package com.family.gold.entity;

import java.util.List;

import com.family.gold.entity.VO.PersonalInfoVO;
import com.family.gold.entity.VO.UserDiyGroupConfigVO;
import com.family.gold.entity.VO.UserDiyMetalConfigVO;

/**
 * 页面上获取的客户数据
 * @author tWX932595
 *
 */
public class ReturnUserInfoData {

	/**
	 * 用户数据
	 */
	private PersonalInfoVO personalInfo;
	/**
	 * 自定义的分组数据
	 */
	private List<UserDiyGroupConfigVO> groupConfig;/**
	 * 自定义的金属数据
	 */
	private List<UserDiyMetalConfigVO> metalConfig;
	public PersonalInfoVO getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(PersonalInfoVO personalInfo) {
		this.personalInfo = personalInfo;
	}
	public List<UserDiyMetalConfigVO> getMetalConfig() {
		return metalConfig;
	}
	public void setMetalConfig(List<UserDiyMetalConfigVO> metalConfig) {
		this.metalConfig = metalConfig;
	}
	public List<UserDiyGroupConfigVO> getGroupConfig() {
		return groupConfig;
	}
	public void setGroupConfig(List<UserDiyGroupConfigVO> groupConfig) {
		this.groupConfig = groupConfig;
	}
	
	
	
	
}
