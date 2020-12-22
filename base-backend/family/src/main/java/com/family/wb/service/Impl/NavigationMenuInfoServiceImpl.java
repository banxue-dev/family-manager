package com.family.wb.service.Impl; 
import com.family.wb.entity.NavigationMenuInfo; 
import com.family.wb.mapper.NavigationMenuInfoMapper; 
import com.family.wb.service.INavigationMenuInfoService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.wb.entity.VO.NavigationMenuInfoVO; 
import com.family.wb.entity.DO.NavigationMenuInfoDO; 
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
* NavigationMenuInfo服务层 
* Auther:feng
* Date:2020-12-16 15:41:22
*/ 
@Service 
public class NavigationMenuInfoServiceImpl implements INavigationMenuInfoService { 

	@Autowired
	private NavigationMenuInfoMapper iNavigationMenuInfoMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public NavigationMenuInfoVO getSingleInfo(NavigationMenuInfoDO navigationMenuInfoDO) {
		NavigationMenuInfo navigationMenuInfo=new NavigationMenuInfo();
		navigationMenuInfo= iNavigationMenuInfoMapper.selectOne(EntityChangeRquestView.createDOToEntity(navigationMenuInfoDO,new NavigationMenuInfo()));
		return this.structDetailData(navigationMenuInfo);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public NavigationMenuInfoVO getSingleInfoById(Integer navigationMenuId) {
		NavigationMenuInfo navigationMenuInfo=new NavigationMenuInfo();
		navigationMenuInfo= iNavigationMenuInfoMapper.selectByPrimaryKey(navigationMenuId);
		return this.structDetailData(navigationMenuInfo);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<NavigationMenuInfoVO> getNavigationMenuInfoList(NavigationMenuInfoDO navigationMenuInfoDO) {
		  Example example = getPublicExample(navigationMenuInfoDO);
		  List<NavigationMenuInfoVO> lstVO = new ArrayList<NavigationMenuInfoVO>();
		  List<NavigationMenuInfo> lst = iNavigationMenuInfoMapper.selectByExample(example); 
		lst.forEach(t->{
		  NavigationMenuInfoVO vo=this.structDetailData(t);
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
	public LayuiPage<NavigationMenuInfoVO> getNavigationMenuInfoListByPage(NavigationMenuInfoDO navigationMenuInfoDO, LayuiPage<NavigationMenuInfoVO> layuiPage){
		  Example example = getPublicExample(navigationMenuInfoDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<NavigationMenuInfoVO> lstVO = new ArrayList<NavigationMenuInfoVO>();
		  List<NavigationMenuInfo> lst = iNavigationMenuInfoMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  NavigationMenuInfoVO vo=this.structDetailData(t);
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
	public ResultObject delNavigationMenuInfo(NavigationMenuInfo navigationMenuInfo) {
		 int i= iNavigationMenuInfoMapper.updateByPrimaryKeySelective(navigationMenuInfo);
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
	public ResultObject modNavigationMenuInfo(NavigationMenuInfo navigationMenuInfo) {
		int i= iNavigationMenuInfoMapper.updateByPrimaryKeySelective(navigationMenuInfo);
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
	public ResultObject addNewNavigationMenuInfo(NavigationMenuInfo navigationMenuInfo) {
		int i= iNavigationMenuInfoMapper.insertSelective(navigationMenuInfo);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private NavigationMenuInfoVO structDetailData(NavigationMenuInfo navigationMenuInfo) { 
		 if(navigationMenuInfo==null){ 
			 return null; 
		} 
		NavigationMenuInfoVO vo= EntityChangeRquestView.createEntityToVO(navigationMenuInfo,new NavigationMenuInfoVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(NavigationMenuInfoDO navigationMenuInfoDO) { 
		Example example = new Example(NavigationMenuInfo.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(navigationMenuInfoDO,new NavigationMenuInfo())); 
		return example; 
	}
}
