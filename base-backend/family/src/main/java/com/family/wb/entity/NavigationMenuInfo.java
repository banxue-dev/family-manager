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
* NavigationMenuInfo实体层 
* Auther:feng
* Date:2020-12-16 15:41:22
*/ 

@Table(name = "wb_navigation_menu_info")
@ApiModel("")
public class NavigationMenuInfo {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="navigation_menu_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer navigationMenuId;

	@Column(name="menu_name")
	@ApiModelProperty(value = "名称")
	private String menuName;

	@Column(name="menu_page_banner")
	@ApiModelProperty(value = "页面上的banner图")
	private String menuPageBanner;

	@Column(name="menu_logo")
	@ApiModelProperty(value = "菜单图片")
	private String menuLogo;

	@Column(name="menu_link")
	@ApiModelProperty(value = "跳转地址")
	private String menuLink;


	/**
	 *主键
	 */ 
	public void setNavigationMenuId(Integer navigationMenuId){
		this.navigationMenuId=navigationMenuId;
	}
	/**
	 *主键
	 */ 
	public Integer getNavigationMenuId(){
		return navigationMenuId;
	}
	/**
	 *名称
	 */ 
	public void setMenuName(String menuName){
		this.menuName=menuName;
	}
	/**
	 *名称
	 */ 
	public String getMenuName(){
		return menuName;
	}
	/**
	 *页面上的banner图
	 */ 
	public void setMenuPageBanner(String menuPageBanner){
		this.menuPageBanner=menuPageBanner;
	}
	/**
	 *页面上的banner图
	 */ 
	public String getMenuPageBanner(){
		return menuPageBanner;
	}
	/**
	 *菜单图片
	 */ 
	public void setMenuLogo(String menuLogo){
		this.menuLogo=menuLogo;
	}
	/**
	 *菜单图片
	 */ 
	public String getMenuLogo(){
		return menuLogo;
	}
	/**
	 *跳转地址
	 */ 
	public void setMenuLink(String menuLink){
		this.menuLink=menuLink;
	}
	/**
	 *跳转地址
	 */ 
	public String getMenuLink(){
		return menuLink;
	}
}

