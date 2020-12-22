package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.wb.entity.ContactInfo; 
import com.family.wb.entity.DO.ContactInfoAD; 
import com.family.wb.entity.VO.ContactInfoVO; 
import com.family.wb.entity.DO.ContactInfoDO; 
import com.family.wb.service.IContactInfoService; 
import com.family.wb.mapper.ContactInfoMapper; 
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
* 官网联系方式控制器 
* Auther:feng
* Date:2020-12-16 17:24:05
*/ 
@RestController 
@RequestMapping("contactInfo/v1.0") 
@Api(tags = "官网联系方式的接口") 
public class ContactInfoController {  
	@Autowired 
	private IContactInfoService iContactInfoService; 
	@Autowired 
	private ContactInfoMapper iContactInfoMapper; 


Logger logger=LoggerFactory.getLogger(ContactInfoController.class);	/** 
	* 依据ID获取官网联系方式详情 
	* Auther:feng
	*/ 
	@PostMapping("getContactInfoSingleById") 
	@ApiOperation("依据ID获取官网联系方式详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "contactInfoId", value = "官网联系方式的id", required = false,example="1") })
	public  ResultObject getContactInfoSingleById(Integer contactInfoId) {  
		try{ 
		  ContactInfoVO entity=new ContactInfoVO(); 
		  entity=iContactInfoService.getSingleInfoById(contactInfoId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取官网联系方式详情异常"); 
		  return ResultUtil.error("依据ID获取官网联系方式详情异常"); 
		} 
	} 
	/** 
	* 获取官网联系方式单条记录 
	* Auther:feng
	*/ 
	@PostMapping("api/getContactInfoSingle") 
	@ApiOperation("获取官网联系方式单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getContactInfoSingle(ContactInfoDO contactInfo) {  
		try{ 
		 ContactInfoVO contactInfoVO=iContactInfoService.getSingleInfo(contactInfo); 
		  return ResultUtil.successData(contactInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网联系方式单条记录异常"); 
		  return ResultUtil.error("获取官网联系方式单条记录异常"); 
		} 
	} 
	/** 
	* 获取官网联系方式列表 
	* Auther:feng
	*/ 
	@PostMapping("api/etContactInfoList") 
	@ApiOperation("获取官网联系方式列表") 
	@ApiImplicitParams({ })
	public ResultObject getContactInfoList(ContactInfoDO contactInfo) {  
		try{ 
		  List<ContactInfoVO> lst = iContactInfoService.getContactInfoList(contactInfo); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网联系方式列表记录异常"); 
		  return ResultUtil.error("获取官网联系方式列表记录异常"); 
		} 
	} 
	/** 
	* 获取官网联系方式分页数据 
	* Auther:feng
	*/ 
	@PostMapping("api/getContactInfoListByPage") 
	@ApiOperation("获取官网联系方式分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getContactInfoListByPage(ContactInfoDO contactInfo,LayuiPage<ContactInfoVO> layuiPage) {  
		try{ 
		  return iContactInfoService.getContactInfoListByPage(contactInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网联系方式分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加官网联系方式方法 
	* Auther:feng
	*/ 
	@PostMapping("addContactInfo") 
	@ApiOperation("添加官网联系方式") 
	@ApiImplicitParams({  })
	public  ResultObject addContactInfo(ContactInfoAD contactInfoad) {  
		try{ 
		  ContactInfo contactInfo=EntityChangeRquestView.createDOToEntity(contactInfoad, new ContactInfo()); 
		  return iContactInfoService.addNewContactInfo(contactInfo); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加官网联系方式异常"); 
		  return ResultUtil.error("添加官网联系方式异常"); 
		} 
	} 
	/** 
	* 修改官网联系方式方法 
	* Auther:feng
	*/ 
	@PostMapping("modContactInfo") 
	@ApiOperation("修改官网联系方式") 
	@ApiImplicitParams({  })
	public  ResultObject modContactInfo(ContactInfoAD contactInfoad) {  
		try{ 
		  ContactInfo contactInfo=EntityChangeRquestView.createDOToEntity(contactInfoad, new ContactInfo()); 
			  return iContactInfoService.modContactInfo(contactInfo); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改官网联系方式异常"); 
		  return ResultUtil.error("修改官网联系方式异常"); 
		} 
	} 
	/** 
	* 删除官网联系方式 
	* Auther:feng
	*/ 
	@PostMapping("delContactInfo") 
	@ApiOperation("删除官网联系方式方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "contactInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delContactInfo(String contactInfoIds) {  
		try{ 
		  if(StringUtils.isNull(contactInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=contactInfoIds.split(",");
		 for(String str:strs) {
			  iContactInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除官网联系方式方法异常 "); 
		  return ResultUtil.error("删除官网联系方式方法异常 "); 
		} 
	} 
}
