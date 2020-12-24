package com.family.wb.service.Impl;

import com.family.wb.entity.GroupConfig;
import com.family.wb.entity.GroupType;
import com.family.wb.mapper.GroupConfigMapper;
import com.family.wb.mapper.GroupTypeMapper;
import com.family.wb.service.IGroupConfigService;
import org.springframework.stereotype.Service;
import com.family.utils.EntityChangeRquestView;
import com.family.wb.entity.VO.GroupConfigVO;
import com.family.wb.entity.DO.GroupConfigDO;
import com.github.pagehelper.PageHelper;
import com.family.utils.ResultUtil;
import com.family.utils.ResultObject;
import javax.persistence.Transient;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.HashMap;

import com.family.utils.TimeUtils;
import com.github.pagehelper.PageInfo;
import com.family.utils.LayuiPage;
import com.family.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

/**
 * GroupConfig服务层 Auther:feng Date:2020-12-16 15:41:20
 */
@Service
public class GroupConfigServiceImpl implements IGroupConfigService {

	@Autowired
	private GroupConfigMapper iGroupConfigMapper;

	@Autowired
	private GroupTypeMapper iGroupTypeMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public GroupConfigVO getSingleInfo(GroupConfigDO groupConfigDO) {
		GroupConfig groupConfig = new GroupConfig();
		groupConfig = iGroupConfigMapper
				.selectOne(EntityChangeRquestView.createDOToEntity(groupConfigDO, new GroupConfig()));
		return this.structDetailData(groupConfig);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public GroupConfigVO getSingleInfoById(Integer caseGroupId) {
		GroupConfig groupConfig = new GroupConfig();
		groupConfig = iGroupConfigMapper.selectByPrimaryKey(caseGroupId);
		return this.structDetailData(groupConfig);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<GroupConfigVO> getGroupConfigList(GroupConfigDO groupConfigDO) {
		Example example = getPublicExample(groupConfigDO);
		List<GroupConfigVO> lstVO = new ArrayList<GroupConfigVO>();
		List<GroupConfig> lst = iGroupConfigMapper.selectByExample(example);
		lst.forEach(t -> {
			GroupConfigVO vo = this.structDetailData(t);
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
	public LayuiPage<GroupConfigVO> getGroupConfigListByPage(GroupConfigDO groupConfigDO,
			LayuiPage<GroupConfigVO> layuiPage) {
		Example example = getPublicExample(groupConfigDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<GroupType> gcs=iGroupTypeMapper.selectAll();
		Map<Integer,String> maps=new HashMap<Integer,String>();
		maps.put(0, "");
		if(gcs.size()>0) {
			maps=gcs.stream().collect(Collectors.toMap(GroupType::getTypeValue, GroupType::getTypeName));
		}
		List<GroupConfigVO> lstVO = new ArrayList<GroupConfigVO>();
		List<GroupConfig> lst = iGroupConfigMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		for(GroupConfig t:lst) {
			GroupConfigVO vo = this.structDetailData(t);
			if (vo != null) {
				vo.setGroupTypeName(maps.get(vo.getGroupType()));
				lstVO.add(vo);
			}
		};
		pageInfo.setList(lstVO);
		layuiPage = new LayuiPage<>(pageInfo);
		return layuiPage;
	}

	/**
	 * 删除记录 Auther:feng
	 */
	@Override
	@Transactional
	public ResultObject delGroupConfig(GroupConfig groupConfig) {
		int i = iGroupConfigMapper.updateByPrimaryKeySelective(groupConfig);
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
	public ResultObject modGroupConfig(GroupConfig groupConfig) {
		int i = iGroupConfigMapper.updateByPrimaryKeySelective(groupConfig);
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
	public ResultObject addNewGroupConfig(GroupConfig groupConfig) {
		int i = iGroupConfigMapper.insertSelective(groupConfig);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private GroupConfigVO structDetailData(GroupConfig groupConfig) {
		if (groupConfig == null) {
			return null;
		}
		GroupConfigVO vo = EntityChangeRquestView.createEntityToVO(groupConfig, new GroupConfigVO());
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(GroupConfigDO groupConfigDO) {
		Example example = new Example(GroupConfig.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(groupConfigDO, new GroupConfig()));
		return example;
	}
}
