package com.family.gold.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.gold.entity.CustomerMessage; 
import com.family.gold.entity.DO.CustomerMessageAD; 
import com.family.gold.entity.VO.CustomerMessageVO; 
import com.family.gold.entity.DO.CustomerMessageDO; 
import com.family.gold.service.ICustomerMessageService; 
import com.family.gold.mapper.CustomerMessageMapper; 
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
* 黄金的业务-客户留言表控制器 
* Auther:feng
* Date:2020-09-18 16:32:02
*/ 
@RestController 
@RequestMapping("customerMessage/v1.0") 
@Api(tags = "黄金的业务-客户留言表的接口") 
public class CustomerMessageController {  
	@Autowired 
	private ICustomerMessageService iCustomerMessageService; 
	@Autowired 
	private CustomerMessageMapper iCustomerMessageMapper; 


Logger logger=LoggerFactory.getLogger(CustomerMessageController.class);	/** 
	* 依据ID获取黄金的业务-客户留言表详情 
	* Auther:feng
	*/ 
	@PostMapping("getCustomerMessageSingleById") 
	@ApiOperation("依据ID获取黄金的业务-客户留言表详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "customerMessageId", value = "黄金的业务-客户留言表的id", required = false,example="1") })
	public  ResultObject getCustomerMessageSingleById(Integer customerMessageId) {  
		try{ 
		  CustomerMessageVO entity=new CustomerMessageVO(); 
		  entity=iCustomerMessageService.getSingleInfoById(customerMessageId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取黄金的业务-客户留言表详情异常"); 
		  return ResultUtil.error("依据ID获取黄金的业务-客户留言表详情异常"); 
		} 
	} 
	/** 
	* 获取黄金的业务-客户留言表单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getCustomerMessageSingle") 
	@ApiOperation("获取黄金的业务-客户留言表单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getCustomerMessageSingle(CustomerMessageDO customerMessage) {  
		try{ 
		 CustomerMessageVO customerMessageVO=iCustomerMessageService.getSingleInfo(customerMessage); 
		  return ResultUtil.successData(customerMessageVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取黄金的业务-客户留言表单条记录异常"); 
		  return ResultUtil.error("获取黄金的业务-客户留言表单条记录异常"); 
		} 
	} 
	/** 
	* 获取黄金的业务-客户留言表列表 
	* Auther:feng
	*/ 
	@PostMapping("getCustomerMessageList") 
	@ApiOperation("获取黄金的业务-客户留言表列表") 
	@ApiImplicitParams({ })
	public ResultObject getCustomerMessageList(CustomerMessageDO customerMessage) {  
		try{ 
		  List<CustomerMessageVO> lst = iCustomerMessageService.getCustomerMessageList(customerMessage); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取黄金的业务-客户留言表列表记录异常"); 
		  return ResultUtil.error("获取黄金的业务-客户留言表列表记录异常"); 
		} 
	} 
	/** 
	* 获取黄金的业务-客户留言表分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getCustomerMessageListByPage") 
	@ApiOperation("获取黄金的业务-客户留言表分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getCustomerMessageListByPage(CustomerMessageDO customerMessage,LayuiPage<CustomerMessageVO> layuiPage) {  
		try{ 
		  return iCustomerMessageService.getCustomerMessageListByPage(customerMessage, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取黄金的业务-客户留言表分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加黄金的业务-客户留言表方法 
	* Auther:feng
	*/ 
	@PostMapping("api/addCustomerMessage") 
	@ApiOperation("添加黄金的业务-客户留言表") 
	@ApiImplicitParams({  })
	public  ResultObject addCustomerMessage(CustomerMessageAD customerMessagead) {  
		try{ 
		  CustomerMessage customerMessage=EntityChangeRquestView.createDOToEntity(customerMessagead, new CustomerMessage()); 
		  return iCustomerMessageService.addNewCustomerMessage(customerMessage); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加黄金的业务-客户留言表异常"); 
		  return ResultUtil.error("添加黄金的业务-客户留言表异常"); 
		} 
	} 
	/** 
	* 修改黄金的业务-客户留言表方法 
	* Auther:feng
	*/ 
	@PostMapping("modCustomerMessage") 
	@ApiOperation("修改黄金的业务-客户留言表") 
	@ApiImplicitParams({  })
	public  ResultObject modCustomerMessage(CustomerMessageAD customerMessagead) {  
		try{ 
		  CustomerMessage customerMessage=EntityChangeRquestView.createDOToEntity(customerMessagead, new CustomerMessage()); 
			  return iCustomerMessageService.modCustomerMessage(customerMessage); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改黄金的业务-客户留言表异常"); 
		  return ResultUtil.error("修改黄金的业务-客户留言表异常"); 
		} 
	} 
	/** 
	* 删除黄金的业务-客户留言表 
	* Auther:feng
	*/ 
	@PostMapping("delCustomerMessage") 
	@ApiOperation("删除黄金的业务-客户留言表方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "customerMessageIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delCustomerMessage(String customerMessageIds) {  
		try{ 
		  if(StringUtils.isNull(customerMessageIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=customerMessageIds.split(",");
		 for(String str:strs) {
			  iCustomerMessageMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除黄金的业务-客户留言表方法异常 "); 
		  return ResultUtil.error("删除黄金的业务-客户留言表方法异常 "); 
		} 
	} 
}
