package com.family.system.service.Impl; 
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.system.entity.Role;
import com.family.system.entity.UserRole;
import com.family.system.entity.DO.UserRoleAD;
import com.family.system.entity.DO.UserRoleDO;
import com.family.system.entity.VO.TransferUserRoleVO;
import com.family.system.entity.VO.UserRoleVO;
import com.family.system.mapper.RoleMapper;
import com.family.system.mapper.UserRoleMapper;
import com.family.system.service.IUserRoleService;
import com.family.utils.EntityChangeRquestView;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;
import com.family.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example; 
/** 
* UserRole服务层 
* Auther:feng
* Date:2020-09-17 15:13:14
*/ 
@Service 
public class UserRoleServiceImpl implements IUserRoleService { 

	@Autowired
	private UserRoleMapper iUserRoleMapper;
	@Autowired
	private RoleMapper iRoleMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public UserRoleVO getSingleInfo(UserRoleDO userRoleDO) {
		UserRole userRole=new UserRole();
		userRole= iUserRoleMapper.selectOne(EntityChangeRquestView.createDOToEntity(userRoleDO,new UserRole()));
		return this.structDetailData(userRole);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public UserRoleVO getSingleInfoById(Integer userRoleId) {
		UserRole userRole=new UserRole();
		userRole= iUserRoleMapper.selectByPrimaryKey(userRoleId);
		return this.structDetailData(userRole);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<UserRoleVO> getUserRoleList(UserRoleDO userRoleDO) {
		  Example example = getPublicExample(userRoleDO);
		  List<UserRoleVO> lstVO = new ArrayList<UserRoleVO>();
		  List<UserRole> lst = iUserRoleMapper.selectByExample(example); 
		lst.forEach(t->{
		  UserRoleVO vo=this.structDetailData(t);
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
	public LayuiPage<UserRoleVO> getUserRoleListByPage(UserRoleDO userRoleDO, LayuiPage<UserRoleVO> layuiPage){
		  Example example = getPublicExample(userRoleDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<UserRoleVO> lstVO = new ArrayList<UserRoleVO>();
		  List<UserRole> lst = iUserRoleMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  UserRoleVO vo=this.structDetailData(t);
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
	public ResultObject delUserRole(UserRole userRole) {
		 int i= iUserRoleMapper.updateByPrimaryKeySelective(userRole);
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
	public ResultObject modUserRole(UserRole userRole) {
		int i= iUserRoleMapper.updateByPrimaryKeySelective(userRole);
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
	public ResultObject addNewUserRole(UserRole userRole) {
		int i= iUserRoleMapper.insertSelective(userRole);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private UserRoleVO structDetailData(UserRole userRole) { 
		 if(userRole==null){ 
			 return null; 
		} 
		UserRoleVO vo= EntityChangeRquestView.createEntityToVO(userRole,new UserRoleVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(UserRoleDO userRoleDO) { 
		Example example = new Example(UserRole.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(userRoleDO,new UserRole())); 
		return example; 
	}
	@Override
	public TransferUserRoleVO getTransferRole(UserRoleDO userRoleDO) {
		// TODO Auto-generated method stub
		TransferUserRoleVO res=new TransferUserRoleVO();
		if(userRoleDO.getUserId()==null || userRoleDO.getUserId()<1) {
			return res;
		}
		List<Role> all=iRoleMapper.selectAll();
		if(all.size()<1) {
			return res;
		}
		res.setRoleList(all);
		List<UserRole> have=iUserRoleMapper.select(EntityChangeRquestView.createDOToEntity(userRoleDO,new UserRole()));
		if(have.size()>0) {
			List<Integer> haveIds=have.stream().map(t->t.getRoleId()).collect(Collectors.toList());
//			res.setHaveValue(StringUtils.ListToString(haveIds));
			res.setHaveValue(haveIds);
		}
		return res;
	}
	@Override
	public ResultObject saveUserTransferRole(UserRoleAD userRole) {
		// TODO Auto-generated method stub
		if(userRole.getUserId()==null) {
			return ResultUtil.error("操作出现例外");
		}
		if(StringUtils.isNull(userRole.getCatchRoleIds())) {
			return ResultUtil.error("到少有一个权限");
		}
		UserRole del=new UserRole();
		del.setUserId(userRole.getUserId());
		iUserRoleMapper.delete(del);
		for(String str:userRole.getCatchRoleIds().split(",")) {
			UserRole ur=new UserRole();
			ur.setUserId(userRole.getUserId());
			ur.setRoleId(Integer.parseInt(str));
			iUserRoleMapper.insert(ur);
		}
		return ResultUtil.success();
	}
}
