package com.family.normal.service; 
import com.family.normal.entity.FileSystem; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.normal.entity.VO.FileSystemVO; 
import com.family.normal.entity.DO.FileSystemDO; 
import java.util.List;
/** 
* FileSystem服务接口层 
* Auther:feng
*/ 
public interface IFileSystemService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public FileSystemVO getSingleInfo(FileSystemDO fileSystemDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	FileSystemVO getSingleInfoById(Integer normalFileSysId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<FileSystemVO> getFileSystemList(FileSystemDO fileSystemDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<FileSystemVO> getFileSystemListByPage(FileSystemDO fileSystemDO, LayuiPage<FileSystemVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delFileSystem(FileSystem fileSystem); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modFileSystem(FileSystem fileSystem);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewFileSystem(FileSystem fileSystem);
}
