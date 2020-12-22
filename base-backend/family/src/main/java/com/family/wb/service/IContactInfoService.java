package com.family.wb.service; 
import com.family.wb.entity.ContactInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.ContactInfoVO; 
import com.family.wb.entity.DO.ContactInfoDO; 
import java.util.List;
/** 
* ContactInfo服务接口层 
* Auther:feng
*/ 
public interface IContactInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public ContactInfoVO getSingleInfo(ContactInfoDO contactInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	ContactInfoVO getSingleInfoById(Integer contactId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<ContactInfoVO> getContactInfoList(ContactInfoDO contactInfoDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<ContactInfoVO> getContactInfoListByPage(ContactInfoDO contactInfoDO, LayuiPage<ContactInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delContactInfo(ContactInfo contactInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modContactInfo(ContactInfo contactInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewContactInfo(ContactInfo contactInfo);
}
