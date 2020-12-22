package com.family.wb.service.Impl; 
import com.family.wb.entity.BannerInfo; 
import com.family.wb.mapper.BannerInfoMapper; 
import com.family.wb.service.IBannerInfoService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.wb.entity.VO.BannerInfoVO; 
import com.family.wb.entity.DO.BannerInfoDO; 
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
* BannerInfo服务层 
* Auther:feng
* Date:2020-12-16 15:41:17
*/ 
@Service 
public class BannerInfoServiceImpl implements IBannerInfoService { 

	@Autowired
	private BannerInfoMapper iBannerInfoMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public BannerInfoVO getSingleInfo(BannerInfoDO bannerInfoDO) {
		BannerInfo bannerInfo=new BannerInfo();
		bannerInfo= iBannerInfoMapper.selectOne(EntityChangeRquestView.createDOToEntity(bannerInfoDO,new BannerInfo()));
		return this.structDetailData(bannerInfo);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public BannerInfoVO getSingleInfoById(Integer bannerId) {
		BannerInfo bannerInfo=new BannerInfo();
		bannerInfo= iBannerInfoMapper.selectByPrimaryKey(bannerId);
		return this.structDetailData(bannerInfo);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<BannerInfoVO> getBannerInfoList(BannerInfoDO bannerInfoDO) {
		  Example example = getPublicExample(bannerInfoDO);
		  List<BannerInfoVO> lstVO = new ArrayList<BannerInfoVO>();
		  List<BannerInfo> lst = iBannerInfoMapper.selectByExample(example); 
		lst.forEach(t->{
		  BannerInfoVO vo=this.structDetailData(t);
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
	public LayuiPage<BannerInfoVO> getBannerInfoListByPage(BannerInfoDO bannerInfoDO, LayuiPage<BannerInfoVO> layuiPage){
		  Example example = getPublicExample(bannerInfoDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<BannerInfoVO> lstVO = new ArrayList<BannerInfoVO>();
		  List<BannerInfo> lst = iBannerInfoMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  BannerInfoVO vo=this.structDetailData(t);
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
	public ResultObject delBannerInfo(BannerInfo bannerInfo) {
		 int i= iBannerInfoMapper.updateByPrimaryKeySelective(bannerInfo);
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
	public ResultObject modBannerInfo(BannerInfo bannerInfo) {
		int i= iBannerInfoMapper.updateByPrimaryKeySelective(bannerInfo);
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
	public ResultObject addNewBannerInfo(BannerInfo bannerInfo) {
		int i= iBannerInfoMapper.insertSelective(bannerInfo);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private BannerInfoVO structDetailData(BannerInfo bannerInfo) { 
		 if(bannerInfo==null){ 
			 return null; 
		} 
		BannerInfoVO vo= EntityChangeRquestView.createEntityToVO(bannerInfo,new BannerInfoVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(BannerInfoDO bannerInfoDO) { 
		Example example = new Example(BannerInfo.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(bannerInfoDO,new BannerInfo())); 
		return example; 
	}
}
