package com.family.normal.service; 
import com.family.normal.entity.SupportRecord; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.normal.entity.VO.SupportRecordVO; 
import com.family.normal.entity.DO.SupportRecordDO; 
import java.util.List;
/** 
* SupportRecord服务接口层 
* Auther:feng
*/ 
public interface ISupportRecordService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public SupportRecordVO getSingleInfo(SupportRecordDO supportRecordDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	SupportRecordVO getSingleInfoById(Integer supportRecordId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<SupportRecordVO> getSupportRecordList(SupportRecordDO supportRecordDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<SupportRecordVO> getSupportRecordListByPage(SupportRecordDO supportRecordDO, LayuiPage<SupportRecordVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delSupportRecord(SupportRecord supportRecord); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modSupportRecord(SupportRecord supportRecord);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewSupportRecord(SupportRecord supportRecord);
}
