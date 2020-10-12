package com.family.system.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.system.entity.Menu;
import com.family.system.entity.RoleMenu;
import com.family.system.entity.DO.MenuDO;
import com.family.system.entity.VO.MenuVO;
import com.family.system.entity.VO.TreeMenuVO;
import com.family.system.mapper.MenuMapper;
import com.family.system.mapper.RoleMenuMapper;
import com.family.system.service.IMenuService;
import com.family.utils.EntityChangeRquestView;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;
import com.family.utils.StringUtils;
import com.family.utils.TimeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * Menu服务层 Auther:feng Date:2020-09-16 16:04:43
 */
@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuMapper iMenuMapper;
	@Autowired
	private RoleMenuMapper iRoleMenuMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public MenuVO getSingleInfo(MenuDO menuDO) {
		Menu menu = new Menu();
		menu = iMenuMapper.selectOne(EntityChangeRquestView.createDOToEntity(menuDO, new Menu()));
		return this.structDetailData(menu);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public MenuVO getSingleInfoById(Integer menuId) {
		Menu menu = new Menu();
		menu = iMenuMapper.selectByPrimaryKey(menuId);
		return this.structDetailData(menu);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<MenuVO> getMenuList(MenuDO menuDO) {
		Example example = getPublicExample(menuDO);
		List<MenuVO> lstVO = new ArrayList<MenuVO>();
		List<Menu> lst = iMenuMapper.selectByExample(example);
		lst.forEach(t -> {
			MenuVO vo = this.structDetailData(t);
			if (vo != null) {
				lstVO.add(vo);
			}
		});
		return lstVO;
	}
	@Override
	public List<TreeMenuVO> getLeftMenu(MenuDO menuDO) {
		Example example = getPublicExample(menuDO);
		List<MenuVO> lstVO = new ArrayList<MenuVO>();

		Example roleMenuExample = new Example(RoleMenu.class);
		Example.Criteria criteria = roleMenuExample.createCriteria();
		criteria.andIn("roleId", menuDO.getRoleIds());
		List<RoleMenu> haveMenu=iRoleMenuMapper.selectByExample(roleMenuExample);
		List<Integer> haveIds=new ArrayList<>();
		if(haveMenu.size()>0) {

			haveIds=haveMenu.stream().map(t->t.getMenuId()).collect(Collectors.toList());
		}
		haveIds=haveMenu.stream().map(t->t.getMenuId()).collect(Collectors.toList());
		List<Menu> lst = iMenuMapper.selectByExample(example);
		lst.forEach(t -> {
			MenuVO vo = this.structDetailData(t);
			if (vo != null) {
				lstVO.add(vo);
			}
		});
		
		return buildTree(lstVO,0,haveIds,menuDO.getCatchType());
	}
	public static List<TreeMenuVO> buildTree(List<MenuVO> list, int fid,List<Integer> haveIds,int ctype) {
        List<TreeMenuVO> resultList = new ArrayList<TreeMenuVO>();
        if (list == null || list.size() == 0) {
            return new ArrayList<TreeMenuVO>();
        }
        for (MenuVO menu : list) {
            if (menu.getParentId().equals(fid)) {
            	if(haveIds.indexOf(menu.getMenuId())!=-1) {
                	TreeMenuVO tree=new TreeMenuVO();
                	tree.setId(menu.getMenuId());
                	tree.setIcon(menu.getMenuIcon());
                	tree.setTitle(menu.getMenuTitle());
                	tree.setName(menu.getMenuName());
                	tree.setJump(menu.getMenuUrl());
                	tree.setParentId(menu.getParentId());
                    tree.setChildren(buildTree(list, menu.getMenuId(),haveIds,ctype));
                    resultList.add(tree);
            	}
            }
        }
        return resultList;
    }

	@Override
	public List<TreeMenuVO> getLeftMenuByRole(MenuDO menuDO) {
		// TODO Auto-generated method stub
		Example example = getPublicExample(menuDO);
		List<MenuVO> lstVO = new ArrayList<MenuVO>();

		Example roleMenuExample = new Example(RoleMenu.class);
		Example.Criteria criteria = roleMenuExample.createCriteria();
		criteria.andIn("roleId", menuDO.getRoleIds());
		List<RoleMenu> haveMenu=iRoleMenuMapper.selectByExample(roleMenuExample);
		List<Integer> haveIds=new ArrayList<>();
		if(haveMenu.size()>0) {

			haveIds=haveMenu.stream().map(t->t.getMenuId()).collect(Collectors.toList());
		}
		haveIds=haveMenu.stream().map(t->t.getMenuId()).collect(Collectors.toList());
		List<Menu> lst = iMenuMapper.selectByExample(example);
		lst.forEach(t -> {
			MenuVO vo = this.structDetailData(t);
			if (vo != null) {
				lstVO.add(vo);
			}
		});
		
		return buildTreeByRoleList(lstVO,0,haveIds,menuDO.getCatchType());
	}
	public static List<TreeMenuVO> buildTreeByRoleList(List<MenuVO> list, int fid,List<Integer> haveIds,int ctype) {
		List<TreeMenuVO> resultList = new ArrayList<TreeMenuVO>();
		if (list == null || list.size() == 0) {
			return new ArrayList<TreeMenuVO>();
		}
		for (MenuVO menu : list) {
			if (menu.getParentId().equals(fid)) {
				TreeMenuVO tree=new TreeMenuVO();
				tree.setId(menu.getMenuId());
				tree.setIcon(menu.getMenuIcon());
				tree.setTitle(menu.getMenuTitle());
				tree.setName(menu.getMenuName());
				tree.setJump(menu.getMenuUrl());
				tree.setParentId(menu.getParentId());
				if(haveIds.indexOf(menu.getMenuId())!=-1) {
					tree.setChecked(true);
				}
				tree.setChildren(buildTreeByRoleList(list, menu.getMenuId(),haveIds,ctype));
				/**
				 * 1这个操作是，如果他有下级，那么就不要选中，因为前端tree插件选中上级的话会把下级全部选中
				 * 2所以这里只有在他没有下级时才去判断选中
				 * 3、只有类型为2时才这么做
				 */
				if(ctype==1) {
					if(tree.getChildren().size()>0) {
						tree.setChecked(false);
					}
				}
				resultList.add(tree);
			}
		}
		return resultList;
	}

	/**
	 * 获取分页记录 Auther:feng
	 */
	@Override
	@Transactional
	public LayuiPage<MenuVO> getMenuListByPage(MenuDO menuDO,LayuiPage<MenuVO> layuiPage) {
		
		Example example = getPublicExample(menuDO);
		if(StringUtils.isNotNull(layuiPage.getSort())) {

			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		}else {
			
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<MenuVO> lstVO = new ArrayList<MenuVO>();
		List<Menu> lst = iMenuMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		lst.forEach(t -> {
			MenuVO vo = this.structDetailData(t);
			if (vo != null) {
				lstVO.add(vo);
			}
		});
		pageInfo.setList(lstVO);
		layuiPage = new LayuiPage<>(pageInfo);
		return layuiPage;
	}

	/**
	 * 删除记录 Auther:feng
	 */
	@Override
	@Transactional
	public ResultObject delMenu(String menuIds) {
		String[] menstr=menuIds.split(",");
		for(String id:menstr) {
			//清理子菜单
			Menu m=new Menu();
			m.setParentId(Integer.parseInt(id));
			iMenuMapper.delete(m);
			//删除本菜单
			iMenuMapper.deleteByPrimaryKey(id);
			RoleMenu mr=new RoleMenu();
			mr.setMenuId(Integer.parseInt(id));
			iRoleMenuMapper.delete(mr);
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 修改信息 Auther:feng
	 */
	@Override
	@Transactional
	public ResultObject modMenu(Menu menu) {
		int i = iMenuMapper.updateByPrimaryKeySelective(menu);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 添加信息 Auther:feng
	 */
	@Override
	@Transactional
	public ResultObject addNewMenu(Menu menu) {
		//不是菜单类型的话，就不在左侧展示
		if(menu.getMenuType()!=0) {
			menu.setIfShow(1);
		}
		menu.setMenuId(null);
		menu.setCreateTime(TimeUtils.getCurrentTime());
		int i = iMenuMapper.insertSelective(menu);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private MenuVO structDetailData(Menu menu) {
		if (menu == null) {
			return null;
		}
		MenuVO vo = EntityChangeRquestView.createEntityToVO(menu, new MenuVO());
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(MenuDO menuDO) {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(menuDO, new Menu()));
		return example;
	}

}
