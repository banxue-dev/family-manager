package com.family.system.entity.DO; 

import java.util.List;

import com.family.utils.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* Menu实体层 
* Auther:feng
* Date:2020-09-16 16:04:43
*/ 

@ApiModel("菜单表")
public class MenuDO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer menuId;

	@ApiModelProperty(value = "名称")
	private String menuName;

	@ApiModelProperty(value = "描述")
	private String menuDesc;
	@ApiModelProperty(value = "标题")
	private String menuTitle;

	@ApiModelProperty(value = "图标")
	private String menuIcon;

	@ApiModelProperty(value = "层级",example="1")
	private Integer lLevel;

	@ApiModelProperty(value = "上级id",example="1")
	private Integer parentId;
	
	@ApiModelProperty(value = "角色ids",example="1")
	private List<Integer> roleIds;

	@ApiModelProperty(value = "获取类型，0：左侧菜单，1：赋权",example="1")
	private int catchType=0;

	@ApiModelProperty(value = "url")
	private String menuUrl;

	@ApiModelProperty(value = "排序")
	private Integer menuSort;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "是否展示:0展示，1不展示",example="1")
	private Integer ifShow;

	@ApiModelProperty(value = "菜单类型",example="1")
	private Integer menuType;

	
	
	
	public int getCatchType() {
		return catchType;
	}
	public void setCatchType(int catchType) {
		this.catchType = catchType;
	}
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	/**
	 *主键
	 */ 
	public void setMenuId(Integer menuId){
		this.menuId=menuId;
	}
	/**
	 *主键
	 */ 
	public Integer getMenuId(){
		return menuId;
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
	 *描述
	 */ 
	public void setMenuDesc(String menuDesc){
		this.menuDesc=menuDesc;
	}
	/**
	 *描述
	 */ 
	public String getMenuDesc(){
		return menuDesc;
	}
	/**
	 *图标
	 */ 
	public void setMenuIcon(String menuIcon){
		this.menuIcon=menuIcon;
	}
	/**
	 *图标
	 */ 
	public String getMenuIcon(){
		return menuIcon;
	}
	/**
	 *层级
	 */ 
	public void setLLevel(Integer lLevel){
		this.lLevel=lLevel;
	}
	/**
	 *层级
	 */ 
	public Integer getLLevel(){
		return lLevel;
	}
	/**
	 *上级id
	 */ 
	public void setParentId(Integer parentId){
		this.parentId=parentId;
	}
	/**
	 *上级id
	 */ 
	public Integer getParentId(){
		return parentId;
	}
	/**
	 *url
	 */ 
	public void setMenuUrl(String menuUrl){
		this.menuUrl=menuUrl;
	}
	/**
	 *url
	 */ 
	public String getMenuUrl(){
		return menuUrl;
	}
	
	public String getMenuTitle() {
		return menuTitle;
	}
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}
	public Integer getlLevel() {
		return lLevel;
	}
	public void setlLevel(Integer lLevel) {
		this.lLevel = lLevel;
	}
	/**
	 *排序
	 */ 
	public void setMenuSort(Integer menuSort){
		this.menuSort=menuSort;
	}
	/**
	 *排序
	 */ 
	public Integer getMenuSort(){
		return menuSort;
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
	 *是否展示:0展示，1不展示
	 */ 
	public void setIfShow(Integer ifShow){
		this.ifShow=ifShow;
	}
	/**
	 *是否展示:0展示，1不展示
	 */ 
	public Integer getIfShow(){
		return ifShow;
	}
	/**
	 *菜单类型
	 */ 
	public void setMenuType(Integer menuType){
		this.menuType=menuType;
	}
	/**
	 *菜单类型
	 */ 
	public Integer getMenuType(){
		return menuType;
	}
}

