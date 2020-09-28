package com.family.gold.service; 
import com.family.gold.entity.GoodsOutStorageRecord; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.gold.entity.VO.GoodsOutStorageRecordVO; 
import com.family.gold.entity.DO.GoodsOutStorageRecordDO; 
import java.util.List;
/** 
* GoodsOutStorageRecord服务接口层 
* Auther:feng
*/ 
public interface IGoodsOutStorageRecordService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public GoodsOutStorageRecordVO getSingleInfo(GoodsOutStorageRecordDO goodsOutStorageRecordDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	GoodsOutStorageRecordVO getSingleInfoById(Integer goodsOutStorageId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<GoodsOutStorageRecordVO> getGoodsOutStorageRecordList(GoodsOutStorageRecordDO goodsOutStorageRecordDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<GoodsOutStorageRecordVO> getGoodsOutStorageRecordListByPage(GoodsOutStorageRecordDO goodsOutStorageRecordDO, LayuiPage<GoodsOutStorageRecordVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delGoodsOutStorageRecord(GoodsOutStorageRecord goodsOutStorageRecord); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modGoodsOutStorageRecord(GoodsOutStorageRecord goodsOutStorageRecord);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewGoodsOutStorageRecord(GoodsOutStorageRecord goodsOutStorageRecord);
}
