package com.family.gold.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.gold.entity.GoodsOutStorageRecord; 
import com.family.gold.entity.DO.GoodsOutStorageRecordAD; 
import com.family.gold.entity.VO.GoodsOutStorageRecordVO; 
import com.family.gold.entity.DO.GoodsOutStorageRecordDO; 
import com.family.gold.service.IGoodsOutStorageRecordService; 
import com.family.gold.mapper.GoodsOutStorageRecordMapper; 
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
* 控制器 
* Auther:feng
* Date:2020-09-24 09:35:32
*/ 
@RestController 
@RequestMapping("goodsOutStorageRecord/v1.0") 
@Api(tags = "的接口") 
public class GoodsOutStorageRecordController {  
	@Autowired 
	private IGoodsOutStorageRecordService iGoodsOutStorageRecordService; 
	@Autowired 
	private GoodsOutStorageRecordMapper iGoodsOutStorageRecordMapper; 


Logger logger=LoggerFactory.getLogger(GoodsOutStorageRecordController.class);	/** 
	* 依据ID获取详情 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsOutStorageRecordSingleById") 
	@ApiOperation("依据ID获取详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsOutStorageRecordId", value = "的id", required = false,example="1") })
	public  ResultObject getGoodsOutStorageRecordSingleById(Integer goodsOutStorageRecordId) {  
		try{ 
		  GoodsOutStorageRecordVO entity=new GoodsOutStorageRecordVO(); 
		  entity=iGoodsOutStorageRecordService.getSingleInfoById(goodsOutStorageRecordId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取详情异常"); 
		  return ResultUtil.error("依据ID获取详情异常"); 
		} 
	} 
	/** 
	* 获取单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsOutStorageRecordSingle") 
	@ApiOperation("获取单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getGoodsOutStorageRecordSingle(GoodsOutStorageRecordDO goodsOutStorageRecord) {  
		try{ 
		 GoodsOutStorageRecordVO goodsOutStorageRecordVO=iGoodsOutStorageRecordService.getSingleInfo(goodsOutStorageRecord); 
		  return ResultUtil.successData(goodsOutStorageRecordVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取单条记录异常"); 
		  return ResultUtil.error("获取单条记录异常"); 
		} 
	} 
	/** 
	* 获取列表 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsOutStorageRecordList") 
	@ApiOperation("获取列表") 
	@ApiImplicitParams({ })
	public ResultObject getGoodsOutStorageRecordList(GoodsOutStorageRecordDO goodsOutStorageRecord) {  
		try{ 
		  List<GoodsOutStorageRecordVO> lst = iGoodsOutStorageRecordService.getGoodsOutStorageRecordList(goodsOutStorageRecord); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取列表记录异常"); 
		  return ResultUtil.error("获取列表记录异常"); 
		} 
	} 
	/** 
	* 获取分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsOutStorageRecordListByPage") 
	@ApiOperation("获取分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getGoodsOutStorageRecordListByPage(GoodsOutStorageRecordDO goodsOutStorageRecord,LayuiPage<GoodsOutStorageRecordVO> layuiPage) {  
		try{ 
		  return iGoodsOutStorageRecordService.getGoodsOutStorageRecordListByPage(goodsOutStorageRecord, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加方法 
	* Auther:feng
	*/ 
	@PostMapping("addGoodsOutStorageRecord") 
	@ApiOperation("添加") 
	@ApiImplicitParams({  })
	public  ResultObject addGoodsOutStorageRecord(GoodsOutStorageRecordAD goodsOutStorageRecordad) {  
		try{ 
		  GoodsOutStorageRecord goodsOutStorageRecord=EntityChangeRquestView.createDOToEntity(goodsOutStorageRecordad, new GoodsOutStorageRecord()); 
		  return iGoodsOutStorageRecordService.addNewGoodsOutStorageRecord(goodsOutStorageRecord); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加异常"); 
		  return ResultUtil.error("添加异常"); 
		} 
	} 
	/** 
	* 修改方法 
	* Auther:feng
	*/ 
	@PostMapping("modGoodsOutStorageRecord") 
	@ApiOperation("修改") 
	@ApiImplicitParams({  })
	public  ResultObject modGoodsOutStorageRecord(GoodsOutStorageRecordAD goodsOutStorageRecordad) {  
		try{ 
		  GoodsOutStorageRecord goodsOutStorageRecord=EntityChangeRquestView.createDOToEntity(goodsOutStorageRecordad, new GoodsOutStorageRecord()); 
			  return iGoodsOutStorageRecordService.modGoodsOutStorageRecord(goodsOutStorageRecord); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改异常"); 
		  return ResultUtil.error("修改异常"); 
		} 
	} 
	/** 
	* 删除 
	* Auther:feng
	*/ 
	@PostMapping("delGoodsOutStorageRecord") 
	@ApiOperation("删除方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsOutStorageRecordIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delGoodsOutStorageRecord(String goodsOutStorageRecordIds) {  
		try{ 
		  if(StringUtils.isNull(goodsOutStorageRecordIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=goodsOutStorageRecordIds.split(",");
		 for(String str:strs) {
			  iGoodsOutStorageRecordMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除方法异常 "); 
		  return ResultUtil.error("删除方法异常 "); 
		} 
	} 
}
