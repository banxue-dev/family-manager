package com.family.system.service; 
import com.family.system.entity.Role; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.system.entity.VO.RoleVO; 
import com.family.system.entity.DO.RoleDO; 
import java.util.List;
/** 
* Role服务接口层 
* Auther:feng
*/ 
public interface IRoleService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public RoleVO getSingleInfo(RoleDO roleDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	RoleVO getSingleInfoById(Integer roleId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<RoleVO> getRoleList(RoleDO roleDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<RoleVO> getRoleListByPage(RoleDO roleDO, LayuiPage<RoleVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delRole(Role role); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modRole(Role role);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewRole(Role role);
}
