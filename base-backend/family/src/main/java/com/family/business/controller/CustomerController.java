package com.family.business.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.business.entity.Customer; 
import com.family.business.entity.DO.CustomerAD; 
import com.family.business.entity.VO.CustomerVO; 
import com.family.business.entity.DO.CustomerDO; 
import com.family.business.service.ICustomerService; 
import com.family.business.mapper.CustomerMapper; 
import org.springframework.beans.factory.annotation.Autowired;   
import com.family.utils.EntityChangeRquestView;   
import io.swagger.annotations.Api;   
import io.swagger.annotations.ApiImplicitParam;   
import io.swagger.annotations.ApiImplicitParams;   
import java.util.List;  
import io.swagger.annotations.ApiOperation;  
import com.family.utils.ResultObject;  
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultUtil;  

import com.family.utils.StringUtils;  

/** 
* 业务下的客户控制器 
* Auther:feng
* Date:2020-09-23 15:02:48
*/ 
@RestController 
@RequestMapping("customer/v1.0") 
@Api(tags = "业务下的客户的接口") 
public class CustomerController {  
	@Autowired 
	private ICustomerService iCustomerService; 
	@Autowired 
	private CustomerMapper iCustomerMapper; 


Logger logger=LoggerFactory.getLogger(CustomerController.class);	/** 
	* 依据ID获取业务下的客户详情 
	* Auther:feng
	*/ 
	@PostMapping("getCustomerSingleById") 
	@ApiOperation("依据ID获取业务下的客户详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "customerId", value = "业务下的客户的id", required = false,example="1") })
	public  ResultObject getCustomerSingleById(Integer customerId) {  
		try{ 
		  CustomerVO entity=new CustomerVO(); 
		  entity=iCustomerService.getSingleInfoById(customerId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取业务下的客户详情异常"); 
		  return ResultUtil.error("依据ID获取业务下的客户详情异常"); 
		} 
	} 
	/** 
	* 获取业务下的客户单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getCustomerSingle") 
	@ApiOperation("获取业务下的客户单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getCustomerSingle(CustomerDO customer) {  
		try{ 
		 CustomerVO customerVO=iCustomerService.getSingleInfo(customer); 
		  return ResultUtil.successData(customerVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取业务下的客户单条记录异常"); 
		  return ResultUtil.error("获取业务下的客户单条记录异常"); 
		} 
	} 
	/** 
	 * 获取业务下的客户单条记录 
	 * Auther:feng
	 */ 
	@PostMapping("api/getCustomerSingleByOrgCode") 
	@ApiOperation("获取业务下的客户单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getCustomerSingleByOrgCode(CustomerDO customer) {  
		try{ 
			if(StringUtils.isNull(customer.getOrgCode())) {
				return ResultUtil.error("非法操作"); 
			}
			CustomerVO customerVO=iCustomerService.getSingleInfo(customer); 
			return ResultUtil.successData(customerVO); 
		}catch(Exception e){ 
			logger.error(e+"获取业务下的客户单条记录异常"); 
			return ResultUtil.error("获取业务下的客户单条记录异常"); 
		} 
	} 
	/** 
	* 获取业务下的客户列表 
	* Auther:feng
	*/ 
	@PostMapping("getCustomerList") 
	@ApiOperation("获取业务下的客户列表") 
	@ApiImplicitParams({ })
	public ResultObject getCustomerList(CustomerDO customer) {  
		try{ 
		  List<CustomerVO> lst = iCustomerService.getCustomerList(customer); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取业务下的客户列表记录异常"); 
		  return ResultUtil.error("获取业务下的客户列表记录异常"); 
		} 
	} 
	/** 
	* 获取业务下的客户分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getCustomerListByPage") 
	@ApiOperation("获取业务下的客户分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getCustomerListByPage(CustomerDO customer,LayuiPage<CustomerVO> layuiPage) {  
		try{ 
		  return iCustomerService.getCustomerListByPage(customer, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取业务下的客户分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加业务下的客户方法 
	* Auther:feng
	*/ 
	@PostMapping("addCustomer") 
	@ApiOperation("添加业务下的客户") 
	@ApiImplicitParams({  })
	public  ResultObject addCustomer(CustomerAD customerad) {  
		try{ 
		  Customer customer=EntityChangeRquestView.createDOToEntity(customerad, new Customer()); 
		  return iCustomerService.addNewCustomer(customer); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加业务下的客户异常"); 
		  return ResultUtil.error("添加业务下的客户异常"); 
		} 
	} 
	/** 
	* 修改业务下的客户方法 
	* Auther:feng
	*/ 
	@PostMapping("modCustomer") 
	@ApiOperation("修改业务下的客户") 
	@ApiImplicitParams({  })
	public  ResultObject modCustomer(CustomerAD customerad) {  
		try{ 
		  Customer customer=EntityChangeRquestView.createDOToEntity(customerad, new Customer()); 
			  return iCustomerService.modCustomer(customer); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改业务下的客户异常"); 
		  return ResultUtil.error("修改业务下的客户异常"); 
		} 
	} 
	/** 
	* 删除业务下的客户 
	* Auther:feng
	*/ 
	@PostMapping("delCustomer") 
	@ApiOperation("删除业务下的客户方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "customerIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delCustomer(String customerIds) {  
		try{ 
		  if(StringUtils.isNull(customerIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=customerIds.split(",");
		 for(String str:strs) {
			  iCustomerMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除业务下的客户方法异常 "); 
		  return ResultUtil.error("删除业务下的客户方法异常 "); 
		} 
	} 
}
