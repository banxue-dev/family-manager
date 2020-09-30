package com.family.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.system.entity.Menu;
import com.family.system.entity.DO.MenuAD;
import com.family.system.entity.DO.MenuDO;
import com.family.system.entity.VO.MenuVO;
import com.family.system.entity.VO.TreeMenuVO;
import com.family.system.mapper.MenuMapper;
import com.family.system.mapper.UserRoleMapper;
import com.family.system.service.IMenuService;
import com.family.utils.EntityChangeRquestView;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单表控制器 Auther:feng Date:2020-09-16 16:04:43
 */
@RestController
@RequestMapping("menu/v1.0")
@Api(tags = "菜单表的接口")
public class MenuController {
	@Autowired
	private IMenuService iMenuService;
	@Autowired
	private MenuMapper iMenuMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	Logger logger = LoggerFactory.getLogger(MenuController.class);

	/**
	 * 依据ID获取菜单表详情 Auther:feng
	 */
	@PostMapping("getMenuSingleById")
	@ApiOperation("依据ID获取菜单表详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "menuId", value = "菜单表的id", required = false, example = "1") })
	public ResultObject getMenuSingleById(Integer menuId) {
		try {
			MenuVO entity = new MenuVO();
			entity = iMenuService.getSingleInfoById(menuId);
			return ResultUtil.successData(entity);
		} catch (Exception e) {
			logger.error(e + "依据ID获取菜单表详情异常");
			return ResultUtil.error("异常");
		}
	}
	
	

	/**
	 * 获取菜单表单条记录 Auther:feng
	 */
	@PostMapping("getMenuSingle")
	@ApiOperation("获取菜单表单条记录")
	@ApiImplicitParams({})
	public ResultObject getMenuSingle(MenuDO menu) {
		try {
			MenuVO menuVO = iMenuService.getSingleInfo(menu);
			return ResultUtil.successData(menuVO);
		} catch (Exception e) {
			logger.error(e + "获取菜单表单条记录异常");
			return ResultUtil.error("异常");
		}
	}
	/**
	 * 获取菜单表单条记录 Auther:feng
	 */
	@PostMapping("api/refreshUserAuth")
	@ApiOperation("更新用户的权限")
	@ApiImplicitParams({})
	public ResultObject refreshUserAuth(@RequestHeader("userId") Integer userId) {
		try {
			if(userId==null || userId<1) {
				return ResultUtil.error("更新权限失败");
			}
			List<String> userAuth=iMenuMapper.getUserHaveAuth(userId);
			return ResultUtil.successData(userAuth);
		} catch (Exception e) {
			logger.error(e + "获取菜单表单条记录异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 获取菜单表列表 Auther:feng
	 */
	@PostMapping("getMenuList")
	@ApiOperation("获取菜单表列表")
	@ApiImplicitParams({})
	public ResultObject getMenuList(MenuDO menu) {
		try {
			List lst = iMenuService.getMenuList(menu);
			return ResultUtil.successData(lst);
		} catch (Exception e) {
			logger.error(e + "获取菜单表列表记录异常");
			return ResultUtil.error("异常");
		}
	}
	/**
	 * 获取菜单表列表 Auther:feng
	 */
	@RequestMapping("getLeftMenu")
	@ApiOperation("获取菜单表列表")
	@ApiImplicitParams({})
	public ResultObject getLeftMenu(MenuDO menu,@RequestHeader("userId") Integer userId) {
		try {
			if(userId==null || userId<1) {
				return ResultUtil.error("获取菜单异常,用户操作例外");
			}
			//只要要展示且类型为菜单的
			menu.setIfShow(0);
			menu.setMenuType(0);
			menu.setCatchType(0);
			List<Integer> roleIds=userRoleMapper.getUserRoleByUserId(userId);
			if(roleIds.size()<1) {
				return ResultUtil.success("用户暂无权限");
			}
			menu.setRoleIds(roleIds);
			List<TreeMenuVO> lst = iMenuService.getLeftMenu(menu);
			return ResultUtil.successData(lst);
		} catch (Exception e) {
			logger.error(e + "获取菜单表列表记录异常");
			return ResultUtil.error("获取菜单异常");
		}
	}
	/**
	 * 获取菜单表列表 Auther:feng
	 */
	@RequestMapping("getLeftMenuByRole")
	@ApiOperation("获取菜单表列表")
	@ApiImplicitParams({})
	public ResultObject getLeftMenuByRole(MenuDO menu,Integer roleId) {
		try {
			if(roleId==null || roleId<1) {
				return ResultUtil.error("获取菜单异常,用户操作例外");
			}
			menu.setCatchType(1);
			List<Integer> roleIds=new ArrayList<>();
			roleIds.add(roleId);
			menu.setRoleIds(roleIds);
			List<TreeMenuVO> lst = iMenuService.getLeftMenuByRole(menu);
			return ResultUtil.successData(lst);
		} catch (Exception e) {
			logger.error(e + "获取菜单表列表记录异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 获取菜单表分页数据 Auther:feng
	 */
	@PostMapping("getMenuListByPage")
	@ApiOperation("获取菜单表分页数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, example = "1"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, example = "1"),
			@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false, example = "1"),
			@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false, example = "1") })
	public LayuiPage<MenuVO> getMenuListByPage(MenuDO menu,LayuiPage<MenuVO> layuiPage) {
		try {
			return iMenuService.getMenuListByPage(menu, layuiPage);
		} catch (Exception e) {
			logger.error(e + "获取菜单表分页记录异常");
			return layuiPage;
		}
	}
	/**
	 * 获取菜单表分页数据 Auther:feng
	 */
	@PostMapping("getMenuTreeListByPage")
	@ApiOperation("获取菜单表分页数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, example = "1"),
		@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, example = "1"),
		@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false, example = "1"),
		@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false, example = "1") })
	public LayuiPage<MenuVO> getMenuTreeListByPage(MenuDO menu,LayuiPage<MenuVO> layuiPage) {
		try {
			layuiPage.setLimit(10000);
			return iMenuService.getMenuListByPage(menu, layuiPage);
		} catch (Exception e) {
			logger.error(e + "获取菜单表分页记录异常");
			return layuiPage;
		}
	}

	/**
	 * 添加菜单表方法 Auther:feng
	 */
	@PostMapping("addMenu")
	@ApiOperation("添加菜单表")
	@ApiImplicitParams({})
	public ResultObject addMenu(MenuAD menuad) {
		try {
			Menu menu = EntityChangeRquestView.createDOToEntity(menuad, new Menu());
			return iMenuService.addNewMenu(menu);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "添加菜单表异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 修改菜单表方法 Auther:feng
	 */
	@PostMapping("modMenu")
	@ApiOperation("修改菜单表")
	@ApiImplicitParams({})
	public ResultObject modMenu(MenuAD menuad) {
		try {
			Menu menu = EntityChangeRquestView.createDOToEntity(menuad, new Menu());
			return iMenuService.modMenu(menu);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "修改菜单表异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 删除菜单表 Auther:feng
	 */
	@PostMapping("delMenu")
	@ApiOperation("删除菜单表方法")
	@ApiImplicitParams({ @ApiImplicitParam(name = "menuIds", value = "主键集合数据", example = "1", required = false) })
	public ResultObject delMenu(String menuIds) {
		try {
		
			return iMenuService.delMenu(menuIds);
		} catch (Exception e) {
			logger.error(e + "删除菜单表方法异常 ");
			return ResultUtil.error("异常");
		}
	}
}
