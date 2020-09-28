package com.family.system.service; 
import com.family.system.entity.RoleMenu; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.system.entity.VO.RoleMenuVO; 
import com.family.system.entity.DO.RoleMenuDO; 
import java.util.List;
/** 
* RoleMenu服务接口层 
* Auther:feng
*/ 
public interface IRoleMenuService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public RoleMenuVO getSingleInfo(RoleMenuDO roleMenuDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	RoleMenuVO getSingleInfoById(Integer roleMenuId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<RoleMenuVO> getRoleMenuList(RoleMenuDO roleMenuDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<RoleMenuVO> getRoleMenuListByPage(RoleMenuDO roleMenuDO, LayuiPage<RoleMenuVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delRoleMenu(RoleMenu roleMenu); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modRoleMenu(RoleMenu roleMenu);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewRoleMenu(String menuIds,String roleId);
}
