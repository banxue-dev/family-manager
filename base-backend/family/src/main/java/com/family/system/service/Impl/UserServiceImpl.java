package com.family.system.service.Impl;

import com.family.system.entity.User;
import com.family.system.mapper.UserMapper;
import com.family.system.service.IUserService;
import org.springframework.stereotype.Service;
import com.family.utils.EntityChangeRquestView;
import com.family.system.entity.VO.UserVO;
import com.family.system.entity.DO.UserDO;
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
import com.family.utils.OrgCodeGreater;
import com.family.utils.PassCodeChange;
import com.family.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.List;

/**
 * User服务层 Auther:feng Date:2020-09-17 15:13:13
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper iUserMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public UserVO getSingleInfo(UserDO userDO) {
		User user = new User();
		user = iUserMapper.selectOne(EntityChangeRquestView.createDOToEntity(userDO, new User()));
		return this.structDetailData(user);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public UserVO getSingleInfoById(Integer userId) {
		User user = new User();
		user = iUserMapper.selectByPrimaryKey(userId);
		return this.structDetailData(user);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<UserVO> getUserList(UserDO userDO) {
		Example example = getPublicExample(userDO);
		List<UserVO> lstVO = new ArrayList<UserVO>();
		List<User> lst = iUserMapper.selectByExample(example);
		lst.forEach(t -> {
			UserVO vo = this.structDetailData(t);
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
	public LayuiPage<UserVO> getUserListByPage(UserDO userDO, LayuiPage<UserVO> layuiPage) {
		Example example = getPublicExample(userDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<UserVO> lstVO = new ArrayList<UserVO>();
		List<User> lst = iUserMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		lst.forEach(t -> {
			UserVO vo = this.structDetailData(t);
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
	public ResultObject delUser(User user) {
		int i = iUserMapper.updateByPrimaryKeySelective(user);
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
	public ResultObject modUser(User user) {
		user.setUserPwd(PassCodeChange.encode(user.getUserPwd()));
		user.setOrgCode(OrgCodeGreater.decode(user.getOrgCode()));
		int i = iUserMapper.updateByPrimaryKeySelective(user);
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
	public ResultObject addNewUser(User user) {
		if(StringUtils.isNull( user.getOrgCode())) {
			return ResultUtil.error("用户没有组织");
		}
		if(StringUtils.isNull(user.getUserPwd())) {
			return ResultUtil.error("密码不能为空");
		}
		user.setUserPwd(PassCodeChange.encode(user.getUserPwd()));
		user.setCreateTime(TimeUtils.getCurrentTime());
		user.setOrgCode(OrgCodeGreater.decode(user.getOrgCode()));
		int i = iUserMapper.insertSelective(user);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private UserVO structDetailData(User user) {
		if (user == null) {
			return null;
		}
		UserVO vo = EntityChangeRquestView.createEntityToVO(user, new UserVO());
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(UserDO userDO) {
		Example example = new Example(User.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(userDO, new User()));
		return example;
	}

	@Override
	public UserVO login(User user) {
		// TODO Auto-generated method stub
		User u = iUserMapper.selectOne(user);
		if (u != null) {
			return EntityChangeRquestView.createEntityToVO(u, new UserVO());
		}
		return null;
	}
}
