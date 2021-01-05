package com.family.gold.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import springfox.documentation.annotations.ApiIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.family.business.entity.Customer;
import com.family.gold.entity.ChangePrice;
import com.family.gold.entity.DO.ChangePriceAD;
import com.family.gold.entity.VO.ChangePriceVO;
import com.family.gold.entity.DO.ChangePriceDO;
import com.family.gold.service.IChangePriceService;
import com.family.gold.timer.GoldGetTimes;
import com.family.gold.mapper.ChangePriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.family.utils.EntityChangeRquestView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.text.ParseException;
import java.util.List;
import java.util.Random;

import io.swagger.annotations.ApiOperation;
import com.family.utils.ResultObject;
import com.github.pagehelper.PageInfo;
import com.family.utils.LayuiPage;
import com.family.utils.OrgCodeGreater;
import com.family.utils.ResultUtil;

import com.family.utils.StringUtils;
import com.family.utils.TimeUtils;

/**
 * 黄金通用的接口
 */
@RestController
@RequestMapping("gold/v1.0")
@Api(tags = "黄金通用接口--老版本的获取数据，新版本已经到changePriceController")
public class GoldController {

	Logger logger = LoggerFactory.getLogger(GoldController.class);
	private static Random r = new Random();
	/**
	 * 获取黄金调价的相关配置单条记录 Auther:feng
	 */
	@RequestMapping("NewHtjApi")
	@ApiOperation("获取实行的黄金价格-非调价数据")
	@ApiImplicitParams({})
	public String getTimesGoldData(String orgCode) {
		try {

			if(StringUtils.isNull(orgCode)) {
				return "缺失orgCode";
			}
			ResultObject i=ifValidate(orgCode);
			if(!i.isSuccess()) {
				return "授权已过期，请联系管理员续费";
			}
			if(GoldGetTimes.data.size()<=0) {
				return "[]";
			}
//			int res=r.nextInt(GoldGetTimes.data.size());
			 return GoldGetTimes.data.get(GoldGetTimes.data.size()-1);
		} catch (Exception e) {
			logger.error(e + "获取黄金调价的相关配置单条记录异常");
			return "[]";
		}
	}
	public ResultObject ifValidate(String orgCode) {
		for(Customer org:ChangePriceController.customerList) {
			if(org.getOrgCode().contentEquals(OrgCodeGreater.decode(orgCode))) {
				if(StringUtils.isNull(org.getCustomerValidate())) {
					break;
				}
				try {
					if(TimeUtils.compareDate(TimeUtils.getCurrentTime(), org.getCustomerValidate())==1) {
						break;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					logger.error(e+"判断是否有效时出现时间转换异常");
					return ResultUtil.error("授权已过期");
				}
				//返回还有多久过期
				long abs=TimeUtils.getTimeDiffSecond(TimeUtils.getCurrentTime(), org.getCustomerValidate(), TimeUtils.DATETIME_FORMAT);
				return ResultUtil.success(abs);
			}
		}
		logger.debug("");
		return ResultUtil.error("授权已过期");
	}
	@RequestMapping("getAllTimesGoldData")
	@ApiOperation("获取实行的黄金价格-非调价数据")
	@ApiImplicitParams({})
	public ResultObject getAllTimesGoldData() {
		try {
			if(GoldGetTimes.data.size()<=0) {
				return ResultUtil.error("没有数据");
			}
			int res=r.nextInt(GoldGetTimes.data.size());
			return ResultUtil.successData(GoldGetTimes.data);
		} catch (Exception e) {
			logger.error(e + "获取黄金调价的相关配置单条记录异常");
			return ResultUtil.error("");
		}
	}
	
}
