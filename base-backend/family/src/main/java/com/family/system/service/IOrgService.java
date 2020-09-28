package com.family.system.service; 
import com.family.system.entity.Org; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.system.entity.VO.OrgVO; 
import com.family.system.entity.DO.OrgDO; 
import java.util.List;
/** 
* Org服务接口层 
* Auther:feng
*/ 
public interface IOrgService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public OrgVO getSingleInfo(OrgDO orgDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	OrgVO getSingleInfoById(Integer orgId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<OrgVO> getOrgList(OrgDO orgDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<OrgVO> getOrgListByPage(OrgDO orgDO, LayuiPage<OrgVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delOrg(Org org); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modOrg(Org org);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewOrg(Org org);
}
