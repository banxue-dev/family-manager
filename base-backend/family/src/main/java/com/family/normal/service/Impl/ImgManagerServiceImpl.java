package com.family.normal.service.Impl; 
import com.family.normal.entity.ImgManager; 
import com.family.normal.mapper.ImgManagerMapper; 
import com.family.normal.service.IImgManagerService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.normal.entity.VO.ImgManagerVO; 
import com.family.normal.entity.DO.ImgManagerDO; 
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
* ImgManager服务层 
* Auther:feng
* Date:2020-12-16 15:47:27
*/ 
@Service 
public class ImgManagerServiceImpl implements IImgManagerService { 

	@Autowired
	private ImgManagerMapper iImgManagerMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public ImgManagerVO getSingleInfo(ImgManagerDO imgManagerDO) {
		ImgManager imgManager=new ImgManager();
		imgManager= iImgManagerMapper.selectOne(EntityChangeRquestView.createDOToEntity(imgManagerDO,new ImgManager()));
		return this.structDetailData(imgManager);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public ImgManagerVO getSingleInfoById(Integer normalImgManagerId) {
		ImgManager imgManager=new ImgManager();
		imgManager= iImgManagerMapper.selectByPrimaryKey(normalImgManagerId);
		return this.structDetailData(imgManager);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<ImgManagerVO> getImgManagerList(ImgManagerDO imgManagerDO) {
		  Example example = getPublicExample(imgManagerDO);
		  List<ImgManagerVO> lstVO = new ArrayList<ImgManagerVO>();
		  List<ImgManager> lst = iImgManagerMapper.selectByExample(example); 
		lst.forEach(t->{
		  ImgManagerVO vo=this.structDetailData(t);
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
	public LayuiPage<ImgManagerVO> getImgManagerListByPage(ImgManagerDO imgManagerDO, LayuiPage<ImgManagerVO> layuiPage){
		  Example example = getPublicExample(imgManagerDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<ImgManagerVO> lstVO = new ArrayList<ImgManagerVO>();
		  List<ImgManager> lst = iImgManagerMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  ImgManagerVO vo=this.structDetailData(t);
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
	public ResultObject delImgManager(ImgManager imgManager) {
		 int i= iImgManagerMapper.updateByPrimaryKeySelective(imgManager);
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
	public ResultObject modImgManager(ImgManager imgManager) {
		int i= iImgManagerMapper.updateByPrimaryKeySelective(imgManager);
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
	public ResultObject addNewImgManager(ImgManager imgManager) {
		imgManager.setCreateTime(TimeUtils.getCurrentTime());
		int i= iImgManagerMapper.insertSelective(imgManager);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private ImgManagerVO structDetailData(ImgManager imgManager) { 
		 if(imgManager==null){ 
			 return null; 
		} 
		ImgManagerVO vo= EntityChangeRquestView.createEntityToVO(imgManager,new ImgManagerVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(ImgManagerDO imgManagerDO) { 
		Example example = new Example(ImgManager.class); 
		Example.Criteria criteria = example.createCriteria(); 
		if(StringUtils.isNotNull(imgManagerDO.getFileName())) {
			criteria.andLike("fileName", "'%"+imgManagerDO.getFileName()+"%'");
		}
		imgManagerDO.setFileName(null);
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(imgManagerDO,new ImgManager())); 
		return example; 
	}
}
