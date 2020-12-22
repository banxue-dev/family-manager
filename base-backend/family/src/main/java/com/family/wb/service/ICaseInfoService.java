package com.family.wb.service; 
import com.family.wb.entity.CaseInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.CaseInfoVO; 
import com.family.wb.entity.DO.CaseInfoDO; 
import java.util.List;
/** 
* CaseInfo服务接口层 
* Auther:feng
*/ 
public interface ICaseInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public CaseInfoVO getSingleInfo(CaseInfoDO caseInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	CaseInfoVO getSingleInfoById(Integer caseId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<CaseInfoVO> getCaseInfoList(CaseInfoDO caseInfoDO);
	/**
	 * 获取指定数量的随机数据
	 * @param count
	 * @return
	 */
	public List<CaseInfoVO> getRandomCaseInfoList(Integer count);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<CaseInfoVO> getCaseInfoListByPage(CaseInfoDO caseInfoDO, LayuiPage<CaseInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delCaseInfo(CaseInfo caseInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modCaseInfo(CaseInfo caseInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewCaseInfo(CaseInfo caseInfo);
}
