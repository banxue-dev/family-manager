package com.family.wb.entity; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* AboutOurInfo实体层 
* Auther:feng
* Date:2020-12-17 10:43:50
*/ 

@Table(name = "wb_about_our_info")
@ApiModel("关于我们，及一些展示")
public class AboutOurInfo {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="about_our_id")
	@ApiModelProperty(value = "主键id",example="1")
	private Integer aboutOurId;

	@Column(name="case_count")
	@ApiModelProperty(value = "成功案例数")
	private String caseCount;

	@Column(name="team_count")
	@ApiModelProperty(value = "团队人数")
	private String teamCount;

	@Column(name="customer_like_count")
	@ApiModelProperty(value = "客户好评数")
	private String customerLikeCount;

	@Column(name="our_introduce")
	@ApiModelProperty(value = "自我介绍")
	private String ourIntroduce;

	@Column(name="org_code")
	@ApiModelProperty(value = "orgCode")
	private String orgCode;


	/**
	 *主键id
	 */ 
	public void setAboutOurId(Integer aboutOurId){
		this.aboutOurId=aboutOurId;
	}
	/**
	 *主键id
	 */ 
	public Integer getAboutOurId(){
		return aboutOurId;
	}
	/**
	 *成功案例数
	 */ 
	public void setCaseCount(String caseCount){
		this.caseCount=caseCount;
	}
	/**
	 *成功案例数
	 */ 
	public String getCaseCount(){
		return caseCount;
	}
	/**
	 *团队人数
	 */ 
	public void setTeamCount(String teamCount){
		this.teamCount=teamCount;
	}
	/**
	 *团队人数
	 */ 
	public String getTeamCount(){
		return teamCount;
	}
	/**
	 *客户好评数
	 */ 
	public void setCustomerLikeCount(String customerLikeCount){
		this.customerLikeCount=customerLikeCount;
	}
	/**
	 *客户好评数
	 */ 
	public String getCustomerLikeCount(){
		return customerLikeCount;
	}
	/**
	 *自我介绍
	 */ 
	public void setOurIntroduce(String ourIntroduce){
		this.ourIntroduce=ourIntroduce;
	}
	/**
	 *自我介绍
	 */ 
	public String getOurIntroduce(){
		return ourIntroduce;
	}
	/**
	 *orgCode
	 */ 
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	/**
	 *orgCode
	 */ 
	public String getOrgCode(){
		return orgCode;
	}
}

