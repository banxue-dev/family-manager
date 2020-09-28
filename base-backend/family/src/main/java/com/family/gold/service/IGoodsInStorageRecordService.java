package com.family.gold.service; 
import com.family.gold.entity.GoodsInStorageRecord; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.gold.entity.VO.GoodsInStorageRecordVO; 
import com.family.gold.entity.DO.GoodsInStorageRecordDO; 
import java.util.List;
/** 
* GoodsInStorageRecord服务接口层 
* Auther:feng
*/ 
public interface IGoodsInStorageRecordService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public GoodsInStorageRecordVO getSingleInfo(GoodsInStorageRecordDO goodsInStorageRecordDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	GoodsInStorageRecordVO getSingleInfoById(Integer goodsInStorageId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<GoodsInStorageRecordVO> getGoodsInStorageRecordList(GoodsInStorageRecordDO goodsInStorageRecordDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<GoodsInStorageRecordVO> getGoodsInStorageRecordListByPage(GoodsInStorageRecordDO goodsInStorageRecordDO, LayuiPage<GoodsInStorageRecordVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delGoodsInStorageRecord(GoodsInStorageRecord goodsInStorageRecord); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modGoodsInStorageRecord(GoodsInStorageRecord goodsInStorageRecord);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewGoodsInStorageRecord(GoodsInStorageRecord goodsInStorageRecord);
}
