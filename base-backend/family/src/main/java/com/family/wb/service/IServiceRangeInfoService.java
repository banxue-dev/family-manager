package com.family.wb.service; 
import com.family.wb.entity.ServiceRangeInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.ServiceRangeInfoVO; 
import com.family.wb.entity.DO.ServiceRangeInfoDO; 
import java.util.List;
/** 
* ServiceRangeInfo服务接口层 
* Auther:feng
*/ 
public interface IServiceRangeInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public ServiceRangeInfoVO getSingleInfo(ServiceRangeInfoDO serviceRangeInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	ServiceRangeInfoVO getSingleInfoById(Integer serviceRangeId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<ServiceRangeInfoVO> getServiceRangeInfoList(ServiceRangeInfoDO serviceRangeInfoDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<ServiceRangeInfoVO> getServiceRangeInfoListByPage(ServiceRangeInfoDO serviceRangeInfoDO, LayuiPage<ServiceRangeInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delServiceRangeInfo(ServiceRangeInfo serviceRangeInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modServiceRangeInfo(ServiceRangeInfo serviceRangeInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewServiceRangeInfo(ServiceRangeInfo serviceRangeInfo);
}
