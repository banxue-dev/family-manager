package com.family.wb.service; 
import com.family.wb.entity.LeavingMessageInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.LeavingMessageInfoVO; 
import com.family.wb.entity.DO.LeavingMessageInfoDO; 
import java.util.List;
/** 
* LeavingMessageInfo服务接口层 
* Auther:feng
*/ 
public interface ILeavingMessageInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public LeavingMessageInfoVO getSingleInfo(LeavingMessageInfoDO leavingMessageInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	LeavingMessageInfoVO getSingleInfoById(Integer leavingMessageId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<LeavingMessageInfoVO> getLeavingMessageInfoList(LeavingMessageInfoDO leavingMessageInfoDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<LeavingMessageInfoVO> getLeavingMessageInfoListByPage(LeavingMessageInfoDO leavingMessageInfoDO, LayuiPage<LeavingMessageInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delLeavingMessageInfo(LeavingMessageInfo leavingMessageInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modLeavingMessageInfo(LeavingMessageInfo leavingMessageInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewLeavingMessageInfo(LeavingMessageInfo leavingMessageInfo);
}
