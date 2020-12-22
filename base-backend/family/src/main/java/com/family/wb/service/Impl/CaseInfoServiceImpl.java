package com.family.wb.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.utils.EntityChangeRquestView;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;
import com.family.utils.StringUtils;
import com.family.utils.TimeUtils;
import com.family.wb.entity.CaseInfo;
import com.family.wb.entity.GroupConfig;
import com.family.wb.entity.DO.CaseInfoDO;
import com.family.wb.entity.VO.CaseInfoVO;
import com.family.wb.mapper.CaseInfoMapper;
import com.family.wb.mapper.GroupConfigMapper;
import com.family.wb.service.ICaseInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * CaseInfo服务层 Auther:feng Date:2020-12-16 15:41:18
 */
@Service
public class CaseInfoServiceImpl implements ICaseInfoService {

	@Autowired
	private CaseInfoMapper iCaseInfoMapper;
	@Autowired
	private GroupConfigMapper iGroupConfiMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public CaseInfoVO getSingleInfo(CaseInfoDO caseInfoDO) {
		CaseInfo caseInfo = new CaseInfo();
		caseInfo = iCaseInfoMapper.selectOne(EntityChangeRquestView.createDOToEntity(caseInfoDO, new CaseInfo()));
		return this.structDetailData(caseInfo);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public CaseInfoVO getSingleInfoById(Integer caseId) {
		CaseInfo caseInfo = new CaseInfo();
		caseInfo = iCaseInfoMapper.selectByPrimaryKey(caseId);
		return this.structDetailData(caseInfo);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<CaseInfoVO> getCaseInfoList(CaseInfoDO caseInfoDO) {
		Example example = getPublicExample(caseInfoDO);
		List<CaseInfoVO> lstVO = new ArrayList<CaseInfoVO>();
		List<CaseInfo> lst = iCaseInfoMapper.selectByExample(example);
		lst.forEach(t -> {
			CaseInfoVO vo = this.structDetailData(t);
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
	public LayuiPage<CaseInfoVO> getCaseInfoListByPage(CaseInfoDO caseInfoDO, LayuiPage<CaseInfoVO> layuiPage) {
		Example example = getPublicExample(caseInfoDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<CaseInfoVO> lstVO = new ArrayList<CaseInfoVO>();
		List<CaseInfo> lst = iCaseInfoMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		GroupConfig gcp=new GroupConfig();
		gcp.setGroupType(0);
		List<GroupConfig> gcs=iGroupConfiMapper.select(gcp);
		Map<Integer,String> maps=new HashMap<Integer,String>();
		maps.put(0, "");
		if(gcs.size()>0) {
			maps=gcs.stream().collect(Collectors.toMap(GroupConfig::getGroupId, GroupConfig::getGroupName));
		}
		for(CaseInfo t:lst) {
			CaseInfoVO vo = this.structDetailData(t);
			if (vo != null) {
				vo.setCaseGroupName(maps.get(vo.getCaseGroupId()));
				lstVO.add(vo);
			}
		}
		pageInfo.setList(lstVO);
		layuiPage = new LayuiPage<>(pageInfo);
		return layuiPage;
	}

	/**
	 * 删除记录 Auther:feng
	 */
	@Override
	@Transactional
	public ResultObject delCaseInfo(CaseInfo caseInfo) {
		int i = iCaseInfoMapper.updateByPrimaryKeySelective(caseInfo);
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
	public ResultObject modCaseInfo(CaseInfo caseInfo) {
		int i = iCaseInfoMapper.updateByPrimaryKeySelective(caseInfo);
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
	public ResultObject addNewCaseInfo(CaseInfo caseInfo) {
		caseInfo.setCreateTime(TimeUtils.getCurrentTime());
		int i = iCaseInfoMapper.insertSelective(caseInfo);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private CaseInfoVO structDetailData(CaseInfo caseInfo) {
		if (caseInfo == null) {
			return null;
		}
		CaseInfoVO vo = EntityChangeRquestView.createEntityToVO(caseInfo, new CaseInfoVO());

		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(CaseInfoDO caseInfoDO) {
		Example example = new Example(CaseInfo.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(caseInfoDO, new CaseInfo()));
		return example;
	}

	@Override
	public List<CaseInfoVO> getRandomCaseInfoList(Integer count) {
		// TODO Auto-generated method stub
		List<CaseInfo> lst=iCaseInfoMapper.getRandomCaseInfoList(count);

		List<CaseInfoVO> lstVO = new ArrayList<CaseInfoVO>();
		lst.forEach(t -> {
			CaseInfoVO vo = this.structDetailData(t);
			if (vo != null) {
				lstVO.add(vo);
			}
		});
		return lstVO;
	}
}
