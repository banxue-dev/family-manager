package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.wb.entity.GroupConfig; 
import com.family.wb.entity.DO.GroupConfigAD; 
import com.family.wb.entity.VO.GroupConfigVO; 
import com.family.wb.entity.DO.GroupConfigDO; 
import com.family.wb.service.IGroupConfigService; 
import com.family.wb.mapper.GroupConfigMapper; 
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
* 里面是官网的所有的分组数据控制器 
* Auther:feng
* Date:2020-12-16 15:41:20
*/ 
@RestController 
@RequestMapping("groupConfig/v1.0") 
@Api(tags = "里面是官网的所有的分组数据的接口") 
public class GroupConfigController {  
	@Autowired 
	private IGroupConfigService iGroupConfigService; 
	@Autowired 
	private GroupConfigMapper iGroupConfigMapper; 


Logger logger=LoggerFactory.getLogger(GroupConfigController.class);	/** 
	* 依据ID获取里面是官网的所有的分组数据详情 
	* Auther:feng
	*/ 
	@PostMapping("getGroupConfigSingleById") 
	@ApiOperation("依据ID获取里面是官网的所有的分组数据详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "groupConfigId", value = "里面是官网的所有的分组数据的id", required = false,example="1") })
	public  ResultObject getGroupConfigSingleById(Integer groupConfigId) {  
		try{ 
		  GroupConfigVO entity=new GroupConfigVO(); 
		  entity=iGroupConfigService.getSingleInfoById(groupConfigId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取里面是官网的所有的分组数据详情异常"); 
		  return ResultUtil.error("依据ID获取里面是官网的所有的分组数据详情异常"); 
		} 
	} 
	/** 
	* 获取里面是官网的所有的分组数据单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getGroupConfigSingle") 
	@ApiOperation("获取里面是官网的所有的分组数据单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getGroupConfigSingle(GroupConfigDO groupConfig) {  
		try{ 
		 GroupConfigVO groupConfigVO=iGroupConfigService.getSingleInfo(groupConfig); 
		  return ResultUtil.successData(groupConfigVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取里面是官网的所有的分组数据单条记录异常"); 
		  return ResultUtil.error("获取里面是官网的所有的分组数据单条记录异常"); 
		} 
	} 
	/** 
	* 获取里面是官网的所有的分组数据列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getGroupConfigList") 
	@ApiOperation("获取里面是官网的所有的分组数据列表") 
	@ApiImplicitParams({ })
	public ResultObject getGroupConfigList(GroupConfigDO groupConfig) {  
		try{ 
		  List<GroupConfigVO> lst = iGroupConfigService.getGroupConfigList(groupConfig); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取里面是官网的所有的分组数据列表记录异常"); 
		  return ResultUtil.error("获取里面是官网的所有的分组数据列表记录异常"); 
		} 
	} 
	/** 
	* 获取里面是官网的所有的分组数据分页数据 
	* Auther:feng
	*/ 
	@PostMapping("api/getGroupConfigListByPage") 
	@ApiOperation("获取里面是官网的所有的分组数据分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getGroupConfigListByPage(GroupConfigDO groupConfig,LayuiPage<GroupConfigVO> layuiPage) {  
		try{ 
		  return iGroupConfigService.getGroupConfigListByPage(groupConfig, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取里面是官网的所有的分组数据分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加里面是官网的所有的分组数据方法 
	* Auther:feng
	*/ 
	@PostMapping("addGroupConfig") 
	@ApiOperation("添加里面是官网的所有的分组数据") 
	@ApiImplicitParams({  })
	public  ResultObject addGroupConfig(GroupConfigAD groupConfigad) {  
		try{ 
		  GroupConfig groupConfig=EntityChangeRquestView.createDOToEntity(groupConfigad, new GroupConfig()); 
		  return iGroupConfigService.addNewGroupConfig(groupConfig); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加里面是官网的所有的分组数据异常"); 
		  return ResultUtil.error("添加里面是官网的所有的分组数据异常"); 
		} 
	} 
	/** 
	* 修改里面是官网的所有的分组数据方法 
	* Auther:feng
	*/ 
	@PostMapping("modGroupConfig") 
	@ApiOperation("修改里面是官网的所有的分组数据") 
	@ApiImplicitParams({  })
	public  ResultObject modGroupConfig(GroupConfigAD groupConfigad) {  
		try{ 
		  GroupConfig groupConfig=EntityChangeRquestView.createDOToEntity(groupConfigad, new GroupConfig()); 
			  return iGroupConfigService.modGroupConfig(groupConfig); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改里面是官网的所有的分组数据异常"); 
		  return ResultUtil.error("修改里面是官网的所有的分组数据异常"); 
		} 
	} 
	/** 
	* 删除里面是官网的所有的分组数据 
	* Auther:feng
	*/ 
	@PostMapping("delGroupConfig") 
	@ApiOperation("删除里面是官网的所有的分组数据方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "groupConfigIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delGroupConfig(String groupConfigIds) {  
		try{ 
		  if(StringUtils.isNull(groupConfigIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=groupConfigIds.split(",");
		 for(String str:strs) {
			  iGroupConfigMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除里面是官网的所有的分组数据方法异常 "); 
		  return ResultUtil.error("删除里面是官网的所有的分组数据方法异常 "); 
		} 
	} 
}
