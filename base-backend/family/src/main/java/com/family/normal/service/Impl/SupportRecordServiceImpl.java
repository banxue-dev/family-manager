package com.family.normal.service.Impl; 
import com.family.normal.entity.SupportRecord; 
import com.family.normal.mapper.SupportRecordMapper; 
import com.family.normal.service.ISupportRecordService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.normal.entity.VO.SupportRecordVO; 
import com.family.normal.entity.DO.SupportRecordDO; 
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
* SupportRecord服务层 
* Auther:feng
* Date:2020-09-29 17:04:10
*/ 
@Service 
public class SupportRecordServiceImpl implements ISupportRecordService { 

	@Autowired
	private SupportRecordMapper iSupportRecordMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public SupportRecordVO getSingleInfo(SupportRecordDO supportRecordDO) {
		SupportRecord supportRecord=new SupportRecord();
		supportRecord= iSupportRecordMapper.selectOne(EntityChangeRquestView.createDOToEntity(supportRecordDO,new SupportRecord()));
		return this.structDetailData(supportRecord);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public SupportRecordVO getSingleInfoById(Integer supportRecordId) {
		SupportRecord supportRecord=new SupportRecord();
		supportRecord= iSupportRecordMapper.selectByPrimaryKey(supportRecordId);
		return this.structDetailData(supportRecord);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<SupportRecordVO> getSupportRecordList(SupportRecordDO supportRecordDO) {
		  Example example = getPublicExample(supportRecordDO);
		  List<SupportRecordVO> lstVO = new ArrayList<SupportRecordVO>();
		  List<SupportRecord> lst = iSupportRecordMapper.selectByExample(example); 
		lst.forEach(t->{
		  SupportRecordVO vo=this.structDetailData(t);
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
	public LayuiPage<SupportRecordVO> getSupportRecordListByPage(SupportRecordDO supportRecordDO, LayuiPage<SupportRecordVO> layuiPage){
		  Example example = getPublicExample(supportRecordDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<SupportRecordVO> lstVO = new ArrayList<SupportRecordVO>();
		  List<SupportRecord> lst = iSupportRecordMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  SupportRecordVO vo=this.structDetailData(t);
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
	public ResultObject delSupportRecord(SupportRecord supportRecord) {
		 int i= iSupportRecordMapper.updateByPrimaryKeySelective(supportRecord);
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
	public ResultObject modSupportRecord(SupportRecord supportRecord) {
		int i= iSupportRecordMapper.updateByPrimaryKeySelective(supportRecord);
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
	public ResultObject addNewSupportRecord(SupportRecord supportRecord) {
		supportRecord.setCreateTime(TimeUtils.getCurrentTime());
		int i= iSupportRecordMapper.insertSelective(supportRecord);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private SupportRecordVO structDetailData(SupportRecord supportRecord) { 
		 if(supportRecord==null){ 
			 return null; 
		} 
		SupportRecordVO vo= EntityChangeRquestView.createEntityToVO(supportRecord,new SupportRecordVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(SupportRecordDO supportRecordDO) { 
		Example example = new Example(SupportRecord.class); 
		Example.Criteria criteria = example.createCriteria(); 
		if(StringUtils.isNotNull(supportRecordDO.getRecordName())) {
			criteria.andLike("recordName", "%"+supportRecordDO.getRecordName()+"%");
		}
		supportRecordDO.setRecordName(null);
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(supportRecordDO,new SupportRecord())); 
		return example; 
	}
}
