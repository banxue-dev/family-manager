package com.family.system.service; 
import java.util.List;

import com.family.system.entity.UserRole;
import com.family.system.entity.DO.UserRoleAD;
import com.family.system.entity.DO.UserRoleDO;
import com.family.system.entity.VO.TransferUserRoleVO;
import com.family.system.entity.VO.UserRoleVO;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject;
/** 
* UserRole服务接口层 
* Auther:feng
*/ 
public interface IUserRoleService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public UserRoleVO getSingleInfo(UserRoleDO userRoleDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	UserRoleVO getSingleInfoById(Integer userRoleId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<UserRoleVO> getUserRoleList(UserRoleDO userRoleDO);
	public TransferUserRoleVO getTransferRole(UserRoleDO userRoleDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<UserRoleVO> getUserRoleListByPage(UserRoleDO userRoleDO, LayuiPage<UserRoleVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delUserRole(UserRole userRole); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modUserRole(UserRole userRole);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewUserRole(UserRole userRole);
	public ResultObject saveUserTransferRole(UserRoleAD userRole);
}
