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
* CaseInfo实体层 
* Auther:feng
* Date:2020-12-16 15:41:18
*/ 

@Table(name = "wb_case_info")
@ApiModel("官网上的案例数据")
public class CaseInfo {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="case_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer caseId;

	@Column(name="case_name")
	@ApiModelProperty(value = "名称")
	private String caseName;

	@Column(name="case_img")
	@ApiModelProperty(value = "图片")
	private String caseImg;

	@Column(name="case_content")
	@ApiModelProperty(value = "内容")
	private String caseContent;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@Column(name="case_group_id")
	@ApiModelProperty(value = "所属的分组id",example="1")
	private Integer caseGroupId;


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

