package com.family.gold.entity.DO; 

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* ChangePrice实体层 
* Auther:feng
* Date:2020-09-21 15:07:56
*/ 

@ApiModel("黄金调价的相关配置")
public class ChangePriceDO {
	private static final Long serialVersionUID = 1L;

	@ApiModelProperty(value = "所属组织机构")
	private String orgCode;

	@ApiModelProperty(value = "0:开盘，1：停盘，2：周末停盘周内开盘",example="1")
	private Integer openStatus;

	@ApiModelProperty(value = "黄金买价",example="1")
	private BigDecimal xaubuy;

	@ApiModelProperty(value = "黄金售价",example="1")
	private BigDecimal xausale;

	@ApiModelProperty(value = "银买价",example="1")
	private BigDecimal xagbuy;

	@ApiModelProperty(value = "银售价",example="1")
	private BigDecimal xagsale;

	@ApiModelProperty(value = "铂金买价",example="1")
	private BigDecimal xptbuy;

	@ApiModelProperty(value = "铂金售价",example="1")
	private BigDecimal xptsale;

	@ApiModelProperty(value = "钯金买价",example="1")
	private BigDecimal xpdbuy;

	@ApiModelProperty(value = "钯金售价",example="1")
	private BigDecimal xpdsale;

	@ApiModelProperty(value = "18k金买价",example="1")
	private BigDecimal au18buy;

	@ApiModelProperty(value = "18K金售价",example="1")
	private BigDecimal au18sale;

	@ApiModelProperty(value = "银925买价",example="1")
	private BigDecimal ag925buy;

	@ApiModelProperty(value = "银925售价",example="1")
	private BigDecimal ag925sale;

	@ApiModelProperty(value = "铂金990买价",example="1")
	private BigDecimal pt990buy;

	@ApiModelProperty(value = "铂金990售价",example="1")
	private BigDecimal pt990sale;

	@ApiModelProperty(value = "铂金950买价",example="1")
	private BigDecimal pt950buy;

	@ApiModelProperty(value = "铂金950售价",example="1")
	private BigDecimal pt950sale;

	@ApiModelProperty(value = "钯金990买价",example="1")
	private BigDecimal pd990buy;

	@ApiModelProperty(value = "钯金990售价",example="1")
	private BigDecimal pd990sale;

	@ApiModelProperty(value = "钯金950买价",example="1")
	private BigDecimal pd950buy;

	@ApiModelProperty(value = "钯金950售价",example="1")
	private BigDecimal pd950sale;

	@ApiModelProperty(value = "千足金买价",example="1")
	private BigDecimal au1000buy;

