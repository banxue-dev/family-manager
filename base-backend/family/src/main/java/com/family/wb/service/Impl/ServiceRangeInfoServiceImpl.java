package com.family.wb.service.Impl;

import com.family.wb.entity.GroupConfig;
import com.family.wb.entity.ServiceRangeInfo;
import com.family.wb.mapper.GroupConfigMapper;
import com.family.wb.mapper.ServiceRangeInfoMapper;
import com.family.wb.service.IServiceRangeInfoService;
import org.springframework.stereotype.Service;
import com.family.utils.EntityChangeRquestView;
import com.family.wb.entity.VO.ServiceRangeInfoVO;
import com.family.wb.entity.DO.ServiceRangeInfoDO;
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
 * ServiceRangeInfo服务层 Auther:feng Date:2020-12-16 15:41:24
 */
@Service
public class ServiceRangeInfoServiceImpl implements IServiceRangeInfoService {

	@Autowired
	private ServiceRangeInfoMapper iServiceRangeInfoMapper;
	@Autowired
	private GroupConfigMapper iGroupConfiMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public ServiceRangeInfoVO getSingleInfo(ServiceRangeInfoDO serviceRangeInfoDO) {
		ServiceRangeInfo serviceRangeInfo = new ServiceRangeInfo();
		serviceRangeInfo = iServiceRangeInfoMapper
				.selectOne(EntityChangeRquestView.createDOToEntity(serviceRangeInfoDO, new ServiceRangeInfo()));
		return this.structDetailData(serviceRangeInfo);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public ServiceRangeInfoVO getSingleInfoById(Integer serviceRangeId) {
		ServiceRangeInfo serviceRangeInfo = new ServiceRangeInfo();
		serviceRangeInfo = iServiceRangeInfoMapper.selectByPrimaryKey(serviceRangeId);
		return this.structDetailData(serviceRangeInfo);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<ServiceRangeInfoVO> getServiceRangeInfoList(ServiceRangeInfoDO serviceRangeInfoDO) {
		Example example = getPublicExample(serviceRangeInfoDO);
		List<ServiceRangeInfoVO> lstVO = new ArrayList<ServiceRangeInfoVO>();
		List<ServiceRangeInfo> lst = iServiceRangeInfoMapper.selectByExample(example);
		lst.forEach(t -> {
			ServiceRangeInfoVO vo = this.structDetailData(t);
			if (vo != null) {
				//vo.setRangeContent(vo.getRangeContent().substring(0,(vo.getRangeContent().length()>20?20:vo.getRangeContent().length())));
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
	public LayuiPage<ServiceRangeInfoVO> getServiceRangeInfoListByPage(ServiceRangeInfoDO serviceRangeInfoDO,
			LayuiPage<ServiceRangeInfoVO> layuiPage) {
		Example example = getPublicExample(serviceRangeInfoDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<ServiceRangeInfoVO> lstVO = new ArrayList<ServiceRangeInfoVO>();
		List<ServiceRangeInfo> lst = iServiceRangeInfoMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		GroupConfig gcp=new GroupConfig();
		gcp.setGroupType(3);
		List<GroupConfig> gcs=iGroupConfiMapper.select(gcp);
		Map<Integer,String> maps=new HashMap<Integer,String>();
		maps.put(0, "");
		if(gcs.size()>0) {
			maps=gcs.stream().collect(Collectors.toMap(GroupConfig::getGroupId, GroupConfig::getGroupName));
		}
		for(ServiceRangeInfo t:lst){
			ServiceRangeInfoVO vo = this.structDetailData(t);
			if (vo != null) {
				vo.setRangeGroupName(maps.get(vo.getRangeGroupId()));
//				vo.setRangeContent(vo.getRangeContent().substring(0,(vo.getRangeContent().length()>20?20:vo.getRangeContent().length())));
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
	public ResultObject delServiceRangeInfo(ServiceRangeInfo serviceRangeInfo) {
		int i = iServiceRangeInfoMapper.updateByPrimaryKeySelective(serviceRangeInfo);
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
	public ResultObject modServiceRangeInfo(ServiceRangeInfo serviceRangeInfo) {
		int i = iServiceRangeInfoMapper.updateByPrimaryKeySelective(serviceRangeInfo);
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
	public ResultObject addNewServiceRangeInfo(ServiceRangeInfo serviceRangeInfo) {
		serviceRangeInfo.setCreateTime(TimeUtils.getCurrentTime());
		int i = iServiceRangeInfoMapper.insertSelective(serviceRangeInfo);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private ServiceRangeInfoVO structDetailData(ServiceRangeInfo serviceRangeInfo) {
		if (serviceRangeInfo == null) {
			return null;
		}
		ServiceRangeInfoVO vo = EntityChangeRquestView.createEntityToVO(serviceRangeInfo, new ServiceRangeInfoVO());
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(ServiceRangeInfoDO serviceRangeInfoDO) {
		Example example = new Example(ServiceRangeInfo.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(serviceRangeInfoDO, new ServiceRangeInfo()));
		return example;
	}
}
