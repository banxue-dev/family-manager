package com.family.gold.service.Impl; 
import com.family.gold.entity.UserDiyGroupConfig; 
import com.family.gold.mapper.UserDiyGroupConfigMapper; 
import com.family.gold.service.IUserDiyGroupConfigService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.gold.entity.VO.UserDiyGroupConfigVO; 
import com.family.gold.entity.DO.UserDiyGroupConfigDO; 
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
* UserDiyGroupConfig服务层 
* Auther:feng
* Date:2020-10-21 17:50:47
*/ 
@Service 
public class UserDiyGroupConfigServiceImpl implements IUserDiyGroupConfigService { 

	@Autowired
	private UserDiyGroupConfigMapper iUserDiyGroupConfigMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public UserDiyGroupConfigVO getSingleInfo(UserDiyGroupConfigDO userDiyGroupConfigDO) {
		UserDiyGroupConfig userDiyGroupConfig=new UserDiyGroupConfig();
		userDiyGroupConfig= iUserDiyGroupConfigMapper.selectOne(EntityChangeRquestView.createDOToEntity(userDiyGroupConfigDO,new UserDiyGroupConfig()));
		return this.structDetailData(userDiyGroupConfig);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public UserDiyGroupConfigVO getSingleInfoById(Integer goldUserDiyGroupConfigId) {
		UserDiyGroupConfig userDiyGroupConfig=new UserDiyGroupConfig();
		userDiyGroupConfig= iUserDiyGroupConfigMapper.selectByPrimaryKey(goldUserDiyGroupConfigId);
		return this.structDetailData(userDiyGroupConfig);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<UserDiyGroupConfigVO> getUserDiyGroupConfigList(UserDiyGroupConfigDO userDiyGroupConfigDO) {
		  Example example = getPublicExample(userDiyGroupConfigDO);
		  List<UserDiyGroupConfigVO> lstVO = new ArrayList<UserDiyGroupConfigVO>();
		  List<UserDiyGroupConfig> lst = iUserDiyGroupConfigMapper.selectByExample(example); 
		lst.forEach(t->{
		  UserDiyGroupConfigVO vo=this.structDetailData(t);
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
	public LayuiPage<UserDiyGroupConfigVO> getUserDiyGroupConfigListByPage(UserDiyGroupConfigDO userDiyGroupConfigDO, LayuiPage<UserDiyGroupConfigVO> layuiPage){
		  Example example = getPublicExample(userDiyGroupConfigDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<UserDiyGroupConfigVO> lstVO = new ArrayList<UserDiyGroupConfigVO>();
		  List<UserDiyGroupConfig> lst = iUserDiyGroupConfigMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  UserDiyGroupConfigVO vo=this.structDetailData(t);
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
	public ResultObject delUserDiyGroupConfig(UserDiyGroupConfig userDiyGroupConfig) {
		 int i= iUserDiyGroupConfigMapper.updateByPrimaryKeySelective(userDiyGroupConfig);
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
	public ResultObject modUserDiyGroupConfig(UserDiyGroupConfig userDiyGroupConfig) {
		int i= iUserDiyGroupConfigMapper.updateByPrimaryKeySelective(userDiyGroupConfig);
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
	public ResultObject addNewUserDiyGroupConfig(UserDiyGroupConfig userDiyGroupConfig) {
		userDiyGroupConfig.setCreateTime(TimeUtils.getCurrentTime());
		int i= iUserDiyGroupConfigMapper.insertSelective(userDiyGroupConfig);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private UserDiyGroupConfigVO structDetailData(UserDiyGroupConfig userDiyGroupConfig) { 
		 if(userDiyGroupConfig==null){ 
			 return null; 
		} 
		UserDiyGroupConfigVO vo= EntityChangeRquestView.createEntityToVO(userDiyGroupConfig,new UserDiyGroupConfigVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(UserDiyGroupConfigDO userDiyGroupConfigDO) { 
		Example example = new Example(UserDiyGroupConfig.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(userDiyGroupConfigDO,new UserDiyGroupConfig())); 
		return example; 
	}
}
