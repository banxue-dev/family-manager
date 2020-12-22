package com.family.wb.entity.VO; 

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
* CaseInfo实体层 
* Auther:feng
* Date:2020-12-16 15:41:18
*/ 

@ApiModel("官网上的案例数据")
public class CaseInfoVO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "主键",example="1")
	private Integer caseId;

	@ApiModelProperty(value = "名称")
	private String caseName;

	@ApiModelProperty(value = "图片")
	private String caseImg;

	@ApiModelProperty(value = "内容")
	private String caseContent;

	@ApiModelProperty(value = "创建时间")
	private String createTime;
	@ApiModelProperty(value = "上一条的名称")
	private String prevName;
	
	@ApiModelProperty(value = "上一条的id")
	private Integer prevId;
	@ApiModelProperty(value = "下一条的名称")
	private String nextName;
	
	@ApiModelProperty(value = "下一条的id")
	private Integer nextId;

	@ApiModelProperty(value = "所属的分组id",example="1")
	private Integer caseGroupId;
	@ApiModelProperty(value = "所属的分组名称",example="1")
	private String caseGroupName;

	
	
	

	public String getPrevName() {
		return prevName;
	}
	public void setPrevName(String prevName) {
		this.prevName = prevName;
	}
	public Integer getPrevId() {
		return prevId;
	}
	public void setPrevId(Integer prevId) {
		this.prevId = prevId;
	}
	public String getNextName() {
		return nextName;
	}
	public void setNextName(String nextName) {
		this.nextName = nextName;
	}
	public Integer getNextId() {
		return nextId;
	}
	public void setNextId(Integer nextId) {
		this.nextId = nextId;
	}
	public String getCaseGroupName() {
		return caseGroupName;
	}
	public void setCaseGroupName(String caseGroupName) {
		this.caseGroupName = caseGroupName;
	}
	/**
	 *主键
	 */ 
	public void setCaseId(Integer caseId){
		this.caseId=caseId;
	}
	/**
	 *主键
	 */ 
	public Integer getCaseId(){
		return caseId;
	}
	/**
	 *名称
	 */ 
	public void setCaseName(String caseName){
		this.caseName=caseName;
	}
	/**
	 *名称
	 */ 
	public String getCaseName(){
		return caseName;
	}
	/**
	 *图片
	 */ 
	public void setCaseImg(String caseImg){
		this.caseImg=caseImg;
	}
	/**
	 *图片
	 */ 
	public String getCaseImg(){
		return caseImg;
	}
	/**
	 *内容
	 */ 
	public void setCaseContent(String caseContent){
		this.caseContent=caseContent;
	}
	/**
	 *内容
	 */ 
	public String getCaseContent(){
		return caseContent;
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
	 *所属的分组id
	 */ 
	public void setCaseGroupId(Integer caseGroupId){
		this.caseGroupId=caseGroupId;
	}
	/**
	 *所属的分组id
	 */ 
	public Integer getCaseGroupId(){
		return caseGroupId;
	}
}

