package com.family.wb.service; 
import com.family.wb.entity.BannerInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.BannerInfoVO; 
import com.family.wb.entity.DO.BannerInfoDO; 
import java.util.List;
/** 
* BannerInfo服务接口层 
* Auther:feng
*/ 
public interface IBannerInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public BannerInfoVO getSingleInfo(BannerInfoDO bannerInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	BannerInfoVO getSingleInfoById(Integer bannerId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<BannerInfoVO> getBannerInfoList(BannerInfoDO bannerInfoDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<BannerInfoVO> getBannerInfoListByPage(BannerInfoDO bannerInfoDO, LayuiPage<BannerInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delBannerInfo(BannerInfo bannerInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modBannerInfo(BannerInfo bannerInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewBannerInfo(BannerInfo bannerInfo);
}
