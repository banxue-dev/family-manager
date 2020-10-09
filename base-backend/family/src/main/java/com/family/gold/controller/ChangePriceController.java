package com.family.gold.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import springfox.documentation.annotations.ApiIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;
import java.util.Random;

import io.swagger.annotations.ApiOperation;
import com.family.utils.ResultObject;
import com.github.pagehelper.PageInfo;
import com.family.utils.LayuiPage;
import com.family.utils.ResultUtil;

import com.family.utils.StringUtils;

/**
 * 黄金调价的相关配置控制器 Auther:feng Date:2020-09-21 15:07:56
 */
@RestController
@RequestMapping("changePrice/api/v1.0")
@Api(tags = "黄金调价的相关配置的接口")
public class ChangePriceController {
	@Autowired
	private IChangePriceService iChangePriceService;
	@Autowired
	private ChangePriceMapper iChangePriceMapper;

	Logger logger = LoggerFactory.getLogger(ChangePriceController.class);

	/**
	 * 依据ID获取黄金调价的相关配置详情 Auther:feng
	 */


	private static Random r = new Random();
	/**
	 * 获取黄金调价的相关配置单条记录 Auther:feng
	 */
	@PostMapping("getTimesGoldData")
	@ApiOperation("获取实行的黄金价格-非调价数据")
	@ApiImplicitParams({})
	public String getTimesGoldData() {
		try {
			if(GoldGetTimes.data.size()<=0) {
				return "[]";
			}
			int res=r.nextInt(GoldGetTimes.data.size());
			 return GoldGetTimes.data.get(res);
		} catch (Exception e) {
			logger.error(e + "获取黄金调价的相关配置单条记录异常");
			return "[]";
		}
	}
	@PostMapping("getChangePriceSingle")
	@ApiOperation("获取黄金调价的相关配置单条记录")
	@ApiImplicitParams({})
	public ResultObject getChangePriceSingleByOrgCode(ChangePriceDO changePrice) {
		try {
			ChangePriceVO changePriceVO = iChangePriceService.getSingleInfo(changePrice);
			if (changePriceVO != null) {
				changePriceVO.setChangePriceId(null);
			}
			return ResultUtil.successData(changePriceVO);
		} catch (Exception e) {
			logger.error(e + "获取黄金调价的相关配置单条记录异常");
			return ResultUtil.error("获取黄金调价的相关配置单条记录异常");
		}
	}

	/**
	 * 获取黄金调价的相关配置分页数据 Auther:feng
	 */
	@PostMapping("getChangePriceListByPage")
	@ApiOperation("获取黄金调价的相关配置分页数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, example = "1"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, example = "1"),
			@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false, example = "1"),
			@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false, example = "1") })
	public LayuiPage getChangePriceListByPage(ChangePriceDO changePrice, LayuiPage<ChangePriceVO> layuiPage) {
		try {
			return iChangePriceService.getChangePriceListByPage(changePrice, layuiPage);
		} catch (Exception e) {
			logger.error(e + "获取黄金调价的相关配置分页记录异常");
			return layuiPage;
		}
	}

	/**
	 * 修改黄金调价的相关配置方法 Auther:feng
	 */
	@PostMapping("addChangePriceByOrgCode")
	@ApiOperation("修改黄金调价的相关配置")
	@ApiImplicitParams({})
	public ResultObject addChangePriceByOrgCode(ChangePriceAD changePricead) {
		try {
			if(StringUtils.isNull(changePricead.getSourceOrgCode())) {
				return ResultUtil.error("操作非法");
			}
			ChangePrice cp=new ChangePrice();
			cp.setOrgCode(changePricead.getOrgCode());
			cp=iChangePriceMapper.selectOne(cp);
			if(cp!=null) {
				return ResultUtil.error("已存在此组织");
			}
			ChangePrice changePrice = EntityChangeRquestView.createDOToEntity(changePricead, new ChangePrice());
			return iChangePriceService.addNewChangePrice(changePrice);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "修改黄金调价的相关配置异常");
			return ResultUtil.error("修改黄金调价的相关配置异常");
		}
	}

	/**
	 * 修改黄金调价的相关配置方法 Auther:feng
	 */
	@PostMapping("modChangePriceByOrgCode")
	@ApiOperation("修改黄金调价的相关配置")
	@ApiImplicitParams({})
	public ResultObject modChangePriceByOrgCode(ChangePriceAD changePricead) {
		try {
			ChangePrice changePrice = EntityChangeRquestView.createDOToEntity(changePricead, new ChangePrice());
			return iChangePriceService.modChangePrice(changePrice);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "修改黄金调价的相关配置异常");
			return ResultUtil.error("修改黄金调价的相关配置异常");
		}
	}

	/**
	 * 删除黄金调价的相关配置方法 Auther:feng
	 */
	@PostMapping("delChangePrice")
	@ApiOperation("删除黄金调价的相关配置方法 ")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "changePriceIds", value = "主键id数据", example = "1", required = false) })
	public ResultObject delNews(String changePriceIds) {
		try {
			if (StringUtils.isNull(changePriceIds)) {
				return ResultUtil.error("错误的参数");
			}
			String[] strs = changePriceIds.split(",");
			for (String str : strs) {
				iChangePriceMapper.deleteByPrimaryKey(str);
			}
			return ResultUtil.success();
		} catch (Exception e) {
			logger.error(e + "删除黄金的新闻内容方法异常 ");
			return ResultUtil.error("删除黄金的新闻内容方法异常 ");
		}
	}
}
