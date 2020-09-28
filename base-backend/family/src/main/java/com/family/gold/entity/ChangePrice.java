package com.family.gold.entity; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* ChangePrice实体层 
* Auther:feng
* Date:2020-09-21 15:07:56
*/ 

@Table(name = "gold_change_price")
@ApiModel("黄金调价的相关配置")
public class ChangePrice {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="change_price_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer changePriceId;

	@Column(name="org_code")
	@ApiModelProperty(value = "所属组织机构")
	private String orgCode;

	@Column(name="open_status")
	@ApiModelProperty(value = "0:开盘，1：停盘，2：周末停盘周内开盘",example="1")
	private Integer openStatus;

	@Column(name="xaubuy")
	@ApiModelProperty(value = "黄金买价",example="1")
	private Long xaubuy;

	@Column(name="xausale")
	@ApiModelProperty(value = "黄金售价",example="1")
	private Long xausale;

	@Column(name="xagbuy")
	@ApiModelProperty(value = "银买价",example="1")
	private Long xagbuy;

	@Column(name="xagsale")
	@ApiModelProperty(value = "银售价",example="1")
	private Long xagsale;

	@Column(name="ptbuy")
	@ApiModelProperty(value = "铂金买价",example="1")
	private Long ptbuy;

	@Column(name="ptsale")
	@ApiModelProperty(value = "铂金售价",example="1")
	private Long ptsale;

	@Column(name="pdbuy")
	@ApiModelProperty(value = "钯金买价",example="1")
	private Long pdbuy;

	@Column(name="pdsale")
	@ApiModelProperty(value = "钯金售价",example="1")
	private Long pdsale;

	@Column(name="au18buy")
	@ApiModelProperty(value = "18k金买价",example="1")
	private Long au18buy;

	@Column(name="au18sale")
	@ApiModelProperty(value = "18K金售价",example="1")
	private Long au18sale;

	@Column(name="ag925buy")
	@ApiModelProperty(value = "银925买价",example="1")
	private Long ag925buy;

	@Column(name="ag925sale")
	@ApiModelProperty(value = "银925售价",example="1")
	private Long ag925sale;

	@Column(name="pt990buy")
	@ApiModelProperty(value = "铂金990买价",example="1")
	private Long pt990buy;

	@Column(name="pt990sale")
	@ApiModelProperty(value = "铂金990售价",example="1")
	private Long pt990sale;

	@Column(name="pt950buy")
	@ApiModelProperty(value = "铂金950买价",example="1")
	private Long pt950buy;

	@Column(name="pt950sale")
	@ApiModelProperty(value = "铂金950售价",example="1")
	private Long pt950sale;

	@Column(name="pd990buy")
	@ApiModelProperty(value = "钯金990买价",example="1")
	private Long pd990buy;

	@Column(name="pd990sale")
	@ApiModelProperty(value = "钯金990售价",example="1")
	private Long pd990sale;

	@Column(name="pd950buy")
	@ApiModelProperty(value = "钯金950买价",example="1")
	private Long pd950buy;

	@Column(name="pd950sale")
	@ApiModelProperty(value = "钯金950售价",example="1")
	private Long pd950sale;

	@Column(name="au1000buy")
	@ApiModelProperty(value = "千足金买价",example="1")
	private Long au1000buy;

	@Column(name="au1000sale")
	@ApiModelProperty(value = "千足金售价",example="1")
	private Long au1000sale;


