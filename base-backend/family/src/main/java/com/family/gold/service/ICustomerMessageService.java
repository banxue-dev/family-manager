package com.family.gold.service; 
import com.family.gold.entity.CustomerMessage; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.gold.entity.VO.CustomerMessageVO; 
import com.family.gold.entity.DO.CustomerMessageDO; 
import java.util.List;
/** 
* CustomerMessage服务接口层 
* Auther:feng
*/ 
public interface ICustomerMessageService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public CustomerMessageVO getSingleInfo(CustomerMessageDO customerMessageDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	CustomerMessageVO getSingleInfoById(Integer customerMessageId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<CustomerMessageVO> getCustomerMessageList(CustomerMessageDO customerMessageDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<CustomerMessageVO> getCustomerMessageListByPage(CustomerMessageDO customerMessageDO, LayuiPage<CustomerMessageVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delCustomerMessage(CustomerMessage customerMessage); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modCustomerMessage(CustomerMessage customerMessage);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewCustomerMessage(CustomerMessage customerMessage);
}
