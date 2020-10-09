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
 * 黄金通用的接口
 */
@RestController
@RequestMapping("gold/v1.0")
@Api(tags = "黄金通用接口")
public class GoldController {

	Logger logger = LoggerFactory.getLogger(GoldController.class);
	private static Random r = new Random();
	/**
	 * 获取黄金调价的相关配置单条记录 Auther:feng
	 */
	@PostMapping("NewHtjApi")
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
	
}
