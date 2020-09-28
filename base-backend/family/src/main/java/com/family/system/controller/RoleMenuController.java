package com.family.system.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.system.entity.RoleMenu; 
import com.family.system.entity.DO.RoleMenuAD; 
import com.family.system.entity.VO.RoleMenuVO; 
import com.family.system.entity.DO.RoleMenuDO; 
import com.family.system.service.IRoleMenuService; 
import com.family.system.mapper.RoleMenuMapper; 
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

/** 
* 角色菜单关联表控制器 
* Auther:feng
* Date:2020-09-17 15:13:12
*/ 
@RestController 
@RequestMapping("roleMenu/v1.0") 
@Api(tags = "角色菜单关联表的接口") 
public class RoleMenuController {  
	@Autowired 
	private IRoleMenuService iRoleMenuService; 
	@Autowired 
	private RoleMenuMapper iRoleMenuMapper; 


Logger logger=LoggerFactory.getLogger(RoleMenuController.class);	/** 
	* 依据ID获取角色菜单关联表详情 
	* Auther:feng
	*/ 
	@PostMapping("getRoleMenuSingleById") 
	@ApiOperation("依据ID获取角色菜单关联表详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleMenuId", value = "角色菜单关联表的id", required = false,example="1") })
	public  ResultObject getRoleMenuSingleById(Integer roleMenuId) {  
		try{ 
		  RoleMenuVO entity=new RoleMenuVO(); 
		  entity=iRoleMenuService.getSingleInfoById(roleMenuId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取角色菜单关联表详情异常"); 
		  return ResultUtil.error("异常"); 
		} 
	} 
	/** 
	* 获取角色菜单关联表单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getRoleMenuSingle") 
	@ApiOperation("获取角色菜单关联表单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getRoleMenuSingle(RoleMenuDO roleMenu) {  
		try{ 
		 RoleMenuVO roleMenuVO=iRoleMenuService.getSingleInfo(roleMenu); 
		  return ResultUtil.successData(roleMenuVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取角色菜单关联表单条记录异常"); 
		  return ResultUtil.error("异常"); 
		} 
	} 
	/** 
	* 获取角色菜单关联表列表 
	* Auther:feng
	*/ 
	@PostMapping("getRoleMenuList") 
	@ApiOperation("获取角色菜单关联表列表") 
	@ApiImplicitParams({ })
	public ResultObject getRoleMenuList(RoleMenuDO roleMenu) {  
		try{ 
		  List<RoleMenuVO> lst = iRoleMenuService.getRoleMenuList(roleMenu); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取角色菜单关联表列表记录异常"); 
		  return ResultUtil.error("异常"); 
		} 
	} 
	/** 
	* 获取角色菜单关联表分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getRoleMenuListByPage") 
	@ApiOperation("获取角色菜单关联表分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getRoleMenuListByPage(RoleMenuDO roleMenu,LayuiPage<RoleMenuVO> layuiPage) {  
		try{ 
		  return iRoleMenuService.getRoleMenuListByPage(roleMenu, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取角色菜单关联表分页记录异常"); 
		  return layuiPage;
		} 
	} 
	
	/** 
	* 修改角色菜单关联表方法 
	* Auther:feng
	*/ 
	@PostMapping("modRoleMenu") 
	@ApiOperation("修改角色菜单关联表") 
	@ApiImplicitParams({  })
	public  ResultObject modRoleMenu(RoleMenuAD roleMenuad) {  
		try{ 
		  RoleMenu roleMenu=EntityChangeRquestView.createDOToEntity(roleMenuad, new RoleMenu()); 
			  return iRoleMenuService.modRoleMenu(roleMenu); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改角色菜单关联表异常"); 
		  return ResultUtil.error("异常"); 
		} 
	} 
	/** 
	* 删除角色菜单关联表 
	* Auther:feng
	*/ 
	@PostMapping("delRoleMenu") 
	@ApiOperation("删除角色菜单关联表方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleMenuId", value = "主键id数据",example="1", required = false) })
	public  ResultObject delRoleMenu(Integer roleMenuId) {  
		try{ 
		  RoleMenu roleMenu=new RoleMenu(); 
		  roleMenu.setRoleMenuId(roleMenuId); 
		  return iRoleMenuService.delRoleMenu(roleMenu); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"删除角色菜单关联表方法异常 "); 
		  return ResultUtil.error("异常"); 
		} 
	} 
}
