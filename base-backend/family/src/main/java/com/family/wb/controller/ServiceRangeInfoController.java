package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.wb.entity.ServiceRangeInfo; 
import com.family.wb.entity.DO.ServiceRangeInfoAD; 
import com.family.wb.entity.VO.ServiceRangeInfoVO; 
import com.family.wb.entity.DO.ServiceRangeInfoDO; 
import com.family.wb.service.IServiceRangeInfoService; 
import com.family.wb.mapper.ServiceRangeInfoMapper; 
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
* Date:2020-12-16 15:41:24
*/ 
@RestController 
@RequestMapping("serviceRangeInfo/v1.0") 
@Api(tags = "服务范畴的接口") 
public class ServiceRangeInfoController {  
	@Autowired 
	private IServiceRangeInfoService iServiceRangeInfoService; 
	@Autowired 
	private ServiceRangeInfoMapper iServiceRangeInfoMapper; 


Logger logger=LoggerFactory.getLogger(ServiceRangeInfoController.class);	/** 
	* 依据ID获取详情 
	* Auther:feng
	*/ 
	@PostMapping("getServiceRangeInfoSingleById") 
	@ApiOperation("依据ID获取详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "serviceRangeInfoId", value = "的id", required = false,example="1") })
	public  ResultObject getServiceRangeInfoSingleById(Integer serviceRangeInfoId) {  
		try{ 
		  ServiceRangeInfoVO entity=new ServiceRangeInfoVO(); 
		  entity=iServiceRangeInfoService.getSingleInfoById(serviceRangeInfoId); 
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
	@PostMapping("api/getServiceRangeInfoSingle") 
	@ApiOperation("获取单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getServiceRangeInfoSingle(ServiceRangeInfoDO serviceRangeInfo) {  
		try{ 
		 ServiceRangeInfoVO serviceRangeInfoVO=iServiceRangeInfoService.getSingleInfo(serviceRangeInfo); 
		  return ResultUtil.successData(serviceRangeInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取单条记录异常"); 
		  return ResultUtil.error("获取单条记录异常"); 
		} 
	} 
	/** 
	* 获取列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getServiceRangeInfoList") 
	@ApiOperation("获取列表") 
	@ApiImplicitParams({ })
	public ResultObject getServiceRangeInfoList(ServiceRangeInfoDO serviceRangeInfo) {  
		try{ 
		  List<ServiceRangeInfoVO> lst = iServiceRangeInfoService.getServiceRangeInfoList(serviceRangeInfo); 
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
	@PostMapping("api/getServiceRangeInfoListByPage") 
	@ApiOperation("获取分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getServiceRangeInfoListByPage(ServiceRangeInfoDO serviceRangeInfo,LayuiPage<ServiceRangeInfoVO> layuiPage) {  
		try{ 
		  return iServiceRangeInfoService.getServiceRangeInfoListByPage(serviceRangeInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加方法 
	* Auther:feng
	*/ 
	@PostMapping("addServiceRangeInfo") 
	@ApiOperation("添加") 
	@ApiImplicitParams({  })
	public  ResultObject addServiceRangeInfo(ServiceRangeInfoAD serviceRangeInfoad) {  
		try{ 
		  ServiceRangeInfo serviceRangeInfo=EntityChangeRquestView.createDOToEntity(serviceRangeInfoad, new ServiceRangeInfo()); 
		  return iServiceRangeInfoService.addNewServiceRangeInfo(serviceRangeInfo); 
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
	@PostMapping("modServiceRangeInfo") 
	@ApiOperation("修改") 
	@ApiImplicitParams({  })
	public  ResultObject modServiceRangeInfo(ServiceRangeInfoAD serviceRangeInfoad) {  
		try{ 
		  ServiceRangeInfo serviceRangeInfo=EntityChangeRquestView.createDOToEntity(serviceRangeInfoad, new ServiceRangeInfo()); 
			  return iServiceRangeInfoService.modServiceRangeInfo(serviceRangeInfo); 
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
	@PostMapping("delServiceRangeInfo") 
	@ApiOperation("删除方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "serviceRangeInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delServiceRangeInfo(String serviceRangeInfoIds) {  
		try{ 
		  if(StringUtils.isNull(serviceRangeInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=serviceRangeInfoIds.split(",");
		 for(String str:strs) {
			  iServiceRangeInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除方法异常 "); 
		  return ResultUtil.error("删除方法异常 "); 
		} 
	} 
}
