package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.wb.entity.LeavingMessageInfo; 
import com.family.wb.entity.DO.LeavingMessageInfoAD; 
import com.family.wb.entity.VO.LeavingMessageInfoVO; 
import com.family.wb.entity.DO.LeavingMessageInfoDO; 
import com.family.wb.service.ILeavingMessageInfoService; 
import com.family.wb.mapper.LeavingMessageInfoMapper; 
import org.springframework.beans.factory.annotation.Autowired;   
import com.family.utils.EntityChangeRquestView;   
import io.swagger.annotations.Api;   
import io.swagger.annotations.ApiImplicitParam;   
import io.swagger.annotations.ApiImplicitParams;   
import java.util.List;  
import io.swagger.annotations.ApiOperation;  
import com.family.utils.ResultObject;  
import com.family.utils.LayuiPage; 
import com.family.utils.ResultUtil;  

import com.family.utils.StringUtils;  

/** 
* 官网的留言控制器 
* Auther:feng
* Date:2020-12-21 11:22:18
*/ 
@RestController 
@RequestMapping("leavingMessageInfo/v1.0") 
@Api(tags = "官网的留言的接口") 
public class LeavingMessageInfoController {  
	@Autowired 
	private ILeavingMessageInfoService iLeavingMessageInfoService; 
	@Autowired 
	private LeavingMessageInfoMapper iLeavingMessageInfoMapper; 


Logger logger=LoggerFactory.getLogger(LeavingMessageInfoController.class);	/** 
	* 依据ID获取官网的留言详情 
	* Auther:feng
	*/ 
	@PostMapping("getLeavingMessageInfoSingleById") 
	@ApiOperation("依据ID获取官网的留言详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "leavingMessageInfoId", value = "官网的留言的id", required = false,example="1") })
	public  ResultObject getLeavingMessageInfoSingleById(Integer leavingMessageInfoId) {  
		try{ 
		  LeavingMessageInfoVO entity=new LeavingMessageInfoVO(); 
		  entity=iLeavingMessageInfoService.getSingleInfoById(leavingMessageInfoId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取官网的留言详情异常"); 
		  return ResultUtil.error("依据ID获取官网的留言详情异常"); 
		} 
	} 
	/** 
	* 获取官网的留言单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getLeavingMessageInfoSingle") 
	@ApiOperation("获取官网的留言单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getLeavingMessageInfoSingle(LeavingMessageInfoDO leavingMessageInfo) {  
		try{ 
		 LeavingMessageInfoVO leavingMessageInfoVO=iLeavingMessageInfoService.getSingleInfo(leavingMessageInfo); 
		  return ResultUtil.successData(leavingMessageInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网的留言单条记录异常"); 
		  return ResultUtil.error("获取官网的留言单条记录异常"); 
		} 
	} 
	/** 
	* 获取官网的留言列表 
	* Auther:feng
	*/ 
	@PostMapping("getLeavingMessageInfoList") 
	@ApiOperation("获取官网的留言列表") 
	@ApiImplicitParams({ })
	public ResultObject getLeavingMessageInfoList(LeavingMessageInfoDO leavingMessageInfo) {  
		try{ 
		  List<LeavingMessageInfoVO> lst = iLeavingMessageInfoService.getLeavingMessageInfoList(leavingMessageInfo); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网的留言列表记录异常"); 
		  return ResultUtil.error("获取官网的留言列表记录异常"); 
		} 
	} 
	/** 
	* 获取官网的留言分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getLeavingMessageInfoListByPage") 
	@ApiOperation("获取官网的留言分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getLeavingMessageInfoListByPage(LeavingMessageInfoDO leavingMessageInfo,LayuiPage<LeavingMessageInfoVO> layuiPage) {  
		try{ 
		  return iLeavingMessageInfoService.getLeavingMessageInfoListByPage(leavingMessageInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网的留言分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加官网的留言方法 
	* Auther:feng
	*/ 
	@PostMapping("api/addLeavingMessageInfo") 
	@ApiOperation("添加官网的留言") 
	@ApiImplicitParams({  })
	public  ResultObject addLeavingMessageInfo(LeavingMessageInfoAD leavingMessageInfoad) {  
		try{ 
		  LeavingMessageInfo leavingMessageInfo=EntityChangeRquestView.createDOToEntity(leavingMessageInfoad, new LeavingMessageInfo()); 
		  return iLeavingMessageInfoService.addNewLeavingMessageInfo(leavingMessageInfo); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加官网的留言异常"); 
		  return ResultUtil.error("添加官网的留言异常"); 
		} 
	} 
	/** 
	* 修改官网的留言方法 
	* Auther:feng
	*/ 
	@PostMapping("modLeavingMessageInfo") 
	@ApiOperation("修改官网的留言") 
	@ApiImplicitParams({  })
	public  ResultObject modLeavingMessageInfo(LeavingMessageInfoAD leavingMessageInfoad) {  
		try{ 
		  LeavingMessageInfo leavingMessageInfo=EntityChangeRquestView.createDOToEntity(leavingMessageInfoad, new LeavingMessageInfo()); 
			  return iLeavingMessageInfoService.modLeavingMessageInfo(leavingMessageInfo); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改官网的留言异常"); 
		  return ResultUtil.error("修改官网的留言异常"); 
		} 
	} 
	/** 
	* 删除官网的留言 
	* Auther:feng
	*/ 
	@PostMapping("delLeavingMessageInfo") 
	@ApiOperation("删除官网的留言方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "leavingMessageInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delLeavingMessageInfo(String leavingMessageInfoIds) {  
		try{ 
		  if(StringUtils.isNull(leavingMessageInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=leavingMessageInfoIds.split(",");
		 for(String str:strs) {
			  iLeavingMessageInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除官网的留言方法异常 "); 
		  return ResultUtil.error("删除官网的留言方法异常 "); 
		} 
	} 
}
