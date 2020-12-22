package com.family.wb.service.Impl; 
import com.family.wb.entity.LeavingMessageInfo; 
import com.family.wb.mapper.LeavingMessageInfoMapper; 
import com.family.wb.service.ILeavingMessageInfoService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.wb.entity.VO.LeavingMessageInfoVO; 
import com.family.wb.entity.DO.LeavingMessageInfoDO; 
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
* LeavingMessageInfo服务层 
* Auther:feng
* Date:2020-12-21 11:22:18
*/ 
@Service 
public class LeavingMessageInfoServiceImpl implements ILeavingMessageInfoService { 

	@Autowired
	private LeavingMessageInfoMapper iLeavingMessageInfoMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public LeavingMessageInfoVO getSingleInfo(LeavingMessageInfoDO leavingMessageInfoDO) {
		LeavingMessageInfo leavingMessageInfo=new LeavingMessageInfo();
		leavingMessageInfo= iLeavingMessageInfoMapper.selectOne(EntityChangeRquestView.createDOToEntity(leavingMessageInfoDO,new LeavingMessageInfo()));
		return this.structDetailData(leavingMessageInfo);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public LeavingMessageInfoVO getSingleInfoById(Integer leavingMessageId) {
		LeavingMessageInfo leavingMessageInfo=new LeavingMessageInfo();
		leavingMessageInfo= iLeavingMessageInfoMapper.selectByPrimaryKey(leavingMessageId);
		return this.structDetailData(leavingMessageInfo);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<LeavingMessageInfoVO> getLeavingMessageInfoList(LeavingMessageInfoDO leavingMessageInfoDO) {
		  Example example = getPublicExample(leavingMessageInfoDO);
		  List<LeavingMessageInfoVO> lstVO = new ArrayList<LeavingMessageInfoVO>();
		  List<LeavingMessageInfo> lst = iLeavingMessageInfoMapper.selectByExample(example); 
		lst.forEach(t->{
		  LeavingMessageInfoVO vo=this.structDetailData(t);
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
	public LayuiPage<LeavingMessageInfoVO> getLeavingMessageInfoListByPage(LeavingMessageInfoDO leavingMessageInfoDO, LayuiPage<LeavingMessageInfoVO> layuiPage){
		  Example example = getPublicExample(leavingMessageInfoDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<LeavingMessageInfoVO> lstVO = new ArrayList<LeavingMessageInfoVO>();
		  List<LeavingMessageInfo> lst = iLeavingMessageInfoMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  LeavingMessageInfoVO vo=this.structDetailData(t);
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
	public ResultObject delLeavingMessageInfo(LeavingMessageInfo leavingMessageInfo) {
		 int i= iLeavingMessageInfoMapper.updateByPrimaryKeySelective(leavingMessageInfo);
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
	public ResultObject modLeavingMessageInfo(LeavingMessageInfo leavingMessageInfo) {
		int i= iLeavingMessageInfoMapper.updateByPrimaryKeySelective(leavingMessageInfo);
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
	public ResultObject addNewLeavingMessageInfo(LeavingMessageInfo leavingMessageInfo) {
		leavingMessageInfo.setCreateTime(TimeUtils.getCurrentTime());
		int i= iLeavingMessageInfoMapper.insertSelective(leavingMessageInfo);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private LeavingMessageInfoVO structDetailData(LeavingMessageInfo leavingMessageInfo) { 
		 if(leavingMessageInfo==null){ 
			 return null; 
		} 
		LeavingMessageInfoVO vo= EntityChangeRquestView.createEntityToVO(leavingMessageInfo,new LeavingMessageInfoVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(LeavingMessageInfoDO leavingMessageInfoDO) { 
		Example example = new Example(LeavingMessageInfo.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(leavingMessageInfoDO,new LeavingMessageInfo())); 
		return example; 
	}
}
