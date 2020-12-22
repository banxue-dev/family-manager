package com.family.wb.service.Impl; 
import com.family.wb.entity.FriendshipLinkInfo; 
import com.family.wb.mapper.FriendshipLinkInfoMapper; 
import com.family.wb.service.IFriendshipLinkInfoService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.wb.entity.VO.FriendshipLinkInfoVO; 
import com.family.wb.entity.DO.FriendshipLinkInfoDO; 
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
* FriendshipLinkInfo服务层 
* Auther:feng
* Date:2020-12-16 15:41:19
*/ 
@Service 
public class FriendshipLinkInfoServiceImpl implements IFriendshipLinkInfoService { 

	@Autowired
	private FriendshipLinkInfoMapper iFriendshipLinkInfoMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public FriendshipLinkInfoVO getSingleInfo(FriendshipLinkInfoDO friendshipLinkInfoDO) {
		FriendshipLinkInfo friendshipLinkInfo=new FriendshipLinkInfo();
		friendshipLinkInfo= iFriendshipLinkInfoMapper.selectOne(EntityChangeRquestView.createDOToEntity(friendshipLinkInfoDO,new FriendshipLinkInfo()));
		return this.structDetailData(friendshipLinkInfo);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public FriendshipLinkInfoVO getSingleInfoById(Integer friendshipLinkId) {
		FriendshipLinkInfo friendshipLinkInfo=new FriendshipLinkInfo();
		friendshipLinkInfo= iFriendshipLinkInfoMapper.selectByPrimaryKey(friendshipLinkId);
		return this.structDetailData(friendshipLinkInfo);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<FriendshipLinkInfoVO> getFriendshipLinkInfoList(FriendshipLinkInfoDO friendshipLinkInfoDO) {
		  Example example = getPublicExample(friendshipLinkInfoDO);
		  List<FriendshipLinkInfoVO> lstVO = new ArrayList<FriendshipLinkInfoVO>();
		  List<FriendshipLinkInfo> lst = iFriendshipLinkInfoMapper.selectByExample(example); 
		lst.forEach(t->{
		  FriendshipLinkInfoVO vo=this.structDetailData(t);
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
	public LayuiPage<FriendshipLinkInfoVO> getFriendshipLinkInfoListByPage(FriendshipLinkInfoDO friendshipLinkInfoDO, LayuiPage<FriendshipLinkInfoVO> layuiPage){
		  Example example = getPublicExample(friendshipLinkInfoDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<FriendshipLinkInfoVO> lstVO = new ArrayList<FriendshipLinkInfoVO>();
		  List<FriendshipLinkInfo> lst = iFriendshipLinkInfoMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  FriendshipLinkInfoVO vo=this.structDetailData(t);
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
	public ResultObject delFriendshipLinkInfo(FriendshipLinkInfo friendshipLinkInfo) {
		 int i= iFriendshipLinkInfoMapper.updateByPrimaryKeySelective(friendshipLinkInfo);
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
	public ResultObject modFriendshipLinkInfo(FriendshipLinkInfo friendshipLinkInfo) {
		int i= iFriendshipLinkInfoMapper.updateByPrimaryKeySelective(friendshipLinkInfo);
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
	public ResultObject addNewFriendshipLinkInfo(FriendshipLinkInfo friendshipLinkInfo) {
		int i= iFriendshipLinkInfoMapper.insertSelective(friendshipLinkInfo);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private FriendshipLinkInfoVO structDetailData(FriendshipLinkInfo friendshipLinkInfo) { 
		 if(friendshipLinkInfo==null){ 
			 return null; 
		} 
		FriendshipLinkInfoVO vo= EntityChangeRquestView.createEntityToVO(friendshipLinkInfo,new FriendshipLinkInfoVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(FriendshipLinkInfoDO friendshipLinkInfoDO) { 
		Example example = new Example(FriendshipLinkInfo.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(friendshipLinkInfoDO,new FriendshipLinkInfo())); 
		return example; 
	}
}
