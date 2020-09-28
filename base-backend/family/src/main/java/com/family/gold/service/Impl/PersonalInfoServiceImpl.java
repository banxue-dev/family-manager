package com.family.gold.service.Impl; 
import com.family.gold.entity.PersonalInfo; 
import com.family.gold.mapper.PersonalInfoMapper; 
import com.family.gold.service.IPersonalInfoService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.gold.entity.VO.PersonalInfoVO; 
import com.family.gold.entity.DO.PersonalInfoDO; 
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
* PersonalInfo服务层 
* Auther:feng
* Date:2020-09-28 15:37:24
*/ 
@Service 
public class PersonalInfoServiceImpl implements IPersonalInfoService { 

	@Autowired
	private PersonalInfoMapper iPersonalInfoMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public PersonalInfoVO getSingleInfo(PersonalInfoDO personalInfoDO) {
		PersonalInfo personalInfo=new PersonalInfo();
		personalInfo= iPersonalInfoMapper.selectOne(EntityChangeRquestView.createDOToEntity(personalInfoDO,new PersonalInfo()));
		return this.structDetailData(personalInfo);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public PersonalInfoVO getSingleInfoById(Integer customerId) {
		PersonalInfo personalInfo=new PersonalInfo();
		personalInfo= iPersonalInfoMapper.selectByPrimaryKey(customerId);
		return this.structDetailData(personalInfo);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<PersonalInfoVO> getPersonalInfoList(PersonalInfoDO personalInfoDO) {
		  Example example = getPublicExample(personalInfoDO);
		  List<PersonalInfoVO> lstVO = new ArrayList<PersonalInfoVO>();
		  List<PersonalInfo> lst = iPersonalInfoMapper.selectByExample(example); 
		lst.forEach(t->{
		  PersonalInfoVO vo=this.structDetailData(t);
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
	public LayuiPage<PersonalInfoVO> getPersonalInfoListByPage(PersonalInfoDO personalInfoDO, LayuiPage<PersonalInfoVO> layuiPage){
		  Example example = getPublicExample(personalInfoDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<PersonalInfoVO> lstVO = new ArrayList<PersonalInfoVO>();
		  List<PersonalInfo> lst = iPersonalInfoMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  PersonalInfoVO vo=this.structDetailData(t);
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
	public ResultObject delPersonalInfo(PersonalInfo personalInfo) {
		 int i= iPersonalInfoMapper.updateByPrimaryKeySelective(personalInfo);
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
	public ResultObject modPersonalInfo(PersonalInfo personalInfo) {
		int i= iPersonalInfoMapper.updateByPrimaryKeySelective(personalInfo);
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
	public ResultObject addNewPersonalInfo(PersonalInfo personalInfo) {
		personalInfo.setCreateTime(TimeUtils.getCurrentTime());
		int i= iPersonalInfoMapper.insertSelective(personalInfo);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private PersonalInfoVO structDetailData(PersonalInfo personalInfo) { 
		 if(personalInfo==null){ 
			 return null; 
		} 
		PersonalInfoVO vo= EntityChangeRquestView.createEntityToVO(personalInfo,new PersonalInfoVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(PersonalInfoDO personalInfoDO) { 
		Example example = new Example(PersonalInfo.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(personalInfoDO,new PersonalInfo())); 
		return example; 
	}
}
