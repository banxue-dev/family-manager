package com.family.wb.service; 
import com.family.wb.entity.FriendshipLinkInfo; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.wb.entity.VO.FriendshipLinkInfoVO; 
import com.family.wb.entity.DO.FriendshipLinkInfoDO; 
import java.util.List;
/** 
* FriendshipLinkInfo服务接口层 
* Auther:feng
*/ 
public interface IFriendshipLinkInfoService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public FriendshipLinkInfoVO getSingleInfo(FriendshipLinkInfoDO friendshipLinkInfoDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	FriendshipLinkInfoVO getSingleInfoById(Integer friendshipLinkId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<FriendshipLinkInfoVO> getFriendshipLinkInfoList(FriendshipLinkInfoDO friendshipLinkInfoDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<FriendshipLinkInfoVO> getFriendshipLinkInfoListByPage(FriendshipLinkInfoDO friendshipLinkInfoDO, LayuiPage<FriendshipLinkInfoVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delFriendshipLinkInfo(FriendshipLinkInfo friendshipLinkInfo); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modFriendshipLinkInfo(FriendshipLinkInfo friendshipLinkInfo);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewFriendshipLinkInfo(FriendshipLinkInfo friendshipLinkInfo);
}
