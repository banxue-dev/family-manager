package com.family.wb.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

import com.family.wb.entity.NewsInfo;
import com.family.wb.entity.DO.NewsInfoAD;
import com.family.wb.entity.VO.NewsInfoVO;
import com.family.wb.entity.VO.NewsInfoVO;
import com.family.wb.entity.DO.NewsInfoDO; 
import com.family.wb.service.INewsInfoService; 
import com.family.wb.mapper.NewsInfoMapper; 
import org.springframework.beans.factory.annotation.Autowired;   
import com.family.utils.EntityChangeRquestView;   
import io.swagger.annotations.Api;   
import io.swagger.annotations.ApiImplicitParam;   
import io.swagger.annotations.ApiImplicitParams;   
import java.util.List;  
import io.swagger.annotations.ApiOperation;  
import com.family.utils.ResultObject;  
import com.family.utils.LayuiPage; 
import com.family.utils.ResultUtil;  

import com.family.utils.StringUtils;  

/** 
* 官网新闻表控制器 
* Auther:feng
* Date:2020-12-16 15:41:23
*/ 
@RestController 
@RequestMapping("newsInfo/v1.0") 
@Api(tags = "官网新闻表的接口") 
public class NewsInfoController {  
	@Autowired 
	private INewsInfoService iNewsInfoService; 
	@Autowired 
	private NewsInfoMapper iNewsInfoMapper; 


Logger logger=LoggerFactory.getLogger(NewsInfoController.class);	/** 
	* 依据ID获取官网新闻表详情 
	* Auther:feng
	*/ 
	@PostMapping("getNewsInfoSingleById") 
	@ApiOperation("依据ID获取官网新闻表详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "newsInfoId", value = "官网新闻表的id", required = false,example="1") })
	public  ResultObject getNewsInfoSingleById(Integer newsInfoId) {  
		try{ 
		  NewsInfoVO entity=new NewsInfoVO(); 
		  entity=iNewsInfoService.getSingleInfoById(newsInfoId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取官网新闻表详情异常"); 
		  return ResultUtil.error("依据ID获取官网新闻表详情异常"); 
		} 
	} 
	/** 
	 * 获取官网上的案例数据列表 
	 * Auther:feng
	 */ 
	@PostMapping("api/getRandomNewsInfoList") 
	@ApiOperation("获取指定数量的随机案例") 
	@ApiImplicitParams({ })
	public ResultObject getRandomNewsInfoList(Integer count) {  
		try{ 
			List<NewsInfoVO> lst = iNewsInfoService.getRandomNewsInfoList(count);
			return ResultUtil.successData(lst); 
		}catch(Exception e){ 
			logger.error(e+"获取官网上的案例数据列表记录异常"); 
			return ResultUtil.error("获取官网上的案例数据列表记录异常"); 
		} 
	} 

	@PostMapping("api/getNewsInfoDetail") 
	@ApiOperation("依据ID获取官网上的案例数据详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "newsInfoId", value = "官网上的案例数据的id", required = false,example="1") })
	public  ResultObject getNewsInfoDetail(Integer newsInfoId) {  
		try{ 
		  NewsInfoVO entity=new NewsInfoVO(); 
		  entity=iNewsInfoService.getSingleInfoById(newsInfoId); 
		  /**
		   * 获取他的上一条和下一条的数据名称和id
		   */
		  NewsInfo prev=iNewsInfoMapper.getPrevDataById(newsInfoId);
		  if(prev!=null) {
			  entity.setPrevId(prev.getNewsId());
			  entity.setPrevName(prev.getNewsTitle());
		  }
		  NewsInfo next=iNewsInfoMapper.getNextDataById(newsInfoId);
		  if(next!=null) {
			  entity.setNextId(next.getNewsId());
			  entity.setNextName(next.getNewsTitle());
		  }
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取官网上的案例数据详情异常"); 
		  return ResultUtil.error("依据ID获取官网上的案例数据详情异常"); 
		} 
	} 
	/** 
	* 获取官网新闻表单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getNewsInfoSingle") 
	@ApiOperation("获取官网新闻表单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getNewsInfoSingle(NewsInfoDO newsInfo) {  
		try{ 
		 NewsInfoVO newsInfoVO=iNewsInfoService.getSingleInfo(newsInfo); 
		  return ResultUtil.successData(newsInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网新闻表单条记录异常"); 
		  return ResultUtil.error("获取官网新闻表单条记录异常"); 
		} 
	} 
	/** 
	* 获取官网新闻表列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getNewsInfoList") 
	@ApiOperation("获取官网新闻表列表") 
	@ApiImplicitParams({ })
	public ResultObject getNewsInfoList(NewsInfoDO newsInfo) {  
		try{ 
		  List<NewsInfoVO> lst = iNewsInfoService.getNewsInfoList(newsInfo); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网新闻表列表记录异常"); 
		  return ResultUtil.error("获取官网新闻表列表记录异常"); 
		} 
	} 
	/** 
	* 获取官网新闻表分页数据 
	* Auther:feng
	*/ 
	@PostMapping("api/getNewsInfoListByPage") 
	@ApiOperation("获取官网新闻表分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getNewsInfoListByPage(NewsInfoDO newsInfo,LayuiPage<NewsInfoVO> layuiPage) {  
		try{ 
		  return iNewsInfoService.getNewsInfoListByPage(newsInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取官网新闻表分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加官网新闻表方法 
	* Auther:feng
	*/ 
	@PostMapping("addNewsInfo") 
	@ApiOperation("添加官网新闻表") 
	@ApiImplicitParams({  })
	public  ResultObject addNewsInfo(NewsInfoAD newsInfoad) {  
		try{ 
		  NewsInfo newsInfo=EntityChangeRquestView.createDOToEntity(newsInfoad, new NewsInfo()); 
		  return iNewsInfoService.addNewNewsInfo(newsInfo); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加官网新闻表异常"); 
		  return ResultUtil.error("添加官网新闻表异常"); 
		} 
	} 
	/** 
	* 修改官网新闻表方法 
	* Auther:feng
	*/ 
	@PostMapping("modNewsInfo") 
	@ApiOperation("修改官网新闻表") 
	@ApiImplicitParams({  })
	public  ResultObject modNewsInfo(NewsInfoAD newsInfoad) {  
		try{ 
		  NewsInfo newsInfo=EntityChangeRquestView.createDOToEntity(newsInfoad, new NewsInfo()); 
			  return iNewsInfoService.modNewsInfo(newsInfo); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改官网新闻表异常"); 
		  return ResultUtil.error("修改官网新闻表异常"); 
		} 
	} 
	/** 
	* 删除官网新闻表 
	* Auther:feng
	*/ 
	@PostMapping("delNewsInfo") 
	@ApiOperation("删除官网新闻表方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "newsInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delNewsInfo(String newsInfoIds) {  
		try{ 
		  if(StringUtils.isNull(newsInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=newsInfoIds.split(",");
		 for(String str:strs) {
			  iNewsInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除官网新闻表方法异常 "); 
		  return ResultUtil.error("删除官网新闻表方法异常 "); 
		} 
	} 
}
