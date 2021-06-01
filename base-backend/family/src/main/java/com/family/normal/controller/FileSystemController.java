package com.family.normal.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.family.normal.entity.FileSystem;
import com.family.normal.entity.ImgManager;
import com.family.normal.entity.DO.FileSystemAD;
import com.family.normal.entity.VO.FileSystemVO;
import com.family.normal.entity.DO.FileSystemDO;
import com.family.normal.service.IFileSystemService;
import com.family.normal.mapper.FileSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.family.utils.EntityChangeRquestView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.io.File;
import java.util.List;
import io.swagger.annotations.ApiOperation;
import com.family.utils.ResultObject;
import com.family.utils.LayuiPage;
import com.family.utils.ResultUtil;

import com.family.utils.StringUtils;

/**
 * 文件系统管理控制器 Auther:feng Date:2021-06-01 10:15:41
 */
@RestController
@RequestMapping("fileSystem/v1.0")
@Api(tags = "文件系统管理的接口")
public class FileSystemController {
	@Autowired
	private IFileSystemService iFileSystemService;
	@Autowired
	private FileSystemMapper iFileSystemMapper;
	@Value("${uploadFile.hostPath}")
    private String hostPath;

	Logger logger = LoggerFactory.getLogger(FileSystemController.class);

	/**
	 * 依据ID获取文件系统管理详情 Auther:feng
	 */
	@PostMapping("getFileSystemSingleById")
	@ApiOperation("依据ID获取文件系统管理详情")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fileSystemId", value = "文件系统管理的id", required = false, example = "1") })
	public ResultObject getFileSystemSingleById(Integer fileSystemId) {
		try {
			FileSystemVO entity = new FileSystemVO();
			entity = iFileSystemService.getSingleInfoById(fileSystemId);
			return ResultUtil.successData(entity);
		} catch (Exception e) {
			logger.error(e + "依据ID获取文件系统管理详情异常");
			return ResultUtil.error("依据ID获取文件系统管理详情异常");
		}
	}

	/**
	 * 获取文件系统管理单条记录 Auther:feng
	 */
	@PostMapping("getFileSystemSingle")
	@ApiOperation("获取文件系统管理单条记录")
	@ApiImplicitParams({})
	public ResultObject getFileSystemSingle(FileSystemDO fileSystem) {
		try {
			FileSystemVO fileSystemVO = iFileSystemService.getSingleInfo(fileSystem);
			return ResultUtil.successData(fileSystemVO);
		} catch (Exception e) {
			logger.error(e + "获取文件系统管理单条记录异常");
			return ResultUtil.error("获取文件系统管理单条记录异常");
		}
	}

	/**
	 * 获取文件系统管理列表 Auther:feng
	 */
	@PostMapping("getFileSystemList")
	@ApiOperation("获取文件系统管理列表")
	@ApiImplicitParams({})
	public ResultObject getFileSystemList(FileSystemDO fileSystem) {
		try {
			List<FileSystemVO> lst = iFileSystemService.getFileSystemList(fileSystem);
			return ResultUtil.successData(lst);
		} catch (Exception e) {
			logger.error(e + "获取文件系统管理列表记录异常");
			return ResultUtil.error("获取文件系统管理列表记录异常");
		}
	}

	/**
	 * 获取文件系统管理分页数据 Auther:feng
	 */
	@PostMapping("getFileSystemListByPage")
	@ApiOperation("获取文件系统管理分页数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, example = "1"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, example = "1"),
			@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false, example = "1"),
			@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false, example = "1") })
	public LayuiPage getFileSystemListByPage(FileSystemDO fileSystem, LayuiPage<FileSystemVO> layuiPage) {
		try {
			return iFileSystemService.getFileSystemListByPage(fileSystem, layuiPage);
		} catch (Exception e) {
			logger.error(e + "获取文件系统管理分页记录异常");
			return layuiPage;
		}
	}

	/**
	 * 添加文件系统管理方法 Auther:feng
	 */
	@PostMapping("addFileSystem")
	@ApiOperation("添加文件系统管理")
	@ApiImplicitParams({})
	public ResultObject addFileSystem(FileSystemAD fileSystemad) {
		try {
			FileSystem fileSystem = EntityChangeRquestView.createDOToEntity(fileSystemad, new FileSystem());
			return iFileSystemService.addNewFileSystem(fileSystem);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "添加文件系统管理异常");
			return ResultUtil.error("添加文件系统管理异常");
		}
	}

	/**
	 * 修改文件系统管理方法 Auther:feng
	 */
	@PostMapping("modFileSystem")
	@ApiOperation("修改文件系统管理")
	@ApiImplicitParams({})
	public ResultObject modFileSystem(FileSystemAD fileSystemad) {
		try {
			FileSystem fileSystem = EntityChangeRquestView.createDOToEntity(fileSystemad, new FileSystem());
			return iFileSystemService.modFileSystem(fileSystem);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "修改文件系统管理异常");
			return ResultUtil.error("修改文件系统管理异常");
		}
	}

	/**
	 * 删除文件系统管理 Auther:feng
	 */
	@PostMapping("delFileSystem")
	@ApiOperation("删除文件系统管理方法")
	@ApiImplicitParams({ @ApiImplicitParam(name = "fileSystemIds", value = "主键id数据",example="1", required = false) })
	public  ResultObject delImgManager(String fileSystemIds) {  

		try{ 
		  if(StringUtils.isNull(fileSystemIds)) {
			  return ResultUtil.error("错误的参数");
		  }
		 String[] strs=fileSystemIds.split(",");
		 for(String str:strs) {
			 try {
				 FileSystem im=iFileSystemMapper.selectByPrimaryKey(str);
				 File f1=new File(hostPath+im.getFilePath());
				 f1.delete();
			 }catch(Exception dl) {
				 logger.error("物理删除图片时失败");
			 }
			 iFileSystemMapper.deleteByPrimaryKey(str);
		 }
		 return ResultUtil.success();  
		}catch(Exception e){ 
		  logger.error(e+"删除图片管理方法异常 "); 
		  return ResultUtil.error("删除图片管理方法异常 "); 
		} 
	} 
}
