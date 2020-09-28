package com.family.system.controller; 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.system.entity.Role;
import com.family.system.entity.RoleMenu;
import com.family.system.entity.UserRole;
import com.family.system.entity.DO.RoleAD;
import com.family.system.entity.DO.RoleDO;
import com.family.system.entity.VO.RoleVO;
import com.family.system.mapper.RoleMapper;
import com.family.system.mapper.RoleMenuMapper;
import com.family.system.mapper.UserRoleMapper;
import com.family.system.service.IRoleMenuService;
import com.family.system.service.IRoleService;
import com.family.utils.EntityChangeRquestView;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;  

/** 
* 控制器 
* Auther:feng
* Date:2020-09-17 15:13:11
*/ 
@RestController 
@RequestMapping("role/v1.0") 
@Api(tags = "的接口") 
public class RoleController {  
	@Autowired 
	private IRoleService iRoleService; 
	@Autowired 
	private RoleMapper iRoleMapper; 
	@Autowired 
	private UserRoleMapper iUserRoleMapper; 
	@Autowired 
	private RoleMenuMapper iRoleMenuMapper; 
	@Autowired 
	private IRoleMenuService iRoleMenuService; 


Logger logger=LoggerFactory.getLogger(RoleController.class);	/** 
	* 依据ID获取详情 
	* Auther:feng
	*/ 
	@PostMapping("getRoleSingleById") 
	@ApiOperation("依据ID获取详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleId", value = "的id", required = false,example="1") })
	public  ResultObject getRoleSingleById(Integer roleId) {  
		try{ 
		  RoleVO entity=new RoleVO(); 
		  entity=iRoleService.getSingleInfoById(roleId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取详情异常"); 
		  return ResultUtil.error("异常"); 
		} 
	} 
	/** 
	* 获取单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getRoleSingle") 
	@ApiOperation("获取单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getRoleSingle(RoleDO role) {  
		try{ 
		 RoleVO roleVO=iRoleService.getSingleInfo(role); 
		  return ResultUtil.successData(roleVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取单条记录异常"); 
		  return ResultUtil.error("异常"); 
		} 
	} 
	/** 
	* 获取列表 
	* Auther:feng
	*/ 
	@PostMapping("getRoleList") 
	@ApiOperation("获取列表") 
	@ApiImplicitParams({ })
	public ResultObject getRoleList(RoleDO role) {  
		try{ 
		  List<RoleVO> lst = iRoleService.getRoleList(role); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取列表记录异常"); 
		  return ResultUtil.error("异常"); 
		} 
	} 
	/** 
	* 获取分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getRoleListByPage") 
	@ApiOperation("获取分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getRoleListByPage(RoleDO role,LayuiPage<RoleVO> layuiPage) {  
		try{ 
		  return iRoleService.getRoleListByPage(role, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加方法 
	* Auther:feng
	*/ 
	@PostMapping("addRole") 
	@ApiOperation("添加") 
	@ApiImplicitParams({  })
	public  ResultObject addRole(RoleAD rolead) {  
		try{ 
		  Role role=EntityChangeRquestView.createDOToEntity(rolead, new Role()); 
		  return iRoleService.addNewRole(role); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加异常"); 
		 return ResultUtil.error("异常"); 
		} 
	} 
	/** 
	* 修改方法 
	* Auther:feng
	*/ 
	@PostMapping("modRole") 
	@ApiOperation("修改") 
	@ApiImplicitParams({  })
	public  ResultObject modRole(RoleAD rolead) {  
		try{ 
		  Role role=EntityChangeRquestView.createDOToEntity(rolead, new Role()); 
			  return iRoleService.modRole(role); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改异常"); 
		  return ResultUtil.error("异常"); 
		} 
	} 
	/** 
	* 删除 
	* Auther:feng
	*/ 
	@PostMapping("delRole") 
	@ApiOperation("删除方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "roleIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delRole(String roleIds) {  
		try{ 
			for(String str:roleIds.split(",")) {
				//删除这个角色
				iRoleMapper.deleteByPrimaryKey(Integer.parseInt(str));
				UserRole ur=new UserRole();
				//清理用户拥有的这个角色
				ur.setRoleId(Integer.parseInt(str));
				iUserRoleMapper.delete(ur);
				//清理这个角色拥有的菜单
				RoleMenu mr=new RoleMenu();
				mr.setRoleId(Integer.parseInt(str));
				iRoleMenuMapper.delete(mr);
			}
		  return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"删除方法异常 "); 
		  return ResultUtil.error("异常"); 
		} 
	} 
	/** 
	* 添加角色菜单关联表方法 
	* Auther:feng
	*/ 
	@PostMapping("addRoleMenu") 
	@ApiOperation("添加角色菜单关联表") 
	@ApiImplicitParams({  })
	public  ResultObject addRoleMenu(String menuIds,String roleId) {  
		try{ 
		
		  return iRoleMenuService.addNewRoleMenu( menuIds, roleId); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加角色菜单关联表异常"); 
		 return ResultUtil.error("异常"); 
		} 
	} 
}