	@ApiModelProperty(value = "千足金售价",example="1")
	private BigDecimal au1000sale;

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
		return com.family.utils.OrgCodeGreater.decode(this.orgCode);
	}
	public String getEncodeOrgCode() {
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
	public void setXaubuy(BigDecimal xaubuy){
		this.xaubuy=xaubuy;
	}
	/**
	 *黄金买价
	 */ 
	public BigDecimal getXaubuy(){
		return xaubuy;
	}
	/**
	 *黄金售价
	 */ 
	public void setXausale(BigDecimal xausale){
		this.xausale=xausale;
	}
	/**
	 *黄金售价
	 */ 
	public BigDecimal getXausale(){
		return xausale;
	}
	/**
	 *银买价
	 */ 
	public void setXagbuy(BigDecimal xagbuy){
		this.xagbuy=xagbuy;
	}
	/**
	 *银买价
	 */ 
	public BigDecimal getXagbuy(){
		return xagbuy;
	}
	/**
	 *银售价
	 */ 
	public void setXagsale(BigDecimal xagsale){
		this.xagsale=xagsale;
	}
	/**
	 *银售价
	 */ 
	public BigDecimal getXagsale(){
		return xagsale;
	}
	
	public BigDecimal getXptbuy() {
		return xptbuy;
	}
	public void setXptbuy(BigDecimal xptbuy) {
		this.xptbuy = xptbuy;
	}
	public BigDecimal getXptsale() {
		return xptsale;
	}
	public void setXptsale(BigDecimal xptsale) {
		this.xptsale = xptsale;
	}
	public BigDecimal getXpdbuy() {
		return xpdbuy;
	}
	public void setXpdbuy(BigDecimal xpdbuy) {
		this.xpdbuy = xpdbuy;
	}
	public BigDecimal getXpdsale() {
		return xpdsale;
	}
	public void setXpdsale(BigDecimal xpdsale) {
		this.xpdsale = xpdsale;
	}
	/**
	 *18k金买价
	 */ 
	public void setAu18buy(BigDecimal au18buy){
		this.au18buy=au18buy;
	}
	/**
	 *18k金买价
	 */ 
	public BigDecimal getAu18buy(){
		return au18buy;
	}
	/**
	 *18K金售价
	 */ 
	public void setAu18sale(BigDecimal au18sale){
		this.au18sale=au18sale;
	}
	/**
	 *18K金售价
	 */ 
	public BigDecimal getAu18sale(){
		return au18sale;
	}
	/**
	 *银925买价
	 */ 
	public void setAg925buy(BigDecimal ag925buy){
		this.ag925buy=ag925buy;
	}
	/**
	 *银925买价
	 */ 
	public BigDecimal getAg925buy(){
		return ag925buy;
	}
	/**
	 *银925售价
	 */ 
	public void setAg925sale(BigDecimal ag925sale){
		this.ag925sale=ag925sale;
	}
	/**
	 *银925售价
	 */ 
	public BigDecimal getAg925sale(){
		return ag925sale;
	}
	/**
	 *铂金990买价
	 */ 
	public void setPt990buy(BigDecimal pt990buy){
		this.pt990buy=pt990buy;
	}
	/**
	 *铂金990买价
	 */ 
	public BigDecimal getPt990buy(){
		return pt990buy;
	}
	/**
	 *铂金990售价
	 */ 
	public void setPt990sale(BigDecimal pt990sale){
		this.pt990sale=pt990sale;
	}
	/**
	 *铂金990售价
	 */ 
	public BigDecimal getPt990sale(){
		return pt990sale;
	}
	/**
	 *铂金950买价
	 */ 
	public void setPt950buy(BigDecimal pt950buy){
		this.pt950buy=pt950buy;
	}
	/**
	 *铂金950买价
	 */ 
	public BigDecimal getPt950buy(){
		return pt950buy;
	}
	/**
	 *铂金950售价
	 */ 
	public void setPt950sale(BigDecimal pt950sale){
		this.pt950sale=pt950sale;
	}
	/**
	 *铂金950售价
	 */ 
	public BigDecimal getPt950sale(){
		return pt950sale;
	}
	/**
	 *钯金990买价
	 */ 
	public void setPd990buy(BigDecimal pd990buy){
		this.pd990buy=pd990buy;
	}
	/**
	 *钯金990买价
	 */ 
	public BigDecimal getPd990buy(){
		return pd990buy;
	}
	/**
	 *钯金990售价
	 */ 
	public void setPd990sale(BigDecimal pd990sale){
		this.pd990sale=pd990sale;
	}
	/**
	 *钯金990售价
	 */ 
	public BigDecimal getPd990sale(){
		return pd990sale;
	}
	/**
	 *钯金950买价
	 */ 
	public void setPd950buy(BigDecimal pd950buy){
		this.pd950buy=pd950buy;
	}
	/**
	 *钯金950买价
	 */ 
	public BigDecimal getPd950buy(){
		return pd950buy;
	}
	/**
	 *钯金950售价
	 */ 
	public void setPd950sale(BigDecimal pd950sale){
		this.pd950sale=pd950sale;
	}
	/**
	 *钯金950售价
	 */ 
	public BigDecimal getPd950sale(){
		return pd950sale;
	}
	/**
	 *千足金买价
	 */ 
	public void setAu1000buy(BigDecimal au1000buy){
		this.au1000buy=au1000buy;
	}
	/**
	 *千足金买价
	 */ 
	public BigDecimal getAu1000buy(){
		return au1000buy;
	}
	/**
	 *千足金售价
	 */ 
	public void setAu1000sale(BigDecimal au1000sale){
		this.au1000sale=au1000sale;
	}
	/**
	 *千足金售价
	 */ 
	public BigDecimal getAu1000sale(){
		return au1000sale;
	}
}

