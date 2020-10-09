package com.family.system.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.system.entity.Org; 
import com.family.system.entity.DO.OrgAD; 
import com.family.system.entity.VO.OrgVO; 
import com.family.system.entity.DO.OrgDO; 
import com.family.system.service.IOrgService; 
import com.family.system.mapper.OrgMapper; 
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
* 用户所属组织控制器 
* Auther:feng
* Date:2020-09-18 14:50:41
*/ 
@RestController 
@RequestMapping("org/v1.0") 
@Api(tags = "用户所属组织的接口") 
public class OrgController {  
	@Autowired 
	private IOrgService iOrgService; 
	@Autowired 
	private OrgMapper iOrgMapper; 


Logger logger=LoggerFactory.getLogger(OrgController.class);	/** 
	* 依据ID获取用户所属组织详情 
	* Auther:feng
	*/ 
	@PostMapping("getOrgSingleById") 
	@ApiOperation("依据ID获取用户所属组织详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "orgId", value = "用户所属组织的id", required = false,example="1") })
	public  ResultObject getOrgSingleById(Integer orgId) {  
		try{ 
		  OrgVO entity=new OrgVO(); 
		  entity=iOrgService.getSingleInfoById(orgId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取用户所属组织详情异常"); 
		  return ResultUtil.error("依据ID获取用户所属组织详情异常"); 
		} 
	} 
	/** 
	* 获取用户所属组织单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getOrgSingle") 
	@ApiOperation("获取用户所属组织单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getOrgSingle(OrgDO org) {  
		try{ 
		 OrgVO orgVO=iOrgService.getSingleInfo(org); 
		  return ResultUtil.successData(orgVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取用户所属组织单条记录异常"); 
		  return ResultUtil.error("获取用户所属组织单条记录异常"); 
		} 
	} 
	/** 
	 * 获取用户所属组织单条记录 
	 * Auther:feng
	 */ 
	@PostMapping("api/getOrgSingleByOrgCode") 
	@ApiOperation("获取用户所属组织单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getOrgSingleByOrgCode(OrgDO org) {  
		try{ 
			if(StringUtils.isNull(org.getOrgCode())) {
				return ResultUtil.error("非法操作"); 
			}
			OrgVO orgVO=iOrgService.getSingleInfo(org); 
			return ResultUtil.successData(orgVO); 
		}catch(Exception e){ 
			logger.error(e+"获取用户所属组织单条记录异常"); 
			return ResultUtil.error("获取用户所属组织单条记录异常"); 
		} 
	} 
	/** 
	* 获取用户所属组织列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getOrgList") 
	@ApiOperation("获取用户所属组织列表") 
	@ApiImplicitParams({ })
	public ResultObject getOrgList(OrgDO org) {  
		try{ 
		  List<OrgVO> lst = iOrgService.getOrgList(org); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取用户所属组织列表记录异常"); 
		  return ResultUtil.error("获取用户所属组织列表记录异常"); 
		} 
	} 
	/** 
	* 获取用户所属组织列表 
	* Auther:feng
	*/ 
	@PostMapping("getOrgList") 
	@ApiOperation("获取用户所属组织列表-需要登录") 
	@ApiImplicitParams({ })
	public ResultObject getOrgListAuth(OrgDO org) {  
		try{ 
		  List<OrgVO> lst = iOrgService.getOrgList(org); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取用户所属组织列表记录异常"); 
		  return ResultUtil.error("获取用户所属组织列表记录异常"); 
		} 
	} 
	/** 
	* 获取用户所属组织分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getOrgListByPage") 
	@ApiOperation("获取用户所属组织分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getOrgListByPage(OrgDO org,LayuiPage<OrgVO> layuiPage) {  
		try{ 
		  return iOrgService.getOrgListByPage(org, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取用户所属组织分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加用户所属组织方法 
	* Auther:feng
	*/ 
	@PostMapping("addOrg") 
	@ApiOperation("添加用户所属组织") 
	@ApiImplicitParams({  })
	public  ResultObject addOrg(OrgAD orgad) {  
		try{ 
		  Org org=EntityChangeRquestView.createDOToEntity(orgad, new Org()); 
		  return iOrgService.addNewOrg(org); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加用户所属组织异常"); 
		  return ResultUtil.error("添加用户所属组织异常"); 
		} 
	} 
	/** 
	* 修改用户所属组织方法 
	* Auther:feng
	*/ 
	@PostMapping("modOrg") 
	@ApiOperation("修改用户所属组织") 
	@ApiImplicitParams({  })
	public  ResultObject modOrg(OrgAD orgad) {  
		try{ 
		  Org org=EntityChangeRquestView.createDOToEntity(orgad, new Org()); 
			  return iOrgService.modOrg(org); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改用户所属组织异常"); 
		  return ResultUtil.error("修改用户所属组织异常"); 
		} 
	} 
	/** 
	* 删除用户所属组织 
	* Auther:feng
	*/ 
	@PostMapping("delOrg") 
	@ApiOperation("删除用户所属组织方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "orgIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delOrg(String orgIds) {  
		try{ 
		  if(StringUtils.isNull(orgIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=orgIds.split(",");
		 for(String str:strs) {
			  iOrgMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除用户所属组织方法异常 "); 
		  return ResultUtil.error("删除用户所属组织方法异常 "); 
		} 
	} 
}