	/**
	 *主键
	 */ 
	public void setChangePriceId(Integer changePriceId){
		this.changePriceId=changePriceId;
	}
	/**
	 *主键
	 */ 
	public Integer getChangePriceId(){
		return changePriceId;
	}
	/**
	 *所属组织机构
	 */ 
	public void setOrgCode(String orgCode){
		this.orgCode=orgCode;
	}
	/**
	 *所属组织机构
	 */ 
	public String getOrgCode(){
		return this.orgCode;
	}
	public String getSourceOrgCode(){
		return this.orgCode;
	}
	/**
	 *0:开盘，1：停盘，2：周末停盘周内开盘
	 */ 
	public void setOpenStatus(Integer openStatus){
		this.openStatus=openStatus;
	}
	/**
	 *0:开盘，1：停盘，2：周末停盘周内开盘
	 */ 
	public Integer getOpenStatus(){
		return openStatus;
	}
	/**
	 *黄金买价
	 */ 
	public void setXaubuy(Long xaubuy){
		this.xaubuy=xaubuy;
	}
	/**
	 *黄金买价
	 */ 
	public Long getXaubuy(){
		return xaubuy;
	}
	/**
	 *黄金售价
	 */ 
	public void setXausale(Long xausale){
		this.xausale=xausale;
	}
	/**
	 *黄金售价
	 */ 
	public Long getXausale(){
		return xausale;
	}
	/**
	 *银买价
	 */ 
	public void setXagbuy(Long xagbuy){
		this.xagbuy=xagbuy;
	}
	/**
	 *银买价
	 */ 
	public Long getXagbuy(){
		return xagbuy;
	}
	/**
	 *银售价
	 */ 
	public void setXagsale(Long xagsale){
		this.xagsale=xagsale;
	}
	/**
	 *银售价
	 */ 
	public Long getXagsale(){
		return xagsale;
	}
	/**
	 *铂金买价
	 */ 
	public void setPtbuy(Long ptbuy){
		this.ptbuy=ptbuy;
	}
	/**
	 *铂金买价
	 */ 
	public Long getPtbuy(){
		return ptbuy;
	}
	/**
	 *铂金售价
	 */ 
	public void setPtsale(Long ptsale){
		this.ptsale=ptsale;
	}
	/**
	 *铂金售价
	 */ 
	public Long getPtsale(){
		return ptsale;
	}
	/**
	 *钯金买价
	 */ 
	public void setPdbuy(Long pdbuy){
		this.pdbuy=pdbuy;
	}
	/**
	 *钯金买价
	 */ 
	public Long getPdbuy(){
		return pdbuy;
	}
	/**
	 *钯金售价
	 */ 
	public void setPdsale(Long pdsale){
		this.pdsale=pdsale;
	}
	/**
	 *钯金售价
	 */ 
	public Long getPdsale(){
		return pdsale;
	}
	/**
	 *18k金买价
	 */ 
	public void setAu18buy(Long au18buy){
		this.au18buy=au18buy;
	}
	/**
	 *18k金买价
	 */ 
	public Long getAu18buy(){
		return au18buy;
	}
	/**
	 *18K金售价
	 */ 
	public void setAu18sale(Long au18sale){
		this.au18sale=au18sale;
	}
	/**
	 *18K金售价
	 */ 
	public Long getAu18sale(){
		return au18sale;
	}
	/**
	 *银925买价
	 */ 
	public void setAg925buy(Long ag925buy){
		this.ag925buy=ag925buy;
	}
	/**
	 *银925买价
	 */ 
	public Long getAg925buy(){
		return ag925buy;
	}
	/**
	 *银925售价
	 */ 
	public void setAg925sale(Long ag925sale){
		this.ag925sale=ag925sale;
	}
	/**
	 *银925售价
	 */ 
	public Long getAg925sale(){
		return ag925sale;
	}
	/**
	 *铂金990买价
	 */ 
	public void setPt990buy(Long pt990buy){
		this.pt990buy=pt990buy;
	}
	/**
	 *铂金990买价
	 */ 
	public Long getPt990buy(){
		return pt990buy;
	}
	/**
	 *铂金990售价
	 */ 
	public void setPt990sale(Long pt990sale){
		this.pt990sale=pt990sale;
	}
	/**
	 *铂金990售价
	 */ 
	public Long getPt990sale(){
		return pt990sale;
	}
	/**
	 *铂金950买价
	 */ 
	public void setPt950buy(Long pt950buy){
		this.pt950buy=pt950buy;
	}
	/**
	 *铂金950买价
	 */ 
	public Long getPt950buy(){
		return pt950buy;
	}
	/**
	 *铂金950售价
	 */ 
	public void setPt950sale(Long pt950sale){
		this.pt950sale=pt950sale;
	}
	/**
	 *铂金950售价
	 */ 
	public Long getPt950sale(){
		return pt950sale;
	}
	/**
	 *钯金990买价
	 */ 
	public void setPd990buy(Long pd990buy){
		this.pd990buy=pd990buy;
	}
	/**
	 *钯金990买价
	 */ 
	public Long getPd990buy(){
		return pd990buy;
	}
	/**
	 *钯金990售价
	 */ 
	public void setPd990sale(Long pd990sale){
		this.pd990sale=pd990sale;
	}
	/**
	 *钯金990售价
	 */ 
	public Long getPd990sale(){
		return pd990sale;
	}
	/**
	 *钯金950买价
	 */ 
	public void setPd950buy(Long pd950buy){
		this.pd950buy=pd950buy;
	}
	/**
	 *钯金950买价
	 */ 
	public Long getPd950buy(){
		return pd950buy;
	}
	/**
	 *钯金950售价
	 */ 
	public void setPd950sale(Long pd950sale){
		this.pd950sale=pd950sale;
	}
	/**
	 *钯金950售价
	 */ 
	public Long getPd950sale(){
		return pd950sale;
	}
	/**
	 *千足金买价
	 */ 
	public void setAu1000buy(Long au1000buy){
		this.au1000buy=au1000buy;
	}
	/**
	 *千足金买价
	 */ 
	public Long getAu1000buy(){
		return au1000buy;
	}
	/**
	 *千足金售价
	 */ 
	public void setAu1000sale(Long au1000sale){
		this.au1000sale=au1000sale;
	}
	/**
	 *千足金售价
	 */ 
	public Long getAu1000sale(){
		return au1000sale;
	}
}

