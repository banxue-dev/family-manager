package com.family.gold.service.Impl; 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.gold.entity.UserDiyGroupConfig;
import com.family.gold.entity.UserDiyMetalConfig;
import com.family.gold.entity.DO.UserDiyMetalConfigDO;
import com.family.gold.entity.VO.UserDiyMetalConfigVO;
import com.family.gold.mapper.UserDiyGroupConfigMapper;
import com.family.gold.mapper.UserDiyMetalConfigMapper;
import com.family.gold.service.IUserDiyMetalConfigService;
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
* UserDiyMetalConfig服务层 
* Auther:feng
* Date:2020-10-22 09:02:06
*/ 
@Service 
public class UserDiyMetalConfigServiceImpl implements IUserDiyMetalConfigService { 

	@Autowired
	private UserDiyMetalConfigMapper iUserDiyMetalConfigMapper;
	@Autowired
	private UserDiyGroupConfigMapper iUserDiyGroupConfigMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public UserDiyMetalConfigVO getSingleInfo(UserDiyMetalConfigDO userDiyMetalConfigDO) {
		UserDiyMetalConfig userDiyMetalConfig=new UserDiyMetalConfig();
		userDiyMetalConfig= iUserDiyMetalConfigMapper.selectOne(EntityChangeRquestView.createDOToEntity(userDiyMetalConfigDO,new UserDiyMetalConfig()));
		return this.structDetailData(userDiyMetalConfig);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public UserDiyMetalConfigVO getSingleInfoById(Long goldUserDiyMetalConfigId) {
		UserDiyMetalConfig userDiyMetalConfig=new UserDiyMetalConfig();
		userDiyMetalConfig= iUserDiyMetalConfigMapper.selectByPrimaryKey(goldUserDiyMetalConfigId);
		return this.structDetailData(userDiyMetalConfig);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<UserDiyMetalConfigVO> getUserDiyMetalConfigList(UserDiyMetalConfigDO userDiyMetalConfigDO) {
		  Example example = getPublicExample(userDiyMetalConfigDO);
		  List<UserDiyMetalConfigVO> lstVO = new ArrayList<UserDiyMetalConfigVO>();
		  List<UserDiyMetalConfig> lst = iUserDiyMetalConfigMapper.selectByExample(example); 
		lst.forEach(t->{
		  UserDiyMetalConfigVO vo=this.structDetailData(t);
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
	public LayuiPage<UserDiyMetalConfigVO> getUserDiyMetalConfigListByPage(UserDiyMetalConfigDO userDiyMetalConfigDO, LayuiPage<UserDiyMetalConfigVO> layuiPage){
		  Example example = getPublicExample(userDiyMetalConfigDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<UserDiyMetalConfigVO> lstVO = new ArrayList<UserDiyMetalConfigVO>();
		  List<UserDiyMetalConfig> lst = iUserDiyMetalConfigMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  UserDiyMetalConfigVO vo=this.structDetailData(t);
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
	public ResultObject delUserDiyMetalConfig(UserDiyMetalConfig userDiyMetalConfig) {
		 int i= iUserDiyMetalConfigMapper.updateByPrimaryKeySelective(userDiyMetalConfig);
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
	public ResultObject modUserDiyMetalConfig(UserDiyMetalConfig userDiyMetalConfig) {
		int i= iUserDiyMetalConfigMapper.updateByPrimaryKeySelective(userDiyMetalConfig);
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
	public ResultObject addNewUserDiyMetalConfig(UserDiyMetalConfig userDiyMetalConfig) {
		userDiyMetalConfig.setCreateTime(TimeUtils.getCurrentTime());
		int i= iUserDiyMetalConfigMapper.insertSelective(userDiyMetalConfig);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private UserDiyMetalConfigVO structDetailData(UserDiyMetalConfig userDiyMetalConfig) { 
		 if(userDiyMetalConfig==null){ 
			 return null; 
		} 
		UserDiyMetalConfigVO vo= EntityChangeRquestView.createEntityToVO(userDiyMetalConfig,new UserDiyMetalConfigVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(UserDiyMetalConfigDO userDiyMetalConfigDO) { 
		Example example = new Example(UserDiyMetalConfig.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(userDiyMetalConfigDO,new UserDiyMetalConfig())); 
		return example; 
	}
	@Override
	public List<UserDiyMetalConfigVO> getSingleInfoByOut(UserDiyMetalConfigDO userDiyMetalConfigDO) {
		// TODO Auto-generated method stub
		return iUserDiyMetalConfigMapper.getSingleInfoByOut(userDiyMetalConfigDO.getOrgCode());
	}
	/**
	 * 为指定的组织生成用户数据
	 */
	@Override
	public void createOrgUseDiyMetalData(String orgCode) {
		/**
		 * 先把default获取出来，再复制
		 */
		String defstr="default";
		UserDiyGroupConfig defGroup=new UserDiyGroupConfig();
		defGroup.setOrgCode(defstr);
		List<UserDiyGroupConfig> defgs=iUserDiyGroupConfigMapper.select(defGroup);
		for(UserDiyGroupConfig ng:defgs) {
			ng.setGoldUserDiyGroupConfigId(null);
			ng.setOrgCode(orgCode);
			iUserDiyGroupConfigMapper.insertSelective(ng);
		}
		UserDiyMetalConfig defParam=new UserDiyMetalConfig();
		defParam.setOrgCode(defstr);
		List<UserDiyMetalConfig> defs=iUserDiyMetalConfigMapper.select(defParam);
		for(UserDiyMetalConfig n:defs) {
			n.setGoldUserDiyMetalConfigId(null);
			n.setOrgCode(orgCode);
			n.setBuyBackWater(BigDecimal.ZERO);
			n.setSaleWater(BigDecimal.ZERO);
			iUserDiyMetalConfigMapper.insertSelective(n);
		}
	}
}
