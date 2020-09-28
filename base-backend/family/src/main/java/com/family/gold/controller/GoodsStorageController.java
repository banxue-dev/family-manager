package com.family.gold.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.gold.entity.GoodsStorage; 
import com.family.gold.entity.DO.GoodsStorageAD; 
import com.family.gold.entity.VO.GoodsStorageVO; 
import com.family.gold.entity.DO.GoodsStorageDO; 
import com.family.gold.service.IGoodsStorageService; 
import com.family.gold.mapper.GoodsStorageMapper; 
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
* 黄金库存管理控制器 
* Auther:feng
* Date:2020-09-24 09:35:33
*/ 
@RestController 
@RequestMapping("goodsStorage/v1.0") 
@Api(tags = "黄金库存管理的接口") 
public class GoodsStorageController {  
	@Autowired 
	private IGoodsStorageService iGoodsStorageService; 
	@Autowired 
	private GoodsStorageMapper iGoodsStorageMapper; 


Logger logger=LoggerFactory.getLogger(GoodsStorageController.class);	/** 
	* 依据ID获取黄金库存管理详情 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsStorageSingleById") 
	@ApiOperation("依据ID获取黄金库存管理详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsStorageId", value = "黄金库存管理的id", required = false,example="1") })
	public  ResultObject getGoodsStorageSingleById(Integer goodsStorageId) {  
		try{ 
		  GoodsStorageVO entity=new GoodsStorageVO(); 
		  entity=iGoodsStorageService.getSingleInfoById(goodsStorageId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取黄金库存管理详情异常"); 
		  return ResultUtil.error("依据ID获取黄金库存管理详情异常"); 
		} 
	} 
	/** 
	* 获取黄金库存管理单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsStorageSingle") 
	@ApiOperation("获取黄金库存管理单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getGoodsStorageSingle(GoodsStorageDO goodsStorage) {  
		try{ 
		 GoodsStorageVO goodsStorageVO=iGoodsStorageService.getSingleInfo(goodsStorage); 
		  return ResultUtil.successData(goodsStorageVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取黄金库存管理单条记录异常"); 
		  return ResultUtil.error("获取黄金库存管理单条记录异常"); 
		} 
	} 
	/** 
	* 获取黄金库存管理列表 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsStorageList") 
	@ApiOperation("获取黄金库存管理列表") 
	@ApiImplicitParams({ })
	public ResultObject getGoodsStorageList(GoodsStorageDO goodsStorage) {  
		try{ 
		  List<GoodsStorageVO> lst = iGoodsStorageService.getGoodsStorageList(goodsStorage); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取黄金库存管理列表记录异常"); 
		  return ResultUtil.error("获取黄金库存管理列表记录异常"); 
		} 
	} 
	/** 
	* 获取黄金库存管理分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsStorageListByPage") 
	@ApiOperation("获取黄金库存管理分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getGoodsStorageListByPage(GoodsStorageDO goodsStorage,LayuiPage<GoodsStorageVO> layuiPage) {  
		try{ 
		  return iGoodsStorageService.getGoodsStorageListByPage(goodsStorage, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取黄金库存管理分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加黄金库存管理方法 
	* Auther:feng
	*/ 
	@PostMapping("addGoodsStorage") 
	@ApiOperation("添加黄金库存管理") 
	@ApiImplicitParams({  })
	public  ResultObject addGoodsStorage(GoodsStorageAD goodsStoragead) {  
		try{ 
		  GoodsStorage goodsStorage=EntityChangeRquestView.createDOToEntity(goodsStoragead, new GoodsStorage()); 
		  return iGoodsStorageService.addNewGoodsStorage(goodsStorage); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加黄金库存管理异常"); 
		  return ResultUtil.error("添加黄金库存管理异常"); 
		} 
	} 
	/** 
	* 修改黄金库存管理方法 
	* Auther:feng
	*/ 
	@PostMapping("modGoodsStorage") 
	@ApiOperation("修改黄金库存管理") 
	@ApiImplicitParams({  })
	public  ResultObject modGoodsStorage(GoodsStorageAD goodsStoragead) {  
		try{ 
		  GoodsStorage goodsStorage=EntityChangeRquestView.createDOToEntity(goodsStoragead, new GoodsStorage()); 
			  return iGoodsStorageService.modGoodsStorage(goodsStorage); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改黄金库存管理异常"); 
		  return ResultUtil.error("修改黄金库存管理异常"); 
		} 
	} 
	/** 
	* 删除黄金库存管理 
	* Auther:feng
	*/ 
	@PostMapping("delGoodsStorage") 
	@ApiOperation("删除黄金库存管理方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsStorageIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delGoodsStorage(String goodsStorageIds) {  
		try{ 
		  if(StringUtils.isNull(goodsStorageIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=goodsStorageIds.split(",");
		 for(String str:strs) {
			  iGoodsStorageMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除黄金库存管理方法异常 "); 
		  return ResultUtil.error("删除黄金库存管理方法异常 "); 
		} 
	} 
}
