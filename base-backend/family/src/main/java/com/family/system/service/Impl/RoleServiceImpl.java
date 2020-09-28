package com.family.system.service.Impl;

import com.family.system.entity.Role;
import com.family.system.mapper.RoleMapper;
import com.family.system.service.IRoleService;
import org.springframework.stereotype.Service;
import com.family.utils.EntityChangeRquestView;
import com.family.system.entity.VO.RoleVO;
import com.family.system.entity.DO.RoleDO;
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
 * Role服务层 Auther:feng Date:2020-09-17 15:13:11
 */
@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper iRoleMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public RoleVO getSingleInfo(RoleDO roleDO) {
		Role role = new Role();
		role = iRoleMapper.selectOne(EntityChangeRquestView.createDOToEntity(roleDO, new Role()));
		return this.structDetailData(role);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public RoleVO getSingleInfoById(Integer roleId) {
		Role role = new Role();
		role = iRoleMapper.selectByPrimaryKey(roleId);
		return this.structDetailData(role);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<RoleVO> getRoleList(RoleDO roleDO) {
		Example example = getPublicExample(roleDO);
		List<RoleVO> lstVO = new ArrayList<RoleVO>();
		List<Role> lst = iRoleMapper.selectByExample(example);
		lst.forEach(t -> {
			RoleVO vo = this.structDetailData(t);
			if (vo != null) {
				lstVO.add(vo);
			}
		});
		return lstVO;
	}

	/**
	 * 获取分页记录 Auther:feng
	 */
	@Override
	@Transactional
	public LayuiPage<RoleVO> getRoleListByPage(RoleDO roleDO, LayuiPage<RoleVO> layuiPage) {
		Example example = getPublicExample(roleDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<RoleVO> lstVO = new ArrayList<RoleVO>();
		List<Role> lst = iRoleMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		lst.forEach(t -> {
			RoleVO vo = this.structDetailData(t);
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
	public ResultObject delRole(Role role) {
		int i = iRoleMapper.updateByPrimaryKeySelective(role);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 修改信息 Auther:feng
	 */
	@Override
	@Transactional
	public ResultObject modRole(Role role) {
		if(StringUtils.isNull(role.getRoleName())) {
			return ResultUtil.error("名称必填");
		}
		int i = iRoleMapper.updateByPrimaryKeySelective(role);
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
	public ResultObject addNewRole(Role role) {
		if(StringUtils.isNull(role.getRoleName())) {
			return ResultUtil.error("名称必填");
		}
		role.setCreateTime(TimeUtils.getCurrentTime());
		int i = iRoleMapper.insertSelective(role);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private RoleVO structDetailData(Role role) {
		if (role == null) {
			return null;
		}
		RoleVO vo = EntityChangeRquestView.createEntityToVO(role, new RoleVO());
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(RoleDO roleDO) {
		Example example = new Example(Role.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(roleDO, new Role()));
		return example;
	}
}
