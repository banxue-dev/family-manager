package com.family.gold.service; 
import com.family.gold.entity.UserDiyGroupConfig; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.gold.entity.VO.UserDiyGroupConfigVO; 
import com.family.gold.entity.DO.UserDiyGroupConfigDO; 
import java.util.List;
/** 
* UserDiyGroupConfig服务接口层 
* Auther:feng
*/ 
public interface IUserDiyGroupConfigService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public UserDiyGroupConfigVO getSingleInfo(UserDiyGroupConfigDO userDiyGroupConfigDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	UserDiyGroupConfigVO getSingleInfoById(Integer goldUserDiyGroupConfigId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<UserDiyGroupConfigVO> getUserDiyGroupConfigList(UserDiyGroupConfigDO userDiyGroupConfigDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<UserDiyGroupConfigVO> getUserDiyGroupConfigListByPage(UserDiyGroupConfigDO userDiyGroupConfigDO, LayuiPage<UserDiyGroupConfigVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delUserDiyGroupConfig(UserDiyGroupConfig userDiyGroupConfig); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modUserDiyGroupConfig(UserDiyGroupConfig userDiyGroupConfig);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewUserDiyGroupConfig(UserDiyGroupConfig userDiyGroupConfig);
}
