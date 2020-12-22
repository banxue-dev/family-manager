package com.family.wb.service; 
import com.family.wb.entity.GroupConfig; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.GroupConfigVO; 
import com.family.wb.entity.DO.GroupConfigDO; 
import java.util.List;
/** 
* GroupConfig服务接口层 
* Auther:feng
*/ 
public interface IGroupConfigService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public GroupConfigVO getSingleInfo(GroupConfigDO groupConfigDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	GroupConfigVO getSingleInfoById(Integer caseGroupId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<GroupConfigVO> getGroupConfigList(GroupConfigDO groupConfigDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<GroupConfigVO> getGroupConfigListByPage(GroupConfigDO groupConfigDO, LayuiPage<GroupConfigVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delGroupConfig(GroupConfig groupConfig); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modGroupConfig(GroupConfig groupConfig);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewGroupConfig(GroupConfig groupConfig);
}
