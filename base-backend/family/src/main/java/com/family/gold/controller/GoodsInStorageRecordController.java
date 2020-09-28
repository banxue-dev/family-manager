package com.family.gold.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.gold.entity.GoodsInStorageRecord; 
import com.family.gold.entity.DO.GoodsInStorageRecordAD; 
import com.family.gold.entity.VO.GoodsInStorageRecordVO; 
import com.family.gold.entity.DO.GoodsInStorageRecordDO; 
import com.family.gold.service.IGoodsInStorageRecordService; 
import com.family.gold.mapper.GoodsInStorageRecordMapper; 
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
* Date:2020-09-24 09:35:31
*/ 
@RestController 
@RequestMapping("goodsInStorageRecord/v1.0") 
@Api(tags = "的接口") 
public class GoodsInStorageRecordController {  
	@Autowired 
	private IGoodsInStorageRecordService iGoodsInStorageRecordService; 
	@Autowired 
	private GoodsInStorageRecordMapper iGoodsInStorageRecordMapper; 


Logger logger=LoggerFactory.getLogger(GoodsInStorageRecordController.class);	/** 
	* 依据ID获取详情 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsInStorageRecordSingleById") 
	@ApiOperation("依据ID获取详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsInStorageRecordId", value = "的id", required = false,example="1") })
	public  ResultObject getGoodsInStorageRecordSingleById(Integer goodsInStorageRecordId) {  
		try{ 
		  GoodsInStorageRecordVO entity=new GoodsInStorageRecordVO(); 
		  entity=iGoodsInStorageRecordService.getSingleInfoById(goodsInStorageRecordId); 
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
	@PostMapping("getGoodsInStorageRecordSingle") 
	@ApiOperation("获取单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getGoodsInStorageRecordSingle(GoodsInStorageRecordDO goodsInStorageRecord) {  
		try{ 
		 GoodsInStorageRecordVO goodsInStorageRecordVO=iGoodsInStorageRecordService.getSingleInfo(goodsInStorageRecord); 
		  return ResultUtil.successData(goodsInStorageRecordVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取单条记录异常"); 
		  return ResultUtil.error("获取单条记录异常"); 
		} 
	} 
	/** 
	* 获取列表 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsInStorageRecordList") 
	@ApiOperation("获取列表") 
	@ApiImplicitParams({ })
	public ResultObject getGoodsInStorageRecordList(GoodsInStorageRecordDO goodsInStorageRecord) {  
		try{ 
		  List<GoodsInStorageRecordVO> lst = iGoodsInStorageRecordService.getGoodsInStorageRecordList(goodsInStorageRecord); 
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
	@PostMapping("getGoodsInStorageRecordListByPage") 
	@ApiOperation("获取分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getGoodsInStorageRecordListByPage(GoodsInStorageRecordDO goodsInStorageRecord,LayuiPage<GoodsInStorageRecordVO> layuiPage) {  
		try{ 
		  return iGoodsInStorageRecordService.getGoodsInStorageRecordListByPage(goodsInStorageRecord, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加方法 
	* Auther:feng
	*/ 
	@PostMapping("addGoodsInStorageRecord") 
	@ApiOperation("添加") 
	@ApiImplicitParams({  })
	public  ResultObject addGoodsInStorageRecord(GoodsInStorageRecordAD goodsInStorageRecordad) {  
		try{ 
		  GoodsInStorageRecord goodsInStorageRecord=EntityChangeRquestView.createDOToEntity(goodsInStorageRecordad, new GoodsInStorageRecord()); 
		  return iGoodsInStorageRecordService.addNewGoodsInStorageRecord(goodsInStorageRecord); 
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
	@PostMapping("modGoodsInStorageRecord") 
	@ApiOperation("修改") 
	@ApiImplicitParams({  })
	public  ResultObject modGoodsInStorageRecord(GoodsInStorageRecordAD goodsInStorageRecordad) {  
		try{ 
		  GoodsInStorageRecord goodsInStorageRecord=EntityChangeRquestView.createDOToEntity(goodsInStorageRecordad, new GoodsInStorageRecord()); 
			  return iGoodsInStorageRecordService.modGoodsInStorageRecord(goodsInStorageRecord); 
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
	@PostMapping("delGoodsInStorageRecord") 
	@ApiOperation("删除方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsInStorageRecordIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delGoodsInStorageRecord(String goodsInStorageRecordIds) {  
		try{ 
		  if(StringUtils.isNull(goodsInStorageRecordIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=goodsInStorageRecordIds.split(",");
		 for(String str:strs) {
			  iGoodsInStorageRecordMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除方法异常 "); 
		  return ResultUtil.error("删除方法异常 "); 
		} 
	} 
}
