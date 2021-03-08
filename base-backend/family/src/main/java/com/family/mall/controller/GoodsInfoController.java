package com.family.mall.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.mall.entity.GoodsInfo; 
import com.family.mall.entity.DO.GoodsInfoAD; 
import com.family.mall.entity.VO.GoodsInfoVO; 
import com.family.mall.entity.DO.GoodsInfoDO; 
import com.family.mall.service.IGoodsInfoService; 
import com.family.mall.mapper.GoodsInfoMapper; 
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
* 商城的商品表控制器 
* Auther:feng
* Date:2021-03-08 17:10:28
*/ 
@RestController 
@RequestMapping("goodsInfo/v1.0") 
@Api(tags = "商城的商品表的接口") 
public class GoodsInfoController {  
	@Autowired 
	private IGoodsInfoService iGoodsInfoService; 
	@Autowired 
	private GoodsInfoMapper iGoodsInfoMapper; 


Logger logger=LoggerFactory.getLogger(GoodsInfoController.class);	/** 
	* 依据ID获取商城的商品表详情 
	* Auther:feng
	*/ 
	@PostMapping("api/getGoodsInfoSingleById") 
	@ApiOperation("依据ID获取商城的商品表详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsInfoId", value = "商城的商品表的id", required = false,example="1") })
	public  ResultObject getGoodsInfoSingleById(Long goodsInfoId) {  
		try{ 
		  GoodsInfoVO entity=new GoodsInfoVO(); 
		  entity=iGoodsInfoService.getSingleInfoById(goodsInfoId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取商城的商品表详情异常"); 
		  return ResultUtil.error("依据ID获取商城的商品表详情异常"); 
		} 
	} 
	/** 
	* 获取商城的商品表单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getGoodsInfoSingle") 
	@ApiOperation("获取商城的商品表单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getGoodsInfoSingle(GoodsInfoDO goodsInfo) {  
		try{ 
		 GoodsInfoVO goodsInfoVO=iGoodsInfoService.getSingleInfo(goodsInfo); 
		  return ResultUtil.successData(goodsInfoVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取商城的商品表单条记录异常"); 
		  return ResultUtil.error("获取商城的商品表单条记录异常"); 
		} 
	} 
	/** 
	* 获取商城的商品表列表 
	* Auther:feng
	*/ 
	@PostMapping("api/getGoodsInfoList") 
	@ApiOperation("获取商城的商品表列表") 
	@ApiImplicitParams({ })
	public ResultObject getGoodsInfoList(GoodsInfoDO goodsInfo) {  
		try{ 
		  List<GoodsInfoVO> lst = iGoodsInfoService.getGoodsInfoList(goodsInfo); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取商城的商品表列表记录异常"); 
		  return ResultUtil.error("获取商城的商品表列表记录异常"); 
		} 
	} 
	/** 
	* 获取商城的商品表分页数据 
	* Auther:feng
	*/ 
	@PostMapping("api/getGoodsInfoListByPage") 
	@ApiOperation("获取商城的商品表分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getGoodsInfoListByPage(GoodsInfoDO goodsInfo,LayuiPage<GoodsInfoVO> layuiPage) {  
		try{ 
		  return iGoodsInfoService.getGoodsInfoListByPage(goodsInfo, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取商城的商品表分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加商城的商品表方法 
	* Auther:feng
	*/ 
	@PostMapping("addGoodsInfo") 
	@ApiOperation("添加商城的商品表") 
	@ApiImplicitParams({  })
	public  ResultObject addGoodsInfo(GoodsInfoAD goodsInfoad) {  
		try{ 
		  GoodsInfo goodsInfo=EntityChangeRquestView.createDOToEntity(goodsInfoad, new GoodsInfo()); 
		  return iGoodsInfoService.addNewGoodsInfo(goodsInfo); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加商城的商品表异常"); 
		  return ResultUtil.error("添加商城的商品表异常"); 
		} 
	} 
	/** 
	* 修改商城的商品表方法 
	* Auther:feng
	*/ 
	@PostMapping("modGoodsInfo") 
	@ApiOperation("修改商城的商品表") 
	@ApiImplicitParams({  })
	public  ResultObject modGoodsInfo(GoodsInfoAD goodsInfoad) {  
		try{ 
		  GoodsInfo goodsInfo=EntityChangeRquestView.createDOToEntity(goodsInfoad, new GoodsInfo()); 
			  return iGoodsInfoService.modGoodsInfo(goodsInfo); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改商城的商品表异常"); 
		  return ResultUtil.error("修改商城的商品表异常"); 
		} 
	} 
	/** 
	* 删除商城的商品表 
	* Auther:feng
	*/ 
	@PostMapping("delGoodsInfo") 
	@ApiOperation("删除商城的商品表方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "goodsInfoIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delGoodsInfo(String goodsInfoIds) {  
		try{ 
		  if(StringUtils.isNull(goodsInfoIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=goodsInfoIds.split(",");
		 for(String str:strs) {
			  iGoodsInfoMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除商城的商品表方法异常 "); 
		  return ResultUtil.error("删除商城的商品表方法异常 "); 
		} 
	} 
}
