package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.wb.entity.CaseInfo; 
import com.family.wb.entity.DO.CaseInfoAD; 
import com.family.wb.entity.VO.CaseInfoVO; 
import com.family.wb.entity.DO.CaseInfoDO; 
import com.family.wb.service.ICaseInfoService; 
import com.family.wb.mapper.CaseInfoMapper; 
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
* 官网上的案例数据控制器 
* Auther:feng
* Date:2020-12-16 15:41:18
*/ 
@RestController 
@RequestMapping("caseInfo/v1.0") 
@Api(tags = "官网上的案例数据的接口") 
public class CaseInfoController {  
	@Autowired 
	private ICaseInfoService iCaseInfoService; 
	@Autowired 
	private CaseInfoMapper iCaseInfoMapper; 


Logger logger=LoggerFactory.getLogger(CaseInfoController.class);	/** 
	* 依据ID获取官网上的案例数据详情 
	* Auther:feng
	*/ 
	@PostMapping("getCaseInfoSingleById") 
	@ApiOperation("依据ID获取官网上的案例数据详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "caseInfoId", value = "官网上的案例数据的id", required = false,example="1") })
	public  ResultObject getCaseInfoSingleById(Integer caseInfoId) {  
		try{ 
		  CaseInfoVO entity=new CaseInfoVO(); 
		  entity=iCaseInfoService.getSingleInfoById(caseInfoId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取官网上的案例数据详情异常"); 
		  return ResultUtil.error("依据ID获取官网上的案例数据详情异常"); 
		} 
	} 
	@PostMapping("api/getCaseInfoDetail") 
	@ApiOperation("依据ID获取官网上的案例数据详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "caseInfoId", value = "官网上的案例数据的id", required = false,example="1") })
	public  ResultObject getCaseInfoDetail(Integer caseInfoId) {  
		try{ 
		  CaseInfoVO entity=new CaseInfoVO(); 
		  entity=iCaseInfoService.getSingleInfoById(caseInfoId); 
		  /**
		   * 获取他的上一条和下一条的数据名称和id
		   */
		  CaseInfo prev=iCaseInfoMapper.getPrevDataById(caseInfoId);
		  if(prev!=null) {
			  entity.setPrevId(prev.getCaseId());
			  entity.setPrevName(prev.getCaseName());
		  }
		  CaseInfo next=iCaseInfoMapper.getNextDataById(caseInfoId);
		  if(next!=null) {
			  entity.setNextId(next.getCaseId());
			  entity.setNextName(next.getCaseName());
		  }
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取官网上的案例数据详情异常"); 
		  return ResultUtil.error("依据ID获取官网上的案例数据详情异常"); 
		} 
	} 
	/** 
	* 获取官网上的案例数据单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getCaseInfoSingle") 
	@ApiOperation("获取官网上的案例数据单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getCaseInfoSingle(CaseInfoDO caseInfo) {  
		try{ 
		 CaseInfoVO caseInfoVO=iCaseInfoService.getSingleInfo(caseInfo); 
		  return ResultUtil.successData(caseInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网上的案例数据单条记录异常"); 
		  return ResultUtil.error("获取官网上的案例数据单条记录异常"); 
		} 
	} 
	/** 
	* 获取官网上的案例数据列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getCaseInfoList") 
	@ApiOperation("获取官网上的案例数据列表") 
	@ApiImplicitParams({ })
	public ResultObject getCaseInfoList(CaseInfoDO caseInfo) {  
		try{ 
		  List<CaseInfoVO> lst = iCaseInfoService.getCaseInfoList(caseInfo); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网上的案例数据列表记录异常"); 
		  return ResultUtil.error("获取官网上的案例数据列表记录异常"); 
		} 
	} 
	/** 
	 * 获取官网上的案例数据列表 
	 * Auther:feng
	 */ 
	@PostMapping("api/getRandomCaseInfoList") 
	@ApiOperation("获取指定数量的随机案例") 
	@ApiImplicitParams({ })
	public ResultObject getRandomCaseInfoList(Integer count) {  
		try{ 
			List<CaseInfoVO> lst = iCaseInfoService.getRandomCaseInfoList(count);
			return ResultUtil.successData(lst); 
		}catch(Exception e){ 
			logger.error(e+"获取官网上的案例数据列表记录异常"); 
			return ResultUtil.error("获取官网上的案例数据列表记录异常"); 
		} 
	} 
	/** 
	* 获取官网上的案例数据分页数据 
	* Auther:feng
	*/ 
	@PostMapping("api/getCaseInfoListByPage") 
	@ApiOperation("获取官网上的案例数据分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getCaseInfoListByPage(CaseInfoDO caseInfo,LayuiPage<CaseInfoVO> layuiPage) {  
		try{ 
		  return iCaseInfoService.getCaseInfoListByPage(caseInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网上的案例数据分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加官网上的案例数据方法 
	* Auther:feng
	*/ 
	@PostMapping("addCaseInfo") 
	@ApiOperation("添加官网上的案例数据") 
	@ApiImplicitParams({  })
	public  ResultObject addCaseInfo(CaseInfoAD caseInfoad) {  
		try{ 
		  CaseInfo caseInfo=EntityChangeRquestView.createDOToEntity(caseInfoad, new CaseInfo()); 
		  return iCaseInfoService.addNewCaseInfo(caseInfo); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加官网上的案例数据异常"); 
		  return ResultUtil.error("添加官网上的案例数据异常"); 
		} 
	} 
	/** 
	* 修改官网上的案例数据方法 
	* Auther:feng
	*/ 
	@PostMapping("modCaseInfo") 
	@ApiOperation("修改官网上的案例数据") 
	@ApiImplicitParams({  })
	public  ResultObject modCaseInfo(CaseInfoAD caseInfoad) {  
		try{ 
		  CaseInfo caseInfo=EntityChangeRquestView.createDOToEntity(caseInfoad, new CaseInfo()); 
			  return iCaseInfoService.modCaseInfo(caseInfo); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改官网上的案例数据异常"); 
		  return ResultUtil.error("修改官网上的案例数据异常"); 
		} 
	} 
	/** 
	* 删除官网上的案例数据 
	* Auther:feng
	*/ 
	@PostMapping("delCaseInfo") 
	@ApiOperation("删除官网上的案例数据方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "caseInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delCaseInfo(String caseInfoIds) {  
		try{ 
		  if(StringUtils.isNull(caseInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=caseInfoIds.split(",");
		 for(String str:strs) {
			  iCaseInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除官网上的案例数据方法异常 "); 
		  return ResultUtil.error("删除官网上的案例数据方法异常 "); 
		} 
	} 
}
