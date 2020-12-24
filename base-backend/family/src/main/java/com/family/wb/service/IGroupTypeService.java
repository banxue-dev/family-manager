package com.family.wb.service; 
import com.family.wb.entity.GroupType; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.GroupTypeVO; 
import com.family.wb.entity.DO.GroupTypeDO; 
import java.util.List;
/** 
* GroupType服务接口层 
* Auther:feng
*/ 
public interface IGroupTypeService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public GroupTypeVO getSingleInfo(GroupTypeDO groupTypeDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	GroupTypeVO getSingleInfoById(Integer groupTypeId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<GroupTypeVO> getGroupTypeList(GroupTypeDO groupTypeDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<GroupTypeVO> getGroupTypeListByPage(GroupTypeDO groupTypeDO, LayuiPage<GroupTypeVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delGroupType(GroupType groupType); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modGroupType(GroupType groupType);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewGroupType(GroupType groupType);
}
