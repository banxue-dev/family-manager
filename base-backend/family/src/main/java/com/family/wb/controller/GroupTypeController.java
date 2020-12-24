package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.wb.entity.GroupType; 
import com.family.wb.entity.DO.GroupTypeAD; 
import com.family.wb.entity.VO.GroupTypeVO; 
import com.family.wb.entity.DO.GroupTypeDO; 
import com.family.wb.service.IGroupTypeService; 
import com.family.wb.mapper.GroupTypeMapper; 
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
* 所属的类型控制器 
* Auther:feng
* Date:2020-12-24 10:27:02
*/ 
@RestController 
@RequestMapping("groupType/v1.0") 
@Api(tags = "所属的类型的接口") 
public class GroupTypeController {  
	@Autowired 
	private IGroupTypeService iGroupTypeService; 
	@Autowired 
	private GroupTypeMapper iGroupTypeMapper; 


Logger logger=LoggerFactory.getLogger(GroupTypeController.class);	/** 
	* 依据ID获取所属的类型详情 
	* Auther:feng
	*/ 
	@PostMapping("getGroupTypeSingleById") 
	@ApiOperation("依据ID获取所属的类型详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "groupTypeId", value = "所属的类型的id", required = false,example="1") })
	public  ResultObject getGroupTypeSingleById(Integer groupTypeId) {  
		try{ 
		  GroupTypeVO entity=new GroupTypeVO(); 
		  entity=iGroupTypeService.getSingleInfoById(groupTypeId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取所属的类型详情异常"); 
		  return ResultUtil.error("依据ID获取所属的类型详情异常"); 
		} 
	} 
	/** 
	* 获取所属的类型单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getGroupTypeSingle") 
	@ApiOperation("获取所属的类型单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getGroupTypeSingle(GroupTypeDO groupType) {  
		try{ 
		 GroupTypeVO groupTypeVO=iGroupTypeService.getSingleInfo(groupType); 
		  return ResultUtil.successData(groupTypeVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取所属的类型单条记录异常"); 
		  return ResultUtil.error("获取所属的类型单条记录异常"); 
		} 
	} 
	/** 
	* 获取所属的类型列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getGroupTypeList") 
	@ApiOperation("获取所属的类型列表") 
	@ApiImplicitParams({ })
	public ResultObject getGroupTypeList(GroupTypeDO groupType) {  
		try{ 
		  List<GroupTypeVO> lst = iGroupTypeService.getGroupTypeList(groupType); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取所属的类型列表记录异常"); 
		  return ResultUtil.error("获取所属的类型列表记录异常"); 
		} 
	} 
	/** 
	* 获取所属的类型分页数据 
	* Auther:feng
	*/ 
	@PostMapping("api/getGroupTypeListByPage") 
	@ApiOperation("获取所属的类型分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getGroupTypeListByPage(GroupTypeDO groupType,LayuiPage<GroupTypeVO> layuiPage) {  
		try{ 
		  return iGroupTypeService.getGroupTypeListByPage(groupType, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取所属的类型分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加所属的类型方法 
	* Auther:feng
	*/ 
	@PostMapping("addGroupType") 
	@ApiOperation("添加所属的类型") 
	@ApiImplicitParams({  })
	public  ResultObject addGroupType(GroupTypeAD groupTypead) {  
		try{ 
		  GroupType groupType=EntityChangeRquestView.createDOToEntity(groupTypead, new GroupType()); 
		  return iGroupTypeService.addNewGroupType(groupType); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加所属的类型异常"); 
		  return ResultUtil.error("添加所属的类型异常"); 
		} 
	} 
	/** 
	* 修改所属的类型方法 
	* Auther:feng
	*/ 
	@PostMapping("modGroupType") 
	@ApiOperation("修改所属的类型") 
	@ApiImplicitParams({  })
	public  ResultObject modGroupType(GroupTypeAD groupTypead) {  
		try{ 
		  GroupType groupType=EntityChangeRquestView.createDOToEntity(groupTypead, new GroupType()); 
			  return iGroupTypeService.modGroupType(groupType); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改所属的类型异常"); 
		  return ResultUtil.error("修改所属的类型异常"); 
		} 
	} 
	/** 
	* 删除所属的类型 
	* Auther:feng
	*/ 
	@PostMapping("delGroupType") 
	@ApiOperation("删除所属的类型方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "groupTypeIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delGroupType(String groupTypeIds) {  
		try{ 
		  if(StringUtils.isNull(groupTypeIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=groupTypeIds.split(",");
		 for(String str:strs) {
			  iGroupTypeMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除所属的类型方法异常 "); 
		  return ResultUtil.error("删除所属的类型方法异常 "); 
		} 
	} 
}
