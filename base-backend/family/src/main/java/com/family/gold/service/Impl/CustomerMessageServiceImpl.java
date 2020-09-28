package com.family.gold.service.Impl; 
import com.family.gold.entity.CustomerMessage; 
import com.family.gold.mapper.CustomerMessageMapper; 
import com.family.gold.service.ICustomerMessageService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.gold.entity.VO.CustomerMessageVO; 
import com.family.gold.entity.DO.CustomerMessageDO; 
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
* CustomerMessage服务层 
* Auther:feng
* Date:2020-09-18 16:32:02
*/ 
@Service 
public class CustomerMessageServiceImpl implements ICustomerMessageService { 

	@Autowired
	private CustomerMessageMapper iCustomerMessageMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public CustomerMessageVO getSingleInfo(CustomerMessageDO customerMessageDO) {
		CustomerMessage customerMessage=new CustomerMessage();
		customerMessage= iCustomerMessageMapper.selectOne(EntityChangeRquestView.createDOToEntity(customerMessageDO,new CustomerMessage()));
		return this.structDetailData(customerMessage);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public CustomerMessageVO getSingleInfoById(Integer customerMessageId) {
		CustomerMessage customerMessage=new CustomerMessage();
		customerMessage= iCustomerMessageMapper.selectByPrimaryKey(customerMessageId);
		return this.structDetailData(customerMessage);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<CustomerMessageVO> getCustomerMessageList(CustomerMessageDO customerMessageDO) {
		  Example example = getPublicExample(customerMessageDO);
		  List<CustomerMessageVO> lstVO = new ArrayList<CustomerMessageVO>();
		  List<CustomerMessage> lst = iCustomerMessageMapper.selectByExample(example); 
		lst.forEach(t->{
		  CustomerMessageVO vo=this.structDetailData(t);
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
	public LayuiPage<CustomerMessageVO> getCustomerMessageListByPage(CustomerMessageDO customerMessageDO, LayuiPage<CustomerMessageVO> layuiPage){
		  Example example = getPublicExample(customerMessageDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<CustomerMessageVO> lstVO = new ArrayList<CustomerMessageVO>();
		  List<CustomerMessage> lst = iCustomerMessageMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  CustomerMessageVO vo=this.structDetailData(t);
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
	public ResultObject delCustomerMessage(CustomerMessage customerMessage) {
		 int i= iCustomerMessageMapper.updateByPrimaryKeySelective(customerMessage);
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
	public ResultObject modCustomerMessage(CustomerMessage customerMessage) {
		
		int i= iCustomerMessageMapper.updateByPrimaryKeySelective(customerMessage);
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
	public ResultObject addNewCustomerMessage(CustomerMessage customerMessage) {
		customerMessage.setCreateTime(TimeUtils.getCurrentTime());
		int i= iCustomerMessageMapper.insertSelective(customerMessage);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private CustomerMessageVO structDetailData(CustomerMessage customerMessage) { 
		 if(customerMessage==null){ 
			 return null; 
		} 
		CustomerMessageVO vo= EntityChangeRquestView.createEntityToVO(customerMessage,new CustomerMessageVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(CustomerMessageDO customerMessageDO) { 
		Example example = new Example(CustomerMessage.class); 
		Example.Criteria criteria = example.createCriteria(); 
		if(StringUtils.isNotNull(customerMessageDO.getCustomerTrueName())) {
			criteria.andLike("customerTrueName", "%"+customerMessageDO.getCustomerTrueName()+"%");
		}
		customerMessageDO.setCustomerTrueName(null);
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(customerMessageDO,new CustomerMessage())); 
		return example; 
	}
}
