package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.wb.entity.AboutOurInfo; 
import com.family.wb.entity.DO.AboutOurInfoAD; 
import com.family.wb.entity.VO.AboutOurInfoVO; 
import com.family.wb.entity.DO.AboutOurInfoDO; 
import com.family.wb.service.IAboutOurInfoService; 
import com.family.wb.mapper.AboutOurInfoMapper; 
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
* 关于我们，及一些展示控制器 
* Auther:feng
* Date:2020-12-17 10:29:59
*/ 
@RestController 
@RequestMapping("aboutOurInfo/v1.0") 
@Api(tags = "关于我们，及一些展示的接口") 
public class AboutOurInfoController {  
	@Autowired 
	private IAboutOurInfoService iAboutOurInfoService; 
	@Autowired 
	private AboutOurInfoMapper iAboutOurInfoMapper; 


Logger logger=LoggerFactory.getLogger(AboutOurInfoController.class);	/** 
	* 依据ID获取关于我们，及一些展示详情 
	* Auther:feng
	*/ 
	@PostMapping("getAboutOurInfoSingleById") 
	@ApiOperation("依据ID获取关于我们，及一些展示详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "aboutOurInfoId", value = "关于我们，及一些展示的id", required = false,example="1") })
	public  ResultObject getAboutOurInfoSingleById(Integer aboutOurInfoId) {  
		try{ 
		  AboutOurInfoVO entity=new AboutOurInfoVO(); 
		  entity=iAboutOurInfoService.getSingleInfoById(aboutOurInfoId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取关于我们，及一些展示详情异常"); 
		  return ResultUtil.error("依据ID获取关于我们，及一些展示详情异常"); 
		} 
	} 
	/** 
	* 获取关于我们，及一些展示单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getAboutOurInfoSingle") 
	@ApiOperation("获取关于我们，及一些展示单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getAboutOurInfoSingle(AboutOurInfoDO aboutOurInfo) {  
		try{ 
		 AboutOurInfoVO aboutOurInfoVO=iAboutOurInfoService.getSingleInfo(aboutOurInfo); 
		  return ResultUtil.successData(aboutOurInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取关于我们，及一些展示单条记录异常"); 
		  return ResultUtil.error("获取关于我们，及一些展示单条记录异常"); 
		} 
	} 
	/** 
	* 获取关于我们，及一些展示列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getAboutOurInfoList") 
	@ApiOperation("获取关于我们，及一些展示列表") 
	@ApiImplicitParams({ })
	public ResultObject getAboutOurInfoList(AboutOurInfoDO aboutOurInfo) {  
		try{ 
		  List<AboutOurInfoVO> lst = iAboutOurInfoService.getAboutOurInfoList(aboutOurInfo); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取关于我们，及一些展示列表记录异常"); 
		  return ResultUtil.error("获取关于我们，及一些展示列表记录异常"); 
		} 
	} 
	/** 
	* 获取关于我们，及一些展示分页数据 
	* Auther:feng
	*/ 
	@PostMapping("api/getAboutOurInfoListByPage") 
	@ApiOperation("获取关于我们，及一些展示分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getAboutOurInfoListByPage(AboutOurInfoDO aboutOurInfo,LayuiPage<AboutOurInfoVO> layuiPage) {  
		try{ 
		  return iAboutOurInfoService.getAboutOurInfoListByPage(aboutOurInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取关于我们，及一些展示分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加关于我们，及一些展示方法 
	* Auther:feng
	*/ 
	@PostMapping("addAboutOurInfo") 
	@ApiOperation("添加关于我们，及一些展示") 
	@ApiImplicitParams({  })
	public  ResultObject addAboutOurInfo(AboutOurInfoAD aboutOurInfoad) {  
		try{ 
		  AboutOurInfo aboutOurInfo=EntityChangeRquestView.createDOToEntity(aboutOurInfoad, new AboutOurInfo()); 
		  return iAboutOurInfoService.addNewAboutOurInfo(aboutOurInfo); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加关于我们，及一些展示异常"); 
		  return ResultUtil.error("添加关于我们，及一些展示异常"); 
		} 
	} 
	/** 
	* 修改关于我们，及一些展示方法 
	* Auther:feng
	*/ 
	@PostMapping("modAboutOurInfo") 
	@ApiOperation("修改关于我们，及一些展示") 
	@ApiImplicitParams({  })
	public  ResultObject modAboutOurInfo(AboutOurInfoAD aboutOurInfoad) {  
		try{ 
		  AboutOurInfo aboutOurInfo=EntityChangeRquestView.createDOToEntity(aboutOurInfoad, new AboutOurInfo()); 
			  return iAboutOurInfoService.modAboutOurInfo(aboutOurInfo); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改关于我们，及一些展示异常"); 
		  return ResultUtil.error("修改关于我们，及一些展示异常"); 
		} 
	} 
	/** 
	* 删除关于我们，及一些展示 
	* Auther:feng
	*/ 
	@PostMapping("delAboutOurInfo") 
	@ApiOperation("删除关于我们，及一些展示方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "aboutOurInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delAboutOurInfo(String aboutOurInfoIds) {  
		try{ 
		  if(StringUtils.isNull(aboutOurInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=aboutOurInfoIds.split(",");
		 for(String str:strs) {
			  iAboutOurInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除关于我们，及一些展示方法异常 "); 
		  return ResultUtil.error("删除关于我们，及一些展示方法异常 "); 
		} 
	} 
}
