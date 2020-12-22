package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.wb.entity.NavigationMenuInfo; 
import com.family.wb.entity.DO.NavigationMenuInfoAD; 
import com.family.wb.entity.VO.NavigationMenuInfoVO; 
import com.family.wb.entity.DO.NavigationMenuInfoDO; 
import com.family.wb.service.INavigationMenuInfoService; 
import com.family.wb.mapper.NavigationMenuInfoMapper; 
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
* Date:2020-12-16 15:41:22
*/ 
@RestController 
@RequestMapping("navigationMenuInfo/v1.0") 
@Api(tags = "的接口") 
public class NavigationMenuInfoController {  
	@Autowired 
	private INavigationMenuInfoService iNavigationMenuInfoService; 
	@Autowired 
	private NavigationMenuInfoMapper iNavigationMenuInfoMapper; 


Logger logger=LoggerFactory.getLogger(NavigationMenuInfoController.class);	/** 
	* 依据ID获取详情 
	* Auther:feng
	*/ 
	@PostMapping("getNavigationMenuInfoSingleById") 
	@ApiOperation("依据ID获取详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "navigationMenuInfoId", value = "的id", required = false,example="1") })
	public  ResultObject getNavigationMenuInfoSingleById(Integer navigationMenuInfoId) {  
		try{ 
		  NavigationMenuInfoVO entity=new NavigationMenuInfoVO(); 
		  entity=iNavigationMenuInfoService.getSingleInfoById(navigationMenuInfoId); 
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
	@PostMapping("getNavigationMenuInfoSingle") 
	@ApiOperation("获取单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getNavigationMenuInfoSingle(NavigationMenuInfoDO navigationMenuInfo) {  
		try{ 
		 NavigationMenuInfoVO navigationMenuInfoVO=iNavigationMenuInfoService.getSingleInfo(navigationMenuInfo); 
		  return ResultUtil.successData(navigationMenuInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取单条记录异常"); 
		  return ResultUtil.error("获取单条记录异常"); 
		} 
	} 
	/** 
	* 获取列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getNavigationMenuInfoList") 
	@ApiOperation("获取列表") 
	@ApiImplicitParams({ })
	public ResultObject getNavigationMenuInfoList(NavigationMenuInfoDO navigationMenuInfo) {  
		try{ 
		  List<NavigationMenuInfoVO> lst = iNavigationMenuInfoService.getNavigationMenuInfoList(navigationMenuInfo); 
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
	@PostMapping("api/getNavigationMenuInfoListByPage") 
	@ApiOperation("获取分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getNavigationMenuInfoListByPage(NavigationMenuInfoDO navigationMenuInfo,LayuiPage<NavigationMenuInfoVO> layuiPage) {  
		try{ 
		  return iNavigationMenuInfoService.getNavigationMenuInfoListByPage(navigationMenuInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加方法 
	* Auther:feng
	*/ 
	@PostMapping("addNavigationMenuInfo") 
	@ApiOperation("添加") 
	@ApiImplicitParams({  })
	public  ResultObject addNavigationMenuInfo(NavigationMenuInfoAD navigationMenuInfoad) {  
		try{ 
		  NavigationMenuInfo navigationMenuInfo=EntityChangeRquestView.createDOToEntity(navigationMenuInfoad, new NavigationMenuInfo()); 
		  return iNavigationMenuInfoService.addNewNavigationMenuInfo(navigationMenuInfo); 
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
	@PostMapping("modNavigationMenuInfo") 
	@ApiOperation("修改") 
	@ApiImplicitParams({  })
	public  ResultObject modNavigationMenuInfo(NavigationMenuInfoAD navigationMenuInfoad) {  
		try{ 
		  NavigationMenuInfo navigationMenuInfo=EntityChangeRquestView.createDOToEntity(navigationMenuInfoad, new NavigationMenuInfo()); 
			  return iNavigationMenuInfoService.modNavigationMenuInfo(navigationMenuInfo); 
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
	@PostMapping("delNavigationMenuInfo") 
	@ApiOperation("删除方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "navigationMenuInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delNavigationMenuInfo(String navigationMenuInfoIds) {  
		try{ 
		  if(StringUtils.isNull(navigationMenuInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=navigationMenuInfoIds.split(",");
		 for(String str:strs) {
			  iNavigationMenuInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除方法异常 "); 
		  return ResultUtil.error("删除方法异常 "); 
		} 
	} 
}
