package com.family.system.service; 
import com.family.system.entity.Menu; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject; 
import com.family.system.entity.VO.MenuVO;
import com.family.system.entity.VO.TreeMenuVO;
import com.family.system.entity.DO.MenuDO; 
import java.util.List;
/** 
* Menu服务接口层 
* Auther:feng
*/ 
public interface IMenuService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public MenuVO getSingleInfo(MenuDO menuDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	MenuVO getSingleInfoById(Integer menuId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<MenuVO> getMenuList(MenuDO menuDO);
	public List<TreeMenuVO> getLeftMenu(MenuDO menuDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<MenuVO> getMenuListByPage(MenuDO menuDO, LayuiPage<MenuVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delMenu(String menuIds); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modMenu(Menu menu);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewMenu(Menu menu);
}
