package com.family.gold.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.gold.entity.PersonalInfo; 
import com.family.gold.entity.DO.PersonalInfoAD; 
import com.family.gold.entity.VO.PersonalInfoVO; 
import com.family.gold.entity.DO.PersonalInfoDO; 
import com.family.gold.service.IPersonalInfoService; 
import com.family.gold.mapper.PersonalInfoMapper; 
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
* 业务下的客户控制器 
* Auther:feng
* Date:2020-09-28 15:37:24
*/ 
@RestController 
@RequestMapping("personalInfo/v1.0") 
@Api(tags = "行情系统下每个客户自己的信息") 
public class PersonalInfoController {  
	@Autowired 
	private IPersonalInfoService iPersonalInfoService; 
	@Autowired 
	private PersonalInfoMapper iPersonalInfoMapper; 


Logger logger=LoggerFactory.getLogger(PersonalInfoController.class);	/** 
	* 依据ID获取业务下的客户详情 
	* Auther:feng
	*/ 
	@PostMapping("getPersonalInfoSingleById") 
	@ApiOperation("依据ID获取业务下的客户详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "personalInfoId", value = "业务下的客户的id", required = false,example="1") })
	public  ResultObject getPersonalInfoSingleById(Integer personalInfoId) {  
		try{ 
		  PersonalInfoVO entity=new PersonalInfoVO(); 
		  entity=iPersonalInfoService.getSingleInfoById(personalInfoId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取业务下的客户详情异常"); 
		  return ResultUtil.error("依据ID获取业务下的客户详情异常"); 
		} 
	} 
	@PostMapping("api/getPersonalInfoSingleByOrgCode") 
	@ApiOperation("依据组织机构获取业务下的客户详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "orgCode", value = "业务下的客户的orgCode", required = false,example="1") })
	public  ResultObject getPersonalInfoSingleByOrgCode(String orgCode) {  
		try{ 
			if(StringUtils.isNull(orgCode)) {
				return ResultUtil.error("重要参数不正确");
			}
			PersonalInfoDO pi=new PersonalInfoDO();
			pi.setOrgCode(orgCode);
			return ResultUtil.successData(iPersonalInfoService.getSingleInfo(pi)); 
		}catch(Exception e){ 
			logger.error(e+"依据orgCode获取业务下的客户详情异常"); 
			return ResultUtil.error("依据orgCode获取业务下的客户详情异常"); 
		} 
	} 
	/** 
	* 获取业务下的客户单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getPersonalInfoSingle") 
	@ApiOperation("获取业务下的客户单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getPersonalInfoSingle(PersonalInfoDO personalInfo) {  
		try{ 
		 PersonalInfoVO personalInfoVO=iPersonalInfoService.getSingleInfo(personalInfo); 
		  return ResultUtil.successData(personalInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取业务下的客户单条记录异常"); 
		  return ResultUtil.error("获取业务下的客户单条记录异常"); 
		} 
	} 
	/** 
	* 获取业务下的客户列表 
	* Auther:feng
	*/ 
	@PostMapping("getPersonalInfoList") 
	@ApiOperation("获取业务下的客户列表") 
	@ApiImplicitParams({ })
	public ResultObject getPersonalInfoList(PersonalInfoDO personalInfo) {  
		try{ 
		  List<PersonalInfoVO> lst = iPersonalInfoService.getPersonalInfoList(personalInfo); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取业务下的客户列表记录异常"); 
		  return ResultUtil.error("获取业务下的客户列表记录异常"); 
		} 
	} 
	/** 
	* 获取业务下的客户分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getPersonalInfoListByPage") 
	@ApiOperation("获取业务下的客户分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getPersonalInfoListByPage(PersonalInfoDO personalInfo,LayuiPage<PersonalInfoVO> layuiPage) {  
		try{ 
		  return iPersonalInfoService.getPersonalInfoListByPage(personalInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取业务下的客户分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加业务下的客户方法 
	* Auther:feng
	*/ 
	@PostMapping("addPersonalInfo") 
	@ApiOperation("添加业务下的客户") 
	@ApiImplicitParams({  })
	public  ResultObject addPersonalInfo(PersonalInfoAD personalInfoad) {  
		try{ 
		  PersonalInfo personalInfo=EntityChangeRquestView.createDOToEntity(personalInfoad, new PersonalInfo()); 
		  return iPersonalInfoService.addNewPersonalInfo(personalInfo); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加业务下的客户异常"); 
		  return ResultUtil.error("添加业务下的客户异常"); 
		} 
	} 
	/** 
	* 修改业务下的客户方法 
	* Auther:feng
	*/ 
	@PostMapping("modPersonalInfo") 
	@ApiOperation("修改业务下的客户") 
	@ApiImplicitParams({  })
	public  ResultObject modPersonalInfo(PersonalInfoAD personalInfoad) {  
		try{ 
		  PersonalInfo personalInfo=EntityChangeRquestView.createDOToEntity(personalInfoad, new PersonalInfo()); 
			  return iPersonalInfoService.modPersonalInfo(personalInfo); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改业务下的客户异常"); 
		  return ResultUtil.error("修改业务下的客户异常"); 
		} 
	} 
	/** 
	* 删除业务下的客户 
	* Auther:feng
	*/ 
	@PostMapping("delPersonalInfo") 
	@ApiOperation("删除业务下的客户方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "personalInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delPersonalInfo(String personalInfoIds) {  
		try{ 
		  if(StringUtils.isNull(personalInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=personalInfoIds.split(",");
		 for(String str:strs) {
			  iPersonalInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除业务下的客户方法异常 "); 
		  return ResultUtil.error("删除业务下的客户方法异常 "); 
		} 
	} 
}
