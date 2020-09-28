package com.family.gold.service; 
import com.family.gold.entity.PersonalInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.gold.entity.VO.PersonalInfoVO; 
import com.family.gold.entity.DO.PersonalInfoDO; 
import java.util.List;
/** 
* PersonalInfo服务接口层 
* Auther:feng
*/ 
public interface IPersonalInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public PersonalInfoVO getSingleInfo(PersonalInfoDO personalInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	PersonalInfoVO getSingleInfoById(Integer customerId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<PersonalInfoVO> getPersonalInfoList(PersonalInfoDO personalInfoDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<PersonalInfoVO> getPersonalInfoListByPage(PersonalInfoDO personalInfoDO, LayuiPage<PersonalInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delPersonalInfo(PersonalInfo personalInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modPersonalInfo(PersonalInfo personalInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewPersonalInfo(PersonalInfo personalInfo);
}
