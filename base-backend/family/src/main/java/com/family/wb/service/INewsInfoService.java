package com.family.wb.service; 
import com.family.wb.entity.NewsInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.NewsInfoVO; 
import com.family.wb.entity.DO.NewsInfoDO; 
import java.util.List;
/** 
* NewsInfo服务接口层 
* Auther:feng
*/ 
public interface INewsInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public NewsInfoVO getSingleInfo(NewsInfoDO newsInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	NewsInfoVO getSingleInfoById(Integer newsId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<NewsInfoVO> getNewsInfoList(NewsInfoDO newsInfoDO);
	public List<NewsInfoVO> getRandomNewsInfoList(int count);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<NewsInfoVO> getNewsInfoListByPage(NewsInfoDO newsInfoDO, LayuiPage<NewsInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delNewsInfo(NewsInfo newsInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modNewsInfo(NewsInfo newsInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewNewsInfo(NewsInfo newsInfo);
}
