package com.family.gold.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.family.gold.entity.UserDiyMetalConfig; 
import com.family.gold.entity.DO.UserDiyMetalConfigAD; 
import com.family.gold.entity.VO.UserDiyMetalConfigVO; 
import com.family.gold.entity.DO.UserDiyMetalConfigDO; 
import com.family.gold.service.IUserDiyMetalConfigService; 
import com.family.gold.mapper.UserDiyMetalConfigMapper; 
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
import com.family.utils.OrgCodeGreater;
import com.family.utils.ResultUtil;  

import com.family.utils.StringUtils;  

/** 
* 价格数据控制器 
* Auther:feng
* Date:2020-10-22 09:02:06
*/ 
@RestController 
@RequestMapping("userDiyMetalConfig/v1.0") 
@Api(tags = "价格数据的接口") 
public class UserDiyMetalConfigController {  
	@Autowired 
	private IUserDiyMetalConfigService iUserDiyMetalConfigService; 
	@Autowired 
	private UserDiyMetalConfigMapper iUserDiyMetalConfigMapper; 


Logger logger=LoggerFactory.getLogger(UserDiyMetalConfigController.class);	/** 
	* 依据ID获取价格数据详情 
	* Auther:feng
	*/ 
	@PostMapping("getUserDiyMetalConfigSingleById") 
	@ApiOperation("依据ID获取价格数据详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "userDiyMetalConfigId", value = "价格数据的id", required = false,example="1") })
	public  ResultObject getUserDiyMetalConfigSingleById(Long userDiyMetalConfigId) {  
		try{ 
		  UserDiyMetalConfigVO entity=new UserDiyMetalConfigVO(); 
		  entity=iUserDiyMetalConfigService.getSingleInfoById(userDiyMetalConfigId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取价格数据详情异常"); 
		  return ResultUtil.error("依据ID获取价格数据详情异常"); 
		} 
	} 
	/** 
	* 获取价格数据单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getUserDiyMetalConfigSingle") 
	@ApiOperation("获取价格数据单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getUserDiyMetalConfigSingle(UserDiyMetalConfigDO userDiyMetalConfig) {  
		try{ 
		 UserDiyMetalConfigVO userDiyMetalConfigVO=iUserDiyMetalConfigService.getSingleInfo(userDiyMetalConfig); 
		  return ResultUtil.successData(userDiyMetalConfigVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取价格数据单条记录异常"); 
		  return ResultUtil.error("获取价格数据单条记录异常"); 
		} 
	} 
	/** 
	 * 获取价格数据单条记录 
	 * Auther:feng
	 */ 
	@PostMapping("api/getUserDiyWaterByOrgCode") 
	@ApiOperation("依据orgcode获取用户的配置") 
	@ApiImplicitParams({  })
	public ResultObject getUserDiyWaterByOrgCode(UserDiyMetalConfigDO userDiyMetalConfig) {  
		try{ 
			List<UserDiyMetalConfigVO> userDiyMetalConfigVO=iUserDiyMetalConfigService.getSingleInfoByOut(userDiyMetalConfig); 
			return ResultUtil.successData(userDiyMetalConfigVO); 
		}catch(Exception e){ 
			logger.error(e+"获取价格数据单条记录异常"); 
			return ResultUtil.error("获取价格数据单条记录异常"); 
		} 
	} 
	/** 
	* 获取价格数据列表 
	* Auther:feng
	*/ 
	@PostMapping("getUserDiyMetalConfigList") 
	@ApiOperation("获取价格数据列表") 
	@ApiImplicitParams({ })
	public ResultObject getUserDiyMetalConfigList(UserDiyMetalConfigDO userDiyMetalConfig) {  
		try{ 
		  List<UserDiyMetalConfigVO> lst = iUserDiyMetalConfigService.getUserDiyMetalConfigList(userDiyMetalConfig); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取价格数据列表记录异常"); 
		  return ResultUtil.error("获取价格数据列表记录异常"); 
		} 
	} 
	/** 
	* 获取价格数据分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getUserDiyMetalConfigListByPage") 
	@ApiOperation("获取价格数据分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getUserDiyMetalConfigListByPage(UserDiyMetalConfigDO userDiyMetalConfig,LayuiPage<UserDiyMetalConfigVO> layuiPage) {  
		try{ 
		  return iUserDiyMetalConfigService.getUserDiyMetalConfigListByPage(userDiyMetalConfig, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取价格数据分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加价格数据方法 
	* Auther:feng
	*/ 
	@PostMapping("addUserDiyMetalConfig") 
	@ApiOperation("添加价格数据") 
	@ApiImplicitParams({  })
	public  ResultObject addUserDiyMetalConfig(UserDiyMetalConfigAD userDiyMetalConfigad) {  
		try{ 
		  UserDiyMetalConfig userDiyMetalConfig=EntityChangeRquestView.createDOToEntity(userDiyMetalConfigad, new UserDiyMetalConfig()); 
		  return iUserDiyMetalConfigService.addNewUserDiyMetalConfig(userDiyMetalConfig); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加价格数据异常"); 
		  return ResultUtil.error("添加价格数据异常"); 
		} 
	} 
	/** 
	* 修改价格数据方法 
	* Auther:feng
	*/ 
	@PostMapping("modUserDiyMetalConfig") 
	@ApiOperation("修改价格数据") 
	@ApiImplicitParams({  })
	public  ResultObject modUserDiyMetalConfig(UserDiyMetalConfigAD userDiyMetalConfigad) {  
		try{ 
		  UserDiyMetalConfig userDiyMetalConfig=EntityChangeRquestView.createDOToEntity(userDiyMetalConfigad, new UserDiyMetalConfig()); 
			  return iUserDiyMetalConfigService.modUserDiyMetalConfig(userDiyMetalConfig); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改价格数据异常"); 
		  return ResultUtil.error("修改价格数据异常"); 
		} 
	} 
	/** 
	 * 修改价格数据方法 
	 * Auther:feng
	 */ 
	@PostMapping("api/modUserDiyMetalConfigByJson") 
	@ApiOperation("修改价格数据") 
	@ApiImplicitParams({  })
	public  ResultObject modUserDiyMetalConfigByJson(String orgCode,String resv) {  
		try{ 
			if(StringUtils.isNull(resv)) {
				return ResultUtil.error("未做任何改动"); 
			}
			List<UserDiyMetalConfig> lst=JSONArray.parseArray(resv,UserDiyMetalConfig.class);
			for(UserDiyMetalConfig t:lst) {
				t.setOrgCode(OrgCodeGreater.decode(orgCode));
				iUserDiyMetalConfigService.modUserDiyMetalConfig(t); 
			}
			return ResultUtil.success("成功"); 
		}catch(Exception e){ 
			logger.error(e+"修改价格数据异常"); 
			return ResultUtil.error("修改价格数据异常"); 
		} 
	} 
	/** 
	* 删除价格数据 
	* Auther:feng
	*/ 
	@PostMapping("delUserDiyMetalConfig") 
	@ApiOperation("删除价格数据方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "userDiyMetalConfigIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delUserDiyMetalConfig(String userDiyMetalConfigIds) {  
		try{ 
		  if(StringUtils.isNull(userDiyMetalConfigIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=userDiyMetalConfigIds.split(",");
		 for(String str:strs) {
			  iUserDiyMetalConfigMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除价格数据方法异常 "); 
		  return ResultUtil.error("删除价格数据方法异常 "); 
		} 
	} 
}
