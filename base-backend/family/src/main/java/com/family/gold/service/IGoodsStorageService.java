package com.family.gold.service; 
import com.family.gold.entity.GoodsStorage; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.gold.entity.VO.GoodsStorageVO; 
import com.family.gold.entity.DO.GoodsStorageDO; 
import java.util.List;
/** 
* GoodsStorage服务接口层 
* Auther:feng
*/ 
public interface IGoodsStorageService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public GoodsStorageVO getSingleInfo(GoodsStorageDO goodsStorageDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	GoodsStorageVO getSingleInfoById(Integer goodsStorageId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<GoodsStorageVO> getGoodsStorageList(GoodsStorageDO goodsStorageDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<GoodsStorageVO> getGoodsStorageListByPage(GoodsStorageDO goodsStorageDO, LayuiPage<GoodsStorageVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delGoodsStorage(GoodsStorage goodsStorage); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modGoodsStorage(GoodsStorage goodsStorage);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewGoodsStorage(GoodsStorage goodsStorage);
}
