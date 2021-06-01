package com.family.normal.service.Impl; 
import com.family.normal.entity.FileSystem; 
import com.family.normal.mapper.FileSystemMapper; 
import com.family.normal.service.IFileSystemService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.normal.entity.VO.FileSystemVO; 
import com.family.normal.entity.DO.FileSystemDO; 
import com.github.pagehelper.PageHelper; 
import com.family.utils.ResultUtil; 
import com.family.utils.ResultObject; 
import javax.persistence.Transient; 
import org.springframework.transaction.annotation.Transactional; 
import tk.mybatis.mapper.entity.Example; 
import java.util.ArrayList; 
import com.family.utils.TimeUtils; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired; 
import java.util.Map; 
import java.util.List; 
/** 
* FileSystem服务层 
* Auther:feng
* Date:2021-06-01 10:15:41
*/ 
@Service 
public class FileSystemServiceImpl implements IFileSystemService { 

	@Autowired
	private FileSystemMapper iFileSystemMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public FileSystemVO getSingleInfo(FileSystemDO fileSystemDO) {
		FileSystem fileSystem=new FileSystem();
		fileSystem= iFileSystemMapper.selectOne(EntityChangeRquestView.createDOToEntity(fileSystemDO,new FileSystem()));
		return this.structDetailData(fileSystem);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public FileSystemVO getSingleInfoById(Integer normalFileSysId) {
		FileSystem fileSystem=new FileSystem();
		fileSystem= iFileSystemMapper.selectByPrimaryKey(normalFileSysId);
		return this.structDetailData(fileSystem);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<FileSystemVO> getFileSystemList(FileSystemDO fileSystemDO) {
		  Example example = getPublicExample(fileSystemDO);
		  List<FileSystemVO> lstVO = new ArrayList<FileSystemVO>();
		  List<FileSystem> lst = iFileSystemMapper.selectByExample(example); 
		lst.forEach(t->{
		  FileSystemVO vo=this.structDetailData(t);
		  if(vo!=null) {
		  	lstVO.add(vo);
		  }
		});
	return lstVO;
	} 
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public LayuiPage<FileSystemVO> getFileSystemListByPage(FileSystemDO fileSystemDO, LayuiPage<FileSystemVO> layuiPage){
		  Example example = getPublicExample(fileSystemDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<FileSystemVO> lstVO = new ArrayList<FileSystemVO>();
		  List<FileSystem> lst = iFileSystemMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  FileSystemVO vo=this.structDetailData(t);
		  if(vo!=null) {
		  	lstVO.add(vo);
		  }
		});
		pageInfo.setList(lstVO);
		layuiPage = new LayuiPage<>(pageInfo);
		  return layuiPage; 
	}
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public ResultObject delFileSystem(FileSystem fileSystem) {
		 int i= iFileSystemMapper.updateByPrimaryKeySelective(fileSystem);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public ResultObject modFileSystem(FileSystem fileSystem) {
		int i= iFileSystemMapper.updateByPrimaryKeySelective(fileSystem);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public ResultObject addNewFileSystem(FileSystem fileSystem) {
		fileSystem.setCreateTime(TimeUtils.getCurrentTime());
		int i= iFileSystemMapper.insertSelective(fileSystem);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private FileSystemVO structDetailData(FileSystem fileSystem) { 
		 if(fileSystem==null){ 
			 return null; 
		} 
		FileSystemVO vo= EntityChangeRquestView.createEntityToVO(fileSystem,new FileSystemVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(FileSystemDO fileSystemDO) { 
		Example example = new Example(FileSystem.class); 
		Example.Criteria criteria = example.createCriteria(); 
		if (StringUtils.isNotNull(fileSystemDO.getFileName())) {
			criteria.andLike("fileName", "%" + fileSystemDO.getFileName() + "%");
		}
		fileSystemDO.setFileName(null);
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(fileSystemDO,new FileSystem())); 
		return example; 
	}
}
