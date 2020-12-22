package com.family.wb.service; 
import com.family.wb.entity.AboutOurInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.AboutOurInfoVO; 
import com.family.wb.entity.DO.AboutOurInfoDO; 
import java.util.List;
/** 
* AboutOurInfo服务接口层 
* Auther:feng
*/ 
public interface IAboutOurInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public AboutOurInfoVO getSingleInfo(AboutOurInfoDO aboutOurInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	AboutOurInfoVO getSingleInfoById(Integer aboutOurId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<AboutOurInfoVO> getAboutOurInfoList(AboutOurInfoDO aboutOurInfoDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<AboutOurInfoVO> getAboutOurInfoListByPage(AboutOurInfoDO aboutOurInfoDO, LayuiPage<AboutOurInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delAboutOurInfo(AboutOurInfo aboutOurInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modAboutOurInfo(AboutOurInfo aboutOurInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewAboutOurInfo(AboutOurInfo aboutOurInfo);
}
