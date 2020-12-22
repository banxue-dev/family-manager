package com.family.wb.service.Impl; 
import com.family.wb.entity.ContactInfo; 
import com.family.wb.mapper.ContactInfoMapper; 
import com.family.wb.service.IContactInfoService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.wb.entity.VO.ContactInfoVO; 
import com.family.wb.entity.DO.ContactInfoDO; 
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
* ContactInfo服务层 
* Auther:feng
* Date:2020-12-16 17:24:05
*/ 
@Service 
public class ContactInfoServiceImpl implements IContactInfoService { 

	@Autowired
	private ContactInfoMapper iContactInfoMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public ContactInfoVO getSingleInfo(ContactInfoDO contactInfoDO) {
		ContactInfo contactInfo=new ContactInfo();
		contactInfo= iContactInfoMapper.selectOne(EntityChangeRquestView.createDOToEntity(contactInfoDO,new ContactInfo()));
		return this.structDetailData(contactInfo);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public ContactInfoVO getSingleInfoById(Integer contactId) {
		ContactInfo contactInfo=new ContactInfo();
		contactInfo= iContactInfoMapper.selectByPrimaryKey(contactId);
		return this.structDetailData(contactInfo);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<ContactInfoVO> getContactInfoList(ContactInfoDO contactInfoDO) {
		  Example example = getPublicExample(contactInfoDO);
		  List<ContactInfoVO> lstVO = new ArrayList<ContactInfoVO>();
		  List<ContactInfo> lst = iContactInfoMapper.selectByExample(example); 
		lst.forEach(t->{
		  ContactInfoVO vo=this.structDetailData(t);
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
	public LayuiPage<ContactInfoVO> getContactInfoListByPage(ContactInfoDO contactInfoDO, LayuiPage<ContactInfoVO> layuiPage){
		  Example example = getPublicExample(contactInfoDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<ContactInfoVO> lstVO = new ArrayList<ContactInfoVO>();
		  List<ContactInfo> lst = iContactInfoMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  ContactInfoVO vo=this.structDetailData(t);
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
	public ResultObject delContactInfo(ContactInfo contactInfo) {
		 int i= iContactInfoMapper.updateByPrimaryKeySelective(contactInfo);
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
	public ResultObject modContactInfo(ContactInfo contactInfo) {
		int i= iContactInfoMapper.updateByPrimaryKeySelective(contactInfo);
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
	public ResultObject addNewContactInfo(ContactInfo contactInfo) {
		ContactInfo has=new ContactInfo();
		has.setOrgCode(contactInfo.getOrgCode());
		int d=iContactInfoMapper.selectCount(has);
		if(d>0) {
			return ResultUtil.error("此公司数据已存在");
		}
		int i= iContactInfoMapper.insertSelective(contactInfo);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private ContactInfoVO structDetailData(ContactInfo contactInfo) { 
		 if(contactInfo==null){ 
			 return null; 
		} 
		ContactInfoVO vo= EntityChangeRquestView.createEntityToVO(contactInfo,new ContactInfoVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(ContactInfoDO contactInfoDO) { 
		Example example = new Example(ContactInfo.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(contactInfoDO,new ContactInfo())); 
		return example; 
	}
}
