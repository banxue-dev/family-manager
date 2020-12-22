package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.wb.entity.FriendshipLinkInfo; 
import com.family.wb.entity.DO.FriendshipLinkInfoAD; 
import com.family.wb.entity.VO.FriendshipLinkInfoVO; 
import com.family.wb.entity.DO.FriendshipLinkInfoDO; 
import com.family.wb.service.IFriendshipLinkInfoService; 
import com.family.wb.mapper.FriendshipLinkInfoMapper; 
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
* Date:2020-12-16 15:41:19
*/ 
@RestController 
@RequestMapping("friendshipLinkInfo/v1.0") 
@Api(tags = "的接口") 
public class FriendshipLinkInfoController {  
	@Autowired 
	private IFriendshipLinkInfoService iFriendshipLinkInfoService; 
	@Autowired 
	private FriendshipLinkInfoMapper iFriendshipLinkInfoMapper; 


Logger logger=LoggerFactory.getLogger(FriendshipLinkInfoController.class);	/** 
	* 依据ID获取详情 
	* Auther:feng
	*/ 
	@PostMapping("getFriendshipLinkInfoSingleById") 
	@ApiOperation("依据ID获取详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "friendshipLinkInfoId", value = "的id", required = false,example="1") })
	public  ResultObject getFriendshipLinkInfoSingleById(Integer friendshipLinkInfoId) {  
		try{ 
		  FriendshipLinkInfoVO entity=new FriendshipLinkInfoVO(); 
		  entity=iFriendshipLinkInfoService.getSingleInfoById(friendshipLinkInfoId); 
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
	@PostMapping("getFriendshipLinkInfoSingle") 
	@ApiOperation("获取单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getFriendshipLinkInfoSingle(FriendshipLinkInfoDO friendshipLinkInfo) {  
		try{ 
		 FriendshipLinkInfoVO friendshipLinkInfoVO=iFriendshipLinkInfoService.getSingleInfo(friendshipLinkInfo); 
		  return ResultUtil.successData(friendshipLinkInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取单条记录异常"); 
		  return ResultUtil.error("获取单条记录异常"); 
		} 
	} 
	/** 
	* 获取列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getFriendshipLinkInfoList") 
	@ApiOperation("获取列表") 
	@ApiImplicitParams({ })
	public ResultObject getFriendshipLinkInfoList(FriendshipLinkInfoDO friendshipLinkInfo) {  
		try{ 
		  List<FriendshipLinkInfoVO> lst = iFriendshipLinkInfoService.getFriendshipLinkInfoList(friendshipLinkInfo); 
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
	@PostMapping("api/getFriendshipLinkInfoListByPage") 
	@ApiOperation("获取分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getFriendshipLinkInfoListByPage(FriendshipLinkInfoDO friendshipLinkInfo,LayuiPage<FriendshipLinkInfoVO> layuiPage) {  
		try{ 
		  return iFriendshipLinkInfoService.getFriendshipLinkInfoListByPage(friendshipLinkInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加方法 
	* Auther:feng
	*/ 
	@PostMapping("addFriendshipLinkInfo") 
	@ApiOperation("添加") 
	@ApiImplicitParams({  })
	public  ResultObject addFriendshipLinkInfo(FriendshipLinkInfoAD friendshipLinkInfoad) {  
		try{ 
		  FriendshipLinkInfo friendshipLinkInfo=EntityChangeRquestView.createDOToEntity(friendshipLinkInfoad, new FriendshipLinkInfo()); 
		  return iFriendshipLinkInfoService.addNewFriendshipLinkInfo(friendshipLinkInfo); 
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
	@PostMapping("modFriendshipLinkInfo") 
	@ApiOperation("修改") 
	@ApiImplicitParams({  })
	public  ResultObject modFriendshipLinkInfo(FriendshipLinkInfoAD friendshipLinkInfoad) {  
		try{ 
		  FriendshipLinkInfo friendshipLinkInfo=EntityChangeRquestView.createDOToEntity(friendshipLinkInfoad, new FriendshipLinkInfo()); 
			  return iFriendshipLinkInfoService.modFriendshipLinkInfo(friendshipLinkInfo); 
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
	@PostMapping("delFriendshipLinkInfo") 
	@ApiOperation("删除方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "friendshipLinkInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delFriendshipLinkInfo(String friendshipLinkInfoIds) {  
		try{ 
		  if(StringUtils.isNull(friendshipLinkInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=friendshipLinkInfoIds.split(",");
		 for(String str:strs) {
			  iFriendshipLinkInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除方法异常 "); 
		  return ResultUtil.error("删除方法异常 "); 
		} 
	} 
}
