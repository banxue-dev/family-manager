package com.family.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.system.entity.UserRole;
import com.family.system.entity.DO.UserRoleAD;
import com.family.system.entity.DO.UserRoleDO;
import com.family.system.entity.VO.TransferUserRoleVO;
import com.family.system.entity.VO.UserRoleVO;
import com.family.system.mapper.UserRoleMapper;
import com.family.system.service.IUserRoleService;
import com.family.utils.EntityChangeRquestView;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户角色表控制器 Auther:feng Date:2020-09-17 15:13:14
 */
@RestController
@RequestMapping("userRole/v1.0")
@Api(tags = "用户角色表的接口")
public class UserRoleController {
	@Autowired
	private IUserRoleService iUserRoleService;
	@Autowired
	private UserRoleMapper iUserRoleMapper;

	Logger logger = LoggerFactory.getLogger(UserRoleController.class);

	/**
	 * 依据ID获取用户角色表详情 Auther:feng
	 */
	@PostMapping("getUserRoleSingleById")
	@ApiOperation("依据ID获取用户角色表详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userRoleId", value = "用户角色表的id", required = false, example = "1") })
	public ResultObject getUserRoleSingleById(Integer userRoleId) {
		try {
			UserRoleVO entity = new UserRoleVO();
			entity = iUserRoleService.getSingleInfoById(userRoleId);
			return ResultUtil.successData(entity);
		} catch (Exception e) {
			logger.error(e + "依据ID获取用户角色表详情异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 获取用户角色表单条记录 Auther:feng
	 */
	@PostMapping("getUserRoleSingle")
	@ApiOperation("获取用户角色表单条记录")
	@ApiImplicitParams({})
	public ResultObject getUserRoleSingle(UserRoleDO userRole) {
		try {
			UserRoleVO userRoleVO = iUserRoleService.getSingleInfo(userRole);
			return ResultUtil.successData(userRoleVO);
		} catch (Exception e) {
			logger.error(e + "获取用户角色表单条记录异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 获取用户角色表列表 Auther:feng
	 */
	@PostMapping("getUserRoleList")
	@ApiOperation("获取用户角色表列表")
	@ApiImplicitParams({})
	public ResultObject getUserRoleList(UserRoleDO userRole) {
		try {
			List<UserRoleVO> lst = iUserRoleService.getUserRoleList(userRole);
			return ResultUtil.successData(lst);
		} catch (Exception e) {
			logger.error(e + "获取用户角色表列表记录异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 获取用户角色表列表 Auther:feng
	 */
	@PostMapping("getUserTransferRole")
	@ApiOperation("获取所有角色，并返回用户目前拥有的")
	@ApiImplicitParams({})
	public ResultObject getTransferRole(UserRoleDO userRole,  Integer doUserId) {
		try {
			userRole.setUserId(doUserId);
			TransferUserRoleVO data = iUserRoleService.getTransferRole(userRole);
			return ResultUtil.successData(data);
		} catch (Exception e) {
			logger.error(e + "获取用户角色表列表记录异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 获取用户角色表分页数据 Auther:feng
	 */
	@PostMapping("getUserRoleListByPage")
	@ApiOperation("获取用户角色表分页数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, example = "1"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, example = "1"),
			@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false, example = "1"),
			@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false, example = "1") })
	public LayuiPage getUserRoleListByPage(UserRoleDO userRole, LayuiPage<UserRoleVO> layuiPage) {
		try {
			return iUserRoleService.getUserRoleListByPage(userRole, layuiPage);
		} catch (Exception e) {
			logger.error(e + "获取用户角色表分页记录异常");
			return layuiPage;
		}
	}

	/**
	 * 添加用户角色表方法 Auther:feng
	 */
	@PostMapping("addUserRole")
	@ApiOperation("添加用户角色表")
	@ApiImplicitParams({})
	public ResultObject addUserRole(UserRoleAD userRolead) {
		try {
			UserRole userRole = EntityChangeRquestView.createDOToEntity(userRolead, new UserRole());
			return iUserRoleService.addNewUserRole(userRole);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "添加用户角色表异常");
			return ResultUtil.error("异常");
		}
	}

	

	/**
	 * 修改用户角色表方法 Auther:feng
	 */
	@PostMapping("modUserRole")
	@ApiOperation("修改用户角色表")
	@ApiImplicitParams({})
	public ResultObject modUserRole(UserRoleAD userRolead) {
		try {
			UserRole userRole = EntityChangeRquestView.createDOToEntity(userRolead, new UserRole());
			return iUserRoleService.modUserRole(userRole);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "修改用户角色表异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 删除用户角色表 Auther:feng
	 */
	@PostMapping("delUserRole")
	@ApiOperation("删除用户角色表方法")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userRoleId", value = "主键id数据", example = "1", required = false) })
	public ResultObject delUserRole(Integer userRoleId) {
		try {
			UserRole userRole = new UserRole();
			userRole.setUserRoleId(userRoleId);
			return iUserRoleService.delUserRole(userRole);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "删除用户角色表方法异常 ");
			return ResultUtil.error("异常");
		}
	}
}
