package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.wb.entity.BannerInfo; 
import com.family.wb.entity.DO.BannerInfoAD; 
import com.family.wb.entity.VO.BannerInfoVO; 
import com.family.wb.entity.DO.BannerInfoDO; 
import com.family.wb.service.IBannerInfoService; 
import com.family.wb.mapper.BannerInfoMapper; 
import org.springframework.beans.factory.annotation.Autowired;   
import com.family.utils.EntityChangeRquestView;   
import io.swagger.annotations.Api;   
import io.swagger.annotations.ApiImplicitParam;   
import io.swagger.annotations.ApiImplicitParams;   
import java.util.List;  
import io.swagger.annotations.ApiOperation;  
import com.family.utils.ResultObject;  
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultUtil;  

import com.family.utils.StringUtils;  

/** 
* 控制器 
* Auther:feng
* Date:2020-12-16 15:41:17
*/ 
@RestController 
@RequestMapping("bannerInfo/v1.0") 
@Api(tags = "的接口") 
public class BannerInfoController {  
	@Autowired 
	private IBannerInfoService iBannerInfoService; 
	@Autowired 
	private BannerInfoMapper iBannerInfoMapper; 


	Logger logger=LoggerFactory.getLogger(BannerInfoController.class);
	/** 
	* 依据ID获取详情 
	* Auther:feng
	*/ 
	@PostMapping("getBannerInfoSingleById") 
	@ApiOperation("依据ID获取详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "bannerInfoId", value = "的id", required = false,example="1") })
	public  ResultObject getBannerInfoSingleById(Integer bannerInfoId) {  
		try{ 
		  BannerInfoVO entity=new BannerInfoVO(); 
		  entity=iBannerInfoService.getSingleInfoById(bannerInfoId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取详情异常"); 
		  return ResultUtil.error("依据ID获取详情异常"); 
		} 
	} 
	/** 
	* 获取单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getBannerInfoSingle") 
	@ApiOperation("获取单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getBannerInfoSingle(BannerInfoDO bannerInfo) {  
		try{ 
		 BannerInfoVO bannerInfoVO=iBannerInfoService.getSingleInfo(bannerInfo); 
		  return ResultUtil.successData(bannerInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取单条记录异常"); 
		  return ResultUtil.error("获取单条记录异常"); 
		} 
	} 
	/** 
	* 获取列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getBannerInfoList") 
	@ApiOperation("获取列表") 
	@ApiImplicitParams({ })
	public ResultObject getBannerInfoList(BannerInfoDO bannerInfo) {  
		try{ 
		  List<BannerInfoVO> lst = iBannerInfoService.getBannerInfoList(bannerInfo); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取列表记录异常"); 
		  return ResultUtil.error("获取列表记录异常"); 
		} 
	} 
	/** 
	* 获取分页数据 
	* Auther:feng
	*/ 
	@PostMapping("api/getBannerInfoListByPage") 
	@ApiOperation("获取分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getBannerInfoListByPage(BannerInfoDO bannerInfo,LayuiPage<BannerInfoVO> layuiPage) {  
		try{ 
		  return iBannerInfoService.getBannerInfoListByPage(bannerInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加方法 
	* Auther:feng
	*/ 
	@PostMapping("addBannerInfo") 
	@ApiOperation("添加") 
	@ApiImplicitParams({  })
	public  ResultObject addBannerInfo(BannerInfoAD bannerInfoad) {  
		try{ 
		  BannerInfo bannerInfo=EntityChangeRquestView.createDOToEntity(bannerInfoad, new BannerInfo()); 
		  return iBannerInfoService.addNewBannerInfo(bannerInfo); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加异常"); 
		  return ResultUtil.error("添加异常"); 
		} 
	} 
	/** 
	* 修改方法 
	* Auther:feng
	*/ 
	@PostMapping("modBannerInfo") 
	@ApiOperation("修改") 
	@ApiImplicitParams({  })
	public  ResultObject modBannerInfo(BannerInfoAD bannerInfoad) {  
		try{ 
		  BannerInfo bannerInfo=EntityChangeRquestView.createDOToEntity(bannerInfoad, new BannerInfo()); 
			  return iBannerInfoService.modBannerInfo(bannerInfo); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改异常"); 
		  return ResultUtil.error("修改异常"); 
		} 
	} 
	/** 
	* 删除 
	* Auther:feng
	*/ 
	@PostMapping("delBannerInfo") 
	@ApiOperation("删除方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "bannerInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delBannerInfo(String bannerInfoIds) {  
		try{ 
		  if(StringUtils.isNull(bannerInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=bannerInfoIds.split(",");
		 for(String str:strs) {
			  iBannerInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除方法异常 "); 
		  return ResultUtil.error("删除方法异常 "); 
		} 
	} 
}
