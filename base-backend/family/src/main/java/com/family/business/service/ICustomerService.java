package com.family.business.service; 
import com.family.business.entity.Customer; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.business.entity.VO.CustomerVO; 
import com.family.business.entity.DO.CustomerDO; 
import java.util.List;
/** 
* Customer服务接口层 
* Auther:feng
*/ 
public interface ICustomerService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public CustomerVO getSingleInfo(CustomerDO customerDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	CustomerVO getSingleInfoById(Integer customerId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<CustomerVO> getCustomerList(CustomerDO customerDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<CustomerVO> getCustomerListByPage(CustomerDO customerDO, LayuiPage<CustomerVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delCustomer(Customer customer); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modCustomer(Customer customer);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewCustomer(Customer customer);
}
