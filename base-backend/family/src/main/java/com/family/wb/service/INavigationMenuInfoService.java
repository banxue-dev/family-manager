package com.family.wb.service; 
import com.family.wb.entity.NavigationMenuInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.NavigationMenuInfoVO; 
import com.family.wb.entity.DO.NavigationMenuInfoDO; 
import java.util.List;
/** 
* NavigationMenuInfo服务接口层 
* Auther:feng
*/ 
public interface INavigationMenuInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public NavigationMenuInfoVO getSingleInfo(NavigationMenuInfoDO navigationMenuInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	NavigationMenuInfoVO getSingleInfoById(Integer navigationMenuId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<NavigationMenuInfoVO> getNavigationMenuInfoList(NavigationMenuInfoDO navigationMenuInfoDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<NavigationMenuInfoVO> getNavigationMenuInfoListByPage(NavigationMenuInfoDO navigationMenuInfoDO, LayuiPage<NavigationMenuInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delNavigationMenuInfo(NavigationMenuInfo navigationMenuInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modNavigationMenuInfo(NavigationMenuInfo navigationMenuInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewNavigationMenuInfo(NavigationMenuInfo navigationMenuInfo);
}
