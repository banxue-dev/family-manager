package com.family.wb.service.Impl; 
import com.family.wb.entity.GroupType; 
import com.family.wb.mapper.GroupTypeMapper; 
import com.family.wb.service.IGroupTypeService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.wb.entity.VO.GroupTypeVO; 
import com.family.wb.entity.DO.GroupTypeDO; 
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
* GroupType服务层 
* Auther:feng
* Date:2020-12-24 10:27:02
*/ 
@Service 
public class GroupTypeServiceImpl implements IGroupTypeService { 

	@Autowired
	private GroupTypeMapper iGroupTypeMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public GroupTypeVO getSingleInfo(GroupTypeDO groupTypeDO) {
		GroupType groupType=new GroupType();
		groupType= iGroupTypeMapper.selectOne(EntityChangeRquestView.createDOToEntity(groupTypeDO,new GroupType()));
		return this.structDetailData(groupType);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public GroupTypeVO getSingleInfoById(Integer groupTypeId) {
		GroupType groupType=new GroupType();
		groupType= iGroupTypeMapper.selectByPrimaryKey(groupTypeId);
		return this.structDetailData(groupType);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<GroupTypeVO> getGroupTypeList(GroupTypeDO groupTypeDO) {
		  Example example = getPublicExample(groupTypeDO);
		  List<GroupTypeVO> lstVO = new ArrayList<GroupTypeVO>();
		  List<GroupType> lst = iGroupTypeMapper.selectByExample(example); 
		lst.forEach(t->{
		  GroupTypeVO vo=this.structDetailData(t);
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
	public LayuiPage<GroupTypeVO> getGroupTypeListByPage(GroupTypeDO groupTypeDO, LayuiPage<GroupTypeVO> layuiPage){
		  Example example = getPublicExample(groupTypeDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<GroupTypeVO> lstVO = new ArrayList<GroupTypeVO>();
		  List<GroupType> lst = iGroupTypeMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  GroupTypeVO vo=this.structDetailData(t);
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
	public ResultObject delGroupType(GroupType groupType) {
		 int i= iGroupTypeMapper.updateByPrimaryKeySelective(groupType);
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
	public ResultObject modGroupType(GroupType groupType) {
		int i= iGroupTypeMapper.updateByPrimaryKeySelective(groupType);
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
	public ResultObject addNewGroupType(GroupType groupType) {
		int i= iGroupTypeMapper.insertSelective(groupType);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private GroupTypeVO structDetailData(GroupType groupType) { 
		 if(groupType==null){ 
			 return null; 
		} 
		GroupTypeVO vo= EntityChangeRquestView.createEntityToVO(groupType,new GroupTypeVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(GroupTypeDO groupTypeDO) { 
		Example example = new Example(GroupType.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(groupTypeDO,new GroupType())); 
		return example; 
	}
}
