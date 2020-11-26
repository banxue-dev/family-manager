package com.family.gold.service; 
import com.family.gold.entity.UserDiyMetalConfig; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.gold.entity.VO.UserDiyMetalConfigVO; 
import com.family.gold.entity.DO.UserDiyMetalConfigDO; 
import java.util.List;
/** 
* UserDiyMetalConfig服务接口层 
* Auther:feng
*/ 
public interface IUserDiyMetalConfigService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public UserDiyMetalConfigVO getSingleInfo(UserDiyMetalConfigDO userDiyMetalConfigDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	UserDiyMetalConfigVO getSingleInfoById(Long goldUserDiyMetalConfigId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<UserDiyMetalConfigVO> getUserDiyMetalConfigList(UserDiyMetalConfigDO userDiyMetalConfigDO);
	public List<UserDiyMetalConfigVO> getSingleInfoByOut(UserDiyMetalConfigDO userDiyMetalConfigDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<UserDiyMetalConfigVO> getUserDiyMetalConfigListByPage(UserDiyMetalConfigDO userDiyMetalConfigDO, LayuiPage<UserDiyMetalConfigVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delUserDiyMetalConfig(UserDiyMetalConfig userDiyMetalConfig); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modUserDiyMetalConfig(UserDiyMetalConfig userDiyMetalConfig);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewUserDiyMetalConfig(UserDiyMetalConfig userDiyMetalConfig);
	/**
	 * 为指定的组织生成用户数据
	 * @param orgCode
	 */
	void createOrgUseDiyMetalData(String orgCode);
}
