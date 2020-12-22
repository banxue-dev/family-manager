package com.family.normal.controller; 
import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.normal.entity.ImgManager;
import com.family.normal.entity.DO.ImgManagerAD;
import com.family.normal.entity.DO.ImgManagerDO;
import com.family.normal.entity.VO.ImgManagerVO;
import com.family.normal.mapper.ImgManagerMapper;
import com.family.normal.service.IImgManagerService;
import com.family.utils.EntityChangeRquestView;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;
import com.family.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;  

/** 
* 图片管理控制器 
* Auther:feng
* Date:2020-12-16 15:47:27
*/ 
@RestController 
@RequestMapping("imgManager/v1.0") 
@Api(tags = "图片管理的接口") 
public class ImgManagerController {  
	@Autowired 
	private IImgManagerService iImgManagerService; 
	@Autowired 
	private ImgManagerMapper iImgManagerMapper; 

	@Value("${uploadFile.hostPath}")
    private String hostPath;
	Logger logger=LoggerFactory.getLogger(ImgManagerController.class);	/** 
	* 依据ID获取图片管理详情 
	* Auther:feng
	*/ 
	@PostMapping("getImgManagerSingleById") 
	@ApiOperation("依据ID获取图片管理详情") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "imgManagerId", value = "图片管理的id", required = false,example="1") })
	public  ResultObject getImgManagerSingleById(Integer imgManagerId) {  
		try{ 
		  ImgManagerVO entity=new ImgManagerVO(); 
		  entity=iImgManagerService.getSingleInfoById(imgManagerId); 
		  return ResultUtil.successData(entity); 
		}catch(Exception e){ 
		  logger.error(e+"依据ID获取图片管理详情异常"); 
		  return ResultUtil.error("依据ID获取图片管理详情异常"); 
		} 
	} 
	/** 
	* 获取图片管理单条记录 
	* Auther:feng
	*/ 
	@PostMapping("getImgManagerSingle") 
	@ApiOperation("获取图片管理单条记录") 
	@ApiImplicitParams({  })
	public ResultObject getImgManagerSingle(ImgManagerDO imgManager) {  
		try{ 
		 ImgManagerVO imgManagerVO=iImgManagerService.getSingleInfo(imgManager); 
		  return ResultUtil.successData(imgManagerVO); 
		}catch(Exception e){ 
		  logger.error(e+"获取图片管理单条记录异常"); 
		  return ResultUtil.error("获取图片管理单条记录异常"); 
		} 
	} 
	/** 
	* 获取图片管理列表 
	* Auther:feng
	*/ 
	@PostMapping("getImgManagerList") 
	@ApiOperation("获取图片管理列表") 
	@ApiImplicitParams({ })
	public ResultObject getImgManagerList(ImgManagerDO imgManager) {  
		try{ 
		  List<ImgManagerVO> lst = iImgManagerService.getImgManagerList(imgManager); 
		  return ResultUtil.successData(lst); 
		}catch(Exception e){ 
		  logger.error(e+"获取图片管理列表记录异常"); 
		  return ResultUtil.error("获取图片管理列表记录异常"); 
		} 
	} 
	/** 
	* 获取图片管理分页数据 
	* Auther:feng
	*/ 
	@PostMapping("getImgManagerListByPage") 
	@ApiOperation("获取图片管理分页数据") 
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "当前页", required = true,example="1"), 
	@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true,example="1"), 
	@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false,example="1"), 
	@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false,example="1")
})
	public LayuiPage getImgManagerListByPage(ImgManagerDO imgManager,LayuiPage<ImgManagerVO> layuiPage) {  
		try{ 
		  return iImgManagerService.getImgManagerListByPage(imgManager, layuiPage); 
		}catch(Exception e){ 
		  logger.error(e+"获取图片管理分页记录异常"); 
		  return layuiPage;
		} 
	} 
	/** 
	* 添加图片管理方法 
	* Auther:feng
	*/ 
	@PostMapping("addImgManager") 
	@ApiOperation("添加图片管理") 
	@ApiImplicitParams({  })
	public  ResultObject addImgManager(ImgManagerAD imgManagerad) {  
		try{ 
		  ImgManager imgManager=EntityChangeRquestView.createDOToEntity(imgManagerad, new ImgManager()); 
		  return iImgManagerService.addNewImgManager(imgManager); 
		  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"添加图片管理异常"); 
		  return ResultUtil.error("添加图片管理异常"); 
		} 
	} 
	/** 
	* 修改图片管理方法 
	* Auther:feng
	*/ 
	@PostMapping("modImgManager") 
	@ApiOperation("修改图片管理") 
	@ApiImplicitParams({  })
	public  ResultObject modImgManager(ImgManagerAD imgManagerad) {  
		try{ 
		  ImgManager imgManager=EntityChangeRquestView.createDOToEntity(imgManagerad, new ImgManager()); 
			  return iImgManagerService.modImgManager(imgManager); 
			  //return ResultUtil.success("成功"); 
		}catch(Exception e){ 
		  logger.error(e+"修改图片管理异常"); 
		  return ResultUtil.error("修改图片管理异常"); 
		} 
	} 
	/** 
	* 删除图片管理 
	* Auther:feng
	*/ 
	@PostMapping("delImgManager") 
	@ApiOperation("删除图片管理方法") 
	@ApiImplicitParams({ @ApiImplicitParam(name = "imgManagerIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delImgManager(String imgManagerIds) {  
		try{ 
		  if(StringUtils.isNull(imgManagerIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=imgManagerIds.split(",");
		 for(String str:strs) {
			 try {

				 ImgManager im=iImgManagerMapper.selectByPrimaryKey(str);
				 File f1=new File(hostPath+im.getImgPath());
				 File f2=new File(hostPath+im.getThumImgPath());
				 f1.delete();
				 f2.delete();
			 }catch(Exception dl) {
				 logger.error("物理删除图片时失败");
			 }
			 iImgManagerMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除图片管理方法异常 "); 
		  return ResultUtil.error("删除图片管理方法异常 "); 
		} 
	} 
}
