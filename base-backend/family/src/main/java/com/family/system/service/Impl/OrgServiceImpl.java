package com.family.system.service.Impl; 
import com.family.system.entity.Org; 
import com.family.system.mapper.OrgMapper; 
import com.family.system.service.IOrgService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.system.entity.VO.OrgVO; 
import com.family.system.entity.DO.OrgDO; 
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
import com.family.utils.OrgCodeGreater;
import com.family.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired; 
import java.util.Map; 
import java.util.List; 
/** 
* Org服务层 
* Auther:feng
* Date:2020-09-18 14:50:41
*/ 
@Service 
public class OrgServiceImpl implements IOrgService { 

	@Autowired
	private OrgMapper iOrgMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public OrgVO getSingleInfo(OrgDO orgDO) {
		Org org=new Org();
		org= iOrgMapper.selectOne(EntityChangeRquestView.createDOToEntity(orgDO,new Org()));
		return this.structDetailData(org);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public OrgVO getSingleInfoById(Integer orgId) {
		Org org=new Org();
		org= iOrgMapper.selectByPrimaryKey(orgId);
		return this.structDetailData(org);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<OrgVO> getOrgList(OrgDO orgDO) {
		  Example example = getPublicExample(orgDO);
		  List<OrgVO> lstVO = new ArrayList<OrgVO>();
		  List<Org> lst = iOrgMapper.selectByExample(example); 
		lst.forEach(t->{
		  OrgVO vo=this.structDetailData(t);
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
	public LayuiPage<OrgVO> getOrgListByPage(OrgDO orgDO, LayuiPage<OrgVO> layuiPage){
		  Example example = getPublicExample(orgDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<OrgVO> lstVO = new ArrayList<OrgVO>();
		  List<Org> lst = iOrgMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  OrgVO vo=this.structDetailData(t);
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
	public ResultObject delOrg(Org org) {
		 int i= iOrgMapper.updateByPrimaryKeySelective(org);
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
	public ResultObject modOrg(Org org) {
		org.setOrgCode(null);
		int i= iOrgMapper.updateByPrimaryKeySelective(org);
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
	public ResultObject addNewOrg(Org org) {
		org.setCreateTime(TimeUtils.getCurrentTime());
		
		/**
		 * 1、开始生成orgCode
		 */
		org.setOrgCode(OrgCodeGreater.createCode());
		int i= iOrgMapper.insertSelective(org);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private OrgVO structDetailData(Org org) { 
		 if(org==null){ 
			 return null; 
		} 
		OrgVO vo= EntityChangeRquestView.createEntityToVO(org,new OrgVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(OrgDO orgDO) { 
		Example example = new Example(Org.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(orgDO,new Org())); 
		return example; 
	}
}
