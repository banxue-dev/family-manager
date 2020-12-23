package com.family.system.service.Impl; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.system.entity.RoleMenu;
import com.family.system.entity.DO.RoleMenuDO;
import com.family.system.entity.VO.RoleMenuVO;
import com.family.system.mapper.RoleMenuMapper;
import com.family.system.service.IRoleMenuService;
import com.family.utils.EntityChangeRquestView;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;
import com.family.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example; 
/** 
* RoleMenu服务层 
* Auther:feng
* Date:2020-09-17 15:13:12
*/ 
@Service 
public class RoleMenuServiceImpl implements IRoleMenuService { 

	@Autowired
	private RoleMenuMapper iRoleMenuMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public RoleMenuVO getSingleInfo(RoleMenuDO roleMenuDO) {
		RoleMenu roleMenu=new RoleMenu();
		roleMenu= iRoleMenuMapper.selectOne(EntityChangeRquestView.createDOToEntity(roleMenuDO,new RoleMenu()));
		return this.structDetailData(roleMenu);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public RoleMenuVO getSingleInfoById(Integer roleMenuId) {
		RoleMenu roleMenu=new RoleMenu();
		roleMenu= iRoleMenuMapper.selectByPrimaryKey(roleMenuId);
		return this.structDetailData(roleMenu);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<RoleMenuVO> getRoleMenuList(RoleMenuDO roleMenuDO) {
		  Example example = getPublicExample(roleMenuDO);
		  List<RoleMenuVO> lstVO = new ArrayList<RoleMenuVO>();
		  List<RoleMenu> lst = iRoleMenuMapper.selectByExample(example); 
		lst.forEach(t->{
		  RoleMenuVO vo=this.structDetailData(t);
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
	public LayuiPage<RoleMenuVO> getRoleMenuListByPage(RoleMenuDO roleMenuDO, LayuiPage<RoleMenuVO> layuiPage){
		  Example example = getPublicExample(roleMenuDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<RoleMenuVO> lstVO = new ArrayList<RoleMenuVO>();
		  List<RoleMenu> lst = iRoleMenuMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  RoleMenuVO vo=this.structDetailData(t);
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
	public ResultObject delRoleMenu(RoleMenu roleMenu) {
		 int i= iRoleMenuMapper.updateByPrimaryKeySelective(roleMenu);
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
	public ResultObject modRoleMenu(RoleMenu roleMenu) {
		int i= iRoleMenuMapper.updateByPrimaryKeySelective(roleMenu);
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
	public ResultObject addNewRoleMenu( String menuIds,String roleId) {
		if(StringUtils.isNullStrings(menuIds,roleId)) {
			 ResultUtil.error("参数错误");
		}
		RoleMenu hapar=new RoleMenu();
		hapar.setRoleId(Integer.parseInt(roleId));
		List<RoleMenu> haves=iRoleMenuMapper.select(hapar);
		String[] menuArr=menuIds.split(",");
//		List<String> newmes=Arrays.asList(menuArr);
		List<Integer> newmes=new ArrayList<>();
		for(String menuId:menuArr) {
			newmes.add(Integer.parseInt(menuId));
		}
		if(haves.size()>0) {
			List<Integer> haveIds = haves.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
			Map<Integer,Integer> haveMaps=haves.stream().collect(Collectors.toMap(RoleMenu::getMenuId, RoleMenu::getRoleMenuId));
			//先求删除的，既新的在老的里面没有，就是新添加的
			for(Integer menuId:newmes) {
				if(haveIds.indexOf(menuId)==-1) {
					RoleMenu rm=new RoleMenu();
					rm.setMenuId(menuId);
					rm.setRoleId(Integer.parseInt(roleId));
					iRoleMenuMapper.insertSelective(rm);
				}
			}
			//找添加的，既老的在新的里面没有，就是被删了
			for(Integer hid:haveIds) {
				if(newmes.indexOf(hid)==-1) {
					//没有，表示被删除了
					iRoleMenuMapper.deleteByPrimaryKey(haveMaps.get(hid));
				}
			}
		}else {
			for(String menuId:menuArr) {
				RoleMenu rm=new RoleMenu();
				rm.setMenuId(Integer.parseInt(menuId));
				rm.setRoleId(Integer.parseInt(roleId));
				iRoleMenuMapper.insertSelective(rm);
			}
		}
		
//		RoleMenu remove=new RoleMenu();
//		remove.setRoleId(Integer.parseInt(roleId));
//		iRoleMenuMapper.delete(remove);
//		String[] menuArr=menuIds.split(",");
//		for(String menuId:menuArr) {
//			RoleMenu rm=new RoleMenu();
//			rm.setMenuId(Integer.parseInt(menuId));
//			rm.setRoleId(Integer.parseInt(roleId));
//			iRoleMenuMapper.insertSelective(rm);
//		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private RoleMenuVO structDetailData(RoleMenu roleMenu) { 
		 if(roleMenu==null){ 
			 return null; 
		} 
		RoleMenuVO vo= EntityChangeRquestView.createEntityToVO(roleMenu,new RoleMenuVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(RoleMenuDO roleMenuDO) { 
		Example example = new Example(RoleMenu.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(roleMenuDO,new RoleMenu())); 
		return example; 
	}
}
