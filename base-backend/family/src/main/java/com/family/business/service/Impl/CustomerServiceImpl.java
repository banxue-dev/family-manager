package com.family.business.service.Impl; 
import com.family.business.entity.Customer; 
import com.family.business.mapper.CustomerMapper; 
import com.family.business.service.ICustomerService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.business.entity.VO.CustomerVO; 
import com.family.business.entity.DO.CustomerDO; 
import com.github.pagehelper.PageHelper; 
import com.family.utils.ResultUtil; 
import com.family.utils.ResultObject; 
import javax.persistence.Transient; 
import org.springframework.transaction.annotation.Transactional; 
import tk.mybatis.mapper.entity.Example; 
import java.util.ArrayList; 
import com.family.utils.TimeUtils; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired; 
import java.util.Map; 
import java.util.List; 
/** 
* Customer服务层 
* Auther:feng
* Date:2020-09-28 15:54:03
*/ 
@Service 
public class CustomerServiceImpl implements ICustomerService { 

	@Autowired
	private CustomerMapper iCustomerMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public CustomerVO getSingleInfo(CustomerDO customerDO) {
		Customer customer=new Customer();
		customer= iCustomerMapper.selectOne(EntityChangeRquestView.createDOToEntity(customerDO,new Customer()));
		return this.structDetailData(customer);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public CustomerVO getSingleInfoById(Integer customerId) {
		Customer customer=new Customer();
		customer= iCustomerMapper.selectByPrimaryKey(customerId);
		return this.structDetailData(customer);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<CustomerVO> getCustomerList(CustomerDO customerDO) {
		  Example example = getPublicExample(customerDO);
		  List<CustomerVO> lstVO = new ArrayList<CustomerVO>();
		  List<Customer> lst = iCustomerMapper.selectByExample(example); 
		lst.forEach(t->{
		  CustomerVO vo=this.structDetailData(t);
		  if(vo!=null) {
		  	lstVO.add(vo);
		  }
		});
	return lstVO;
	} 
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public LayuiPage<CustomerVO> getCustomerListByPage(CustomerDO customerDO, LayuiPage<CustomerVO> layuiPage){
		  Example example = getPublicExample(customerDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<CustomerVO> lstVO = new ArrayList<CustomerVO>();
		  List<Customer> lst = iCustomerMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  CustomerVO vo=this.structDetailData(t);
		  if(vo!=null) {
		  	lstVO.add(vo);
		  }
		});
		pageInfo.setList(lstVO);
		layuiPage = new LayuiPage<>(pageInfo);
		  return layuiPage; 
	}
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public ResultObject delCustomer(Customer customer) {
		 int i= iCustomerMapper.updateByPrimaryKeySelective(customer);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public ResultObject modCustomer(Customer customer) {
		int i= iCustomerMapper.updateByPrimaryKeySelective(customer);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public ResultObject addNewCustomer(Customer customer) {
		customer.setCreateTime(TimeUtils.getCurrentTime());
		int i= iCustomerMapper.insertSelective(customer);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private CustomerVO structDetailData(Customer customer) { 
		 if(customer==null){ 
			 return null; 
		} 
		CustomerVO vo= EntityChangeRquestView.createEntityToVO(customer,new CustomerVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(CustomerDO customerDO) { 
		Example example = new Example(Customer.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(customerDO,new Customer())); 
		return example; 
	}
}
