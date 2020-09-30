package com.family.normal.controller; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.PostMapping;   
import org.springframework.web.bind.annotation.RequestMapping;   
import org.springframework.web.bind.annotation.RequestHeader;   
import springfox.documentation.annotations.ApiIgnore;  
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import com.family.normal.entity.SupportRecord; 
import com.family.normal.entity.DO.SupportRecordAD; 
import com.family.normal.entity.VO.SupportRecordVO; 
import com.family.normal.entity.DO.SupportRecordDO; 
import com.family.normal.service.ISupportRecordService; 
import com.family.normal.mapper.SupportRecordMapper; 
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
* 辅助记录表控制器 
* Auther:feng
* Date:2020-09-29 17:04:10
*/ 
@RestController 
@RequestMapping("supportRecord/v1.0") 
@Api(tags = "辅助记录表的接口") 
public class SupportRecordController {  
	@Autowired 
	private ISupportRecordService iSupportRecordService; 
	@Autowired 
	private SupportRecordMapper iSupportRecordMapper; 


Logger logger=LoggerFactory.getLogger(SupportRecordController.class);	/** 
	* 依据ID获取辅助记录表详情 
	* Auther:feng
	*/ 
	@PostMapping("getSupportRecordSingleById") 
	@ApiOperation("依据ID获取辅助记录表详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "supportRecordId", value = "辅助记录表的id", required = false,example="1") })
	public  ResultObject getSupportRecordSingleById(Integer supportRecordId) {  
		try{ 
		  SupportRecordVO entity=new SupportRecordVO(); 
		  entity=iSupportRecordService.getSingleInfoById(supportRecordId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取辅助记录表详情异常"); 
		  return ResultUtil.error("依据ID获取辅助记录表详情异常"); 
		} 
	} 
	/** 
	* 获取辅助记录表单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getSupportRecordSingle") 
	@ApiOperation("获取辅助记录表单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getSupportRecordSingle(SupportRecordDO supportRecord) {  
		try{ 
		 SupportRecordVO supportRecordVO=iSupportRecordService.getSingleInfo(supportRecord); 
		  return ResultUtil.successData(supportRecordVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取辅助记录表单条记录异常"); 
		  return ResultUtil.error("获取辅助记录表单条记录异常"); 
		} 
	} 
	/** 
	* 获取辅助记录表列表 
	* Auther:feng
	*/ 
	@PostMapping("getSupportRecordList") 
	@ApiOperation("获取辅助记录表列表") 
	@ApiImplicitParams({ })
	public ResultObject getSupportRecordList(SupportRecordDO supportRecord) {  
		try{ 
		  List<SupportRecordVO> lst = iSupportRecordService.getSupportRecordList(supportRecord); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取辅助记录表列表记录异常"); 
		  return ResultUtil.error("获取辅助记录表列表记录异常"); 
		} 
	} 
	/** 
	* 获取辅助记录表分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getSupportRecordListByPage") 
	@ApiOperation("获取辅助记录表分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getSupportRecordListByPage(SupportRecordDO supportRecord,LayuiPage<SupportRecordVO> layuiPage) {  
		try{ 
		  return iSupportRecordService.getSupportRecordListByPage(supportRecord, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取辅助记录表分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加辅助记录表方法 
	* Auther:feng
	*/ 
	@PostMapping("addSupportRecord") 
	@ApiOperation("添加辅助记录表") 
	@ApiImplicitParams({  })
	public  ResultObject addSupportRecord(SupportRecordAD supportRecordad) {  
		try{ 
		  SupportRecord supportRecord=EntityChangeRquestView.createDOToEntity(supportRecordad, new SupportRecord()); 
		  return iSupportRecordService.addNewSupportRecord(supportRecord); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加辅助记录表异常"); 
		  return ResultUtil.error("添加辅助记录表异常"); 
		} 
	} 
	/** 
	* 修改辅助记录表方法 
	* Auther:feng
	*/ 
	@PostMapping("modSupportRecord") 
	@ApiOperation("修改辅助记录表") 
	@ApiImplicitParams({  })
	public  ResultObject modSupportRecord(SupportRecordAD supportRecordad) {  
		try{ 
		  SupportRecord supportRecord=EntityChangeRquestView.createDOToEntity(supportRecordad, new SupportRecord()); 
			  return iSupportRecordService.modSupportRecord(supportRecord); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改辅助记录表异常"); 
		  return ResultUtil.error("修改辅助记录表异常"); 
		} 
	} 
	/** 
	* 删除辅助记录表 
	* Auther:feng
	*/ 
	@PostMapping("delSupportRecord") 
	@ApiOperation("删除辅助记录表方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "supportRecordIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delSupportRecord(String supportRecordIds) {  
		try{ 
		  if(StringUtils.isNull(supportRecordIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=supportRecordIds.split(",");
		 for(String str:strs) {
			  iSupportRecordMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除辅助记录表方法异常 "); 
		  return ResultUtil.error("删除辅助记录表方法异常 "); 
		} 
	} 
}
