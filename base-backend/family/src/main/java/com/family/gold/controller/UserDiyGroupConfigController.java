package com.family.gold.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.gold.entity.UserDiyGroupConfig; 
import com.family.gold.entity.DO.UserDiyGroupConfigAD; 
import com.family.gold.entity.VO.UserDiyGroupConfigVO; 
import com.family.gold.entity.DO.UserDiyGroupConfigDO; 
import com.family.gold.service.IUserDiyGroupConfigService; 
import com.family.gold.mapper.UserDiyGroupConfigMapper; 
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
* Date:2020-10-21 17:50:47
*/ 
@RestController 
@RequestMapping("userDiyGroupConfig/v1.0") 
@Api(tags = "的接口") 
public class UserDiyGroupConfigController {  
	@Autowired 
	private IUserDiyGroupConfigService iUserDiyGroupConfigService; 
	@Autowired 
	private UserDiyGroupConfigMapper iUserDiyGroupConfigMapper; 


Logger logger=LoggerFactory.getLogger(UserDiyGroupConfigController.class);	/** 
	* 依据ID获取详情 
	* Auther:feng
	*/ 
	@PostMapping("getUserDiyGroupConfigSingleById") 
	@ApiOperation("依据ID获取详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "userDiyGroupConfigId", value = "的id", required = false,example="1") })
	public  ResultObject getUserDiyGroupConfigSingleById(Integer userDiyGroupConfigId) {  
		try{ 
		  UserDiyGroupConfigVO entity=new UserDiyGroupConfigVO(); 
		  entity=iUserDiyGroupConfigService.getSingleInfoById(userDiyGroupConfigId); 
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
	@PostMapping("getUserDiyGroupConfigSingle") 
	@ApiOperation("获取单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getUserDiyGroupConfigSingle(UserDiyGroupConfigDO userDiyGroupConfig) {  
		try{ 
		 UserDiyGroupConfigVO userDiyGroupConfigVO=iUserDiyGroupConfigService.getSingleInfo(userDiyGroupConfig); 
		  return ResultUtil.successData(userDiyGroupConfigVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取单条记录异常"); 
		  return ResultUtil.error("获取单条记录异常"); 
		} 
	} 
	/** 
	* 获取列表 
	* Auther:feng
	*/ 
	@PostMapping("getUserDiyGroupConfigList") 
	@ApiOperation("获取列表") 
	@ApiImplicitParams({ })
	public ResultObject getUserDiyGroupConfigList(UserDiyGroupConfigDO userDiyGroupConfig) {  
		try{ 
		  List<UserDiyGroupConfigVO> lst = iUserDiyGroupConfigService.getUserDiyGroupConfigList(userDiyGroupConfig); 
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
	@PostMapping("getUserDiyGroupConfigListByPage") 
	@ApiOperation("获取分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getUserDiyGroupConfigListByPage(UserDiyGroupConfigDO userDiyGroupConfig,LayuiPage<UserDiyGroupConfigVO> layuiPage) {  
		try{ 
		  return iUserDiyGroupConfigService.getUserDiyGroupConfigListByPage(userDiyGroupConfig, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加方法 
	* Auther:feng
	*/ 
	@PostMapping("addUserDiyGroupConfig") 
	@ApiOperation("添加") 
	@ApiImplicitParams({  })
	public  ResultObject addUserDiyGroupConfig(UserDiyGroupConfigAD userDiyGroupConfigad) {  
		try{ 
		  UserDiyGroupConfig userDiyGroupConfig=EntityChangeRquestView.createDOToEntity(userDiyGroupConfigad, new UserDiyGroupConfig()); 
		  return iUserDiyGroupConfigService.addNewUserDiyGroupConfig(userDiyGroupConfig); 
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
	@PostMapping("modUserDiyGroupConfig") 
	@ApiOperation("修改") 
	@ApiImplicitParams({  })
	public  ResultObject modUserDiyGroupConfig(UserDiyGroupConfigAD userDiyGroupConfigad) {  
		try{ 
		  UserDiyGroupConfig userDiyGroupConfig=EntityChangeRquestView.createDOToEntity(userDiyGroupConfigad, new UserDiyGroupConfig()); 
			  return iUserDiyGroupConfigService.modUserDiyGroupConfig(userDiyGroupConfig); 
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
	@PostMapping("delUserDiyGroupConfig") 
	@ApiOperation("删除方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "userDiyGroupConfigIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delUserDiyGroupConfig(String userDiyGroupConfigIds) {  
		try{ 
		  if(StringUtils.isNull(userDiyGroupConfigIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=userDiyGroupConfigIds.split(",");
		 for(String str:strs) {
			  iUserDiyGroupConfigMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除方法异常 "); 
		  return ResultUtil.error("删除方法异常 "); 
		} 
	} 
}
