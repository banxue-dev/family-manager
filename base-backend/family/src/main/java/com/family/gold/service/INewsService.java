package com.family.gold.service; 
import com.family.gold.entity.News; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.gold.entity.VO.NewsVO; 
import com.family.gold.entity.DO.NewsDO; 
import java.util.List;
/** 
* News服务接口层 
* Auther:feng
*/ 
public interface INewsService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public NewsVO getSingleInfo(NewsDO newsDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	NewsVO getSingleInfoById(Integer newsId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<NewsVO> getNewsList(NewsDO newsDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<NewsVO> getNewsListByPage(NewsDO newsDO, LayuiPage<NewsVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delNews(News news); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modNews(News news);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewNews(News news);
}
