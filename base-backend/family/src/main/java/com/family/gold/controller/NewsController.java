package com.family.gold.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.gold.entity.News; 
import com.family.gold.entity.DO.NewsAD; 
import com.family.gold.entity.VO.NewsVO; 
import com.family.gold.entity.DO.NewsDO; 
import com.family.gold.service.INewsService; 
import com.family.gold.mapper.NewsMapper; 
import org.springframework.beans.factory.annotation.Autowired;   
import com.family.utils.EntityChangeRquestView;   
import io.swagger.annotations.Api;   
import io.swagger.annotations.ApiImplicitParam;   
import io.swagger.annotations.ApiImplicitParams;   
import java.util.List;  
import io.swagger.annotations.ApiOperation;  
import com.family.utils.ResultObject;  
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultUtil;  

import com.family.utils.StringUtils;  

/** 
* 黄金的新闻内容控制器 
* Auther:feng
* Date:2020-09-22 17:59:24
*/ 
@RestController 
@RequestMapping("news/v1.0") 
@Api(tags = "黄金的新闻内容的接口") 
public class NewsController {  
	@Autowired 
	private INewsService iNewsService; 
	@Autowired 
	private NewsMapper iNewsMapper; 


Logger logger=LoggerFactory.getLogger(NewsController.class);	/** 
	* 依据ID获取黄金的新闻内容详情 
	* Auther:feng
	*/ 
	@PostMapping("getNewsSingleById") 
	@ApiOperation("依据ID获取黄金的新闻内容详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "newsId", value = "黄金的新闻内容的id", required = false,example="1") })
	public  ResultObject getNewsSingleById(Integer newsId) {  
		try{ 
		  NewsVO entity=new NewsVO(); 
		  entity=iNewsService.getSingleInfoById(newsId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取黄金的新闻内容详情异常"); 
		  return ResultUtil.error("依据ID获取黄金的新闻内容详情异常"); 
		} 
	} 
	/** 
	* 获取黄金的新闻内容单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getNewsSingle") 
	@ApiOperation("获取黄金的新闻内容单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getNewsSingle(NewsDO news) {  
		try{ 
		 NewsVO newsVO=iNewsService.getSingleInfo(news); 
		  return ResultUtil.successData(newsVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取黄金的新闻内容单条记录异常"); 
		  return ResultUtil.error("获取黄金的新闻内容单条记录异常"); 
		} 
	} 
	/** 
	* 获取黄金的新闻内容列表 
	* Auther:feng
	*/ 
	@PostMapping("getNewsList") 
	@ApiOperation("获取黄金的新闻内容列表") 
	@ApiImplicitParams({ })
	public ResultObject getNewsList(NewsDO news) {  
		try{ 
		  List<NewsVO> lst = iNewsService.getNewsList(news); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取黄金的新闻内容列表记录异常"); 
		  return ResultUtil.error("获取黄金的新闻内容列表记录异常"); 
		} 
	} 
	/** 
	* 获取黄金的新闻内容分页数据 
	* Auther:feng
	*/ 
	@PostMapping("api/getNewsListByPage") 
	@ApiOperation("获取黄金的新闻内容分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getNewsListByPage(NewsDO news,LayuiPage<NewsVO> layuiPage) {  
		try{ 
		  return iNewsService.getNewsListByPage(news, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取黄金的新闻内容分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加黄金的新闻内容方法 
	* Auther:feng
	*/ 
	@PostMapping("addNews") 
	@ApiOperation("添加黄金的新闻内容") 
	@ApiImplicitParams({  })
	public  ResultObject addNews(NewsAD newsad) {  
		try{ 
		  News news=EntityChangeRquestView.createDOToEntity(newsad, new News()); 
		  return iNewsService.addNewNews(news); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加黄金的新闻内容异常"); 
		  return ResultUtil.error("添加黄金的新闻内容异常"); 
		} 
	} 
	/** 
	* 修改黄金的新闻内容方法 
	* Auther:feng
	*/ 
	@PostMapping("modNews") 
	@ApiOperation("修改黄金的新闻内容") 
	@ApiImplicitParams({  })
	public  ResultObject modNews(NewsAD newsad) {  
		try{ 
		  News news=EntityChangeRquestView.createDOToEntity(newsad, new News()); 
			  return iNewsService.modNews(news); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改黄金的新闻内容异常"); 
		  return ResultUtil.error("修改黄金的新闻内容异常"); 
		} 
	} 
	/** 
	* 删除黄金的新闻内容 
	* Auther:feng
	*/ 
	@PostMapping("delNews") 
	@ApiOperation("删除黄金的新闻内容方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "newsIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delNews(String newsIds) {  
		try{ 
		  if(StringUtils.isNull(newsIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=newsIds.split(",");
		 for(String str:strs) {
			  iNewsMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除黄金的新闻内容方法异常 "); 
		  return ResultUtil.error("删除黄金的新闻内容方法异常 "); 
		} 
	} 
}
