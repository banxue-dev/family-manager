package com.family.mall.service; 
import com.family.mall.entity.GoodsInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.mall.entity.VO.GoodsInfoVO; 
import com.family.mall.entity.DO.GoodsInfoDO; 
import java.util.List;
/** 
* GoodsInfo服务接口层 
* Auther:feng
*/ 
public interface IGoodsInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public GoodsInfoVO getSingleInfo(GoodsInfoDO goodsInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	GoodsInfoVO getSingleInfoById(Long goodsId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<GoodsInfoVO> getGoodsInfoList(GoodsInfoDO goodsInfoDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<GoodsInfoVO> getGoodsInfoListByPage(GoodsInfoDO goodsInfoDO, LayuiPage<GoodsInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delGoodsInfo(GoodsInfo goodsInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modGoodsInfo(GoodsInfo goodsInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewGoodsInfo(GoodsInfo goodsInfo);
}
