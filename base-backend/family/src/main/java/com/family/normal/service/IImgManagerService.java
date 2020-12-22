package com.family.normal.service; 
import com.family.normal.entity.ImgManager; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.normal.entity.VO.ImgManagerVO; 
import com.family.normal.entity.DO.ImgManagerDO; 
import java.util.List;
/** 
* ImgManager服务接口层 
* Auther:feng
*/ 
public interface IImgManagerService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public ImgManagerVO getSingleInfo(ImgManagerDO imgManagerDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	ImgManagerVO getSingleInfoById(Integer normalImgManagerId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<ImgManagerVO> getImgManagerList(ImgManagerDO imgManagerDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<ImgManagerVO> getImgManagerListByPage(ImgManagerDO imgManagerDO, LayuiPage<ImgManagerVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delImgManager(ImgManager imgManager); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modImgManager(ImgManager imgManager);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewImgManager(ImgManager imgManager);
}
