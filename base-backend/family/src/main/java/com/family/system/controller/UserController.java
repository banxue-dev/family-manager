package com.family.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.system.entity.User;
import com.family.system.entity.UserRole;
import com.family.system.entity.DO.UserAD;
import com.family.system.entity.DO.UserDO;
import com.family.system.entity.DO.UserRoleAD;
import com.family.system.entity.VO.UserVO;
import com.family.system.mapper.UserMapper;
import com.family.system.mapper.UserRoleMapper;
import com.family.system.service.IUserRoleService;
import com.family.system.service.IUserService;
import com.family.utils.EntityChangeRquestView;
import com.family.utils.LayuiPage;
import com.family.utils.PassCodeChange;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;
import com.family.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 系统用户表控制器 Auther:feng Date:2020-09-17 15:13:13
 */
@RestController
@RequestMapping("user/v1.0")
@Api(tags = "系统用户表的接口")
public class UserController {
	@Autowired
	private IUserService iUserService;
	@Autowired
	private UserMapper iUserMapper;
	@Autowired
	private UserRoleMapper iUserRoleMapper;
	@Autowired
	private IUserRoleService iUserRoleService;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 依据ID获取系统用户表详情 Auther:feng
	 */
	@PostMapping("getUserSingleById")
	@ApiOperation("依据ID获取系统用户表详情")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "系统用户表的id", required = false, example = "1") })
	public ResultObject getUserSingleById(@RequestHeader("userId")Integer userId) {
		try {
			UserVO entity = new UserVO();
			entity = iUserService.getSingleInfoById(userId);
			return ResultUtil.successData(entity);
		} catch (Exception e) {
			logger.error(e + "依据ID获取系统用户表详情异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 获取系统用户表单条记录 Auther:feng
	 */
	@PostMapping("getUserSingle")
	@ApiOperation("获取系统用户表单条记录")
	@ApiImplicitParams({})
	public ResultObject getUserSingle(UserDO user) {
		try {
			UserVO userVO = iUserService.getSingleInfo(user);
			return ResultUtil.successData(userVO);
		} catch (Exception e) {
			logger.error(e + "获取系统用户表单条记录异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 获取系统用户表列表 Auther:feng
	 */
	@PostMapping("getUserList")
	@ApiOperation("获取系统用户表列表")
	@ApiImplicitParams({})
	public ResultObject getUserList(UserDO user) {
		try {
			List<UserVO> lst = iUserService.getUserList(user);
			return ResultUtil.successData(lst);
		} catch (Exception e) {
			logger.error(e + "获取系统用户表列表记录异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 获取系统用户表分页数据 Auther:feng
	 */
	@PostMapping("getUserListByPage")
	@ApiOperation("获取系统用户表分页数据")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, example = "1"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小", required = true, example = "1"),
			@ApiImplicitParam(name = "sort", value = "排序依据字段", required = false, example = "1"),
			@ApiImplicitParam(name = "dire", value = "倒序还是正序  asc | desc", required = false, example = "1") })
	public LayuiPage getUserListByPage(UserDO user, LayuiPage<UserVO> layuiPage) {
		try {
			return iUserService.getUserListByPage(user, layuiPage);
		} catch (Exception e) {
			logger.error(e + "获取系统用户表分页记录异常");
			return layuiPage;
		}
	}

	/**
	 * 添加系统用户表方法 Auther:feng
	 */
	@PostMapping("addUser")
	@ApiOperation("添加系统用户表")
	@ApiImplicitParams({})
	public ResultObject addUser(UserAD userad) {
		try {
			User user = EntityChangeRquestView.createDOToEntity(userad, new User());
			return iUserService.addNewUser(user);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "添加系统用户表异常");
			return ResultUtil.error("异常");
		}
	}
	/**
	 * 添加用户角色表方法 Auther:feng
	 */
	@PostMapping("saveUserTransferRole")
	@ApiOperation("倒在用户角色的变更")
	@ApiImplicitParams({})
	public ResultObject saveUserTransferRole(UserRoleAD userRolead, Integer doUserId) {
		try {
			if(doUserId==null || doUserId<1) {
				return ResultUtil.error("请求例外");
			}
			userRolead.setUserId(doUserId);
			return iUserRoleService.saveUserTransferRole(userRolead);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "添加用户角色表异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 修改系统用户表方法 Auther:feng
	 */
	@PostMapping("modUser")
	@ApiOperation("修改系统用户表")
	@ApiImplicitParams({})
	public ResultObject modUser(UserAD userad) {
		try {
			User user = EntityChangeRquestView.createDOToEntity(userad, new User());
			return iUserService.modUser(user);
			// return ResultUtil.success("成功");
		} catch (Exception e) {
			logger.error(e + "修改系统用户表异常");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 删除系统用户表 Auther:feng
	 */
	@PostMapping("delUser")
	@ApiOperation("删除系统用户方法")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userIds", value = "主键id数据", example = "1", required = false) })
	public ResultObject delUser(String userIds) {
		try {
			if (StringUtils.isNull(userIds)) {
				return ResultUtil.error("错误的参数");
			}
			String[] userstr = userIds.split(",");
			for (String str : userstr) {
				iUserMapper.deleteByPrimaryKey(str);
				UserRole ur = new UserRole();
				// 清理用户拥有的这个角色
				ur.setUserId(Integer.parseInt(str));
				iUserRoleMapper.delete(ur);
			}

			return ResultUtil.success();
		} catch (Exception e) {
			logger.error(e + "删除系统用户表方法异常 ");
			return ResultUtil.error("异常");
		}
	}

	/**
	 * 重置系统用户表 Auther:feng
	 */
	@PostMapping("resetPwd")
	@ApiOperation("重置系统用户密码方法")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userIds", value = "主键id数据", example = "1", required = false) })
	public ResultObject resetPwd(String userIds) {
		try {
			if (StringUtils.isNull(userIds)) {

				return ResultUtil.error("错误的参数");
			}
			String[] userstr = userIds.split(",");
			for (String str : userstr) {
				User u = new User();
				u.setUserId(Integer.parseInt(str));
				u.setUserPwd(PassCodeChange.encode("123456"));
				iUserMapper.updateByPrimaryKeySelective(u);
			}

			return ResultUtil.success();
		} catch (Exception e) {
			logger.error(e + "删除系统用户表方法异常 ");
			return ResultUtil.error("异常");
		}
	}
}
