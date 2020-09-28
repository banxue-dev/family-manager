package com.family.system.service; 
import com.family.system.entity.User; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.system.entity.VO.UserVO; 
import com.family.system.entity.DO.UserDO; 
import java.util.List;
/** 
* User服务接口层 
* Auther:feng
*/ 
public interface IUserService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public UserVO getSingleInfo(UserDO userDO);
	public UserVO login(User user);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	UserVO getSingleInfoById(Integer userId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<UserVO> getUserList(UserDO userDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<UserVO> getUserListByPage(UserDO userDO, LayuiPage<UserVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delUser(User user); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modUser(User user);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewUser(User user);
}
