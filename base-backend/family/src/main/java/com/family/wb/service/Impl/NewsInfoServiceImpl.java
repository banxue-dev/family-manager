package com.family.wb.service.Impl;

import com.family.wb.entity.NewsInfo;
import com.family.wb.entity.GroupConfig;
import com.family.wb.mapper.GroupConfigMapper;
import com.family.wb.mapper.NewsInfoMapper;
import com.family.wb.service.INewsInfoService;
import org.springframework.stereotype.Service;
import com.family.utils.EntityChangeRquestView;
import com.family.wb.entity.VO.NewsInfoVO;
import com.family.wb.entity.DO.NewsInfoDO;
import com.github.pagehelper.PageHelper;
import com.family.utils.ResultUtil;
import com.family.utils.ResultObject;
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
 * NewsInfo服务层 Auther:feng Date:2020-12-16 15:41:23
 */
@Service
public class NewsInfoServiceImpl implements INewsInfoService {

	@Autowired
	private NewsInfoMapper iNewsInfoMapper;
	@Autowired
	private GroupConfigMapper iGroupConfiMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public NewsInfoVO getSingleInfo(NewsInfoDO newsInfoDO) {
		NewsInfo newsInfo = new NewsInfo();
		newsInfo = iNewsInfoMapper.selectOne(EntityChangeRquestView.createDOToEntity(newsInfoDO, new NewsInfo()));
		return this.structDetailData(newsInfo);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public NewsInfoVO getSingleInfoById(Integer newsId) {
		NewsInfo newsInfo = new NewsInfo();
		newsInfo = iNewsInfoMapper.selectByPrimaryKey(newsId);
		return this.structDetailData(newsInfo);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<NewsInfoVO> getNewsInfoList(NewsInfoDO newsInfoDO) {
		Example example = getPublicExample(newsInfoDO);
		List<NewsInfoVO> lstVO = new ArrayList<NewsInfoVO>();
		List<NewsInfo> lst = iNewsInfoMapper.selectByExample(example);
		lst.forEach(t -> {
			NewsInfoVO vo = this.structDetailData(t);
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
	public LayuiPage<NewsInfoVO> getNewsInfoListByPage(NewsInfoDO newsInfoDO, LayuiPage<NewsInfoVO> layuiPage) {
		Example example = getPublicExample(newsInfoDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<NewsInfoVO> lstVO = new ArrayList<NewsInfoVO>();
		List<NewsInfo> lst = iNewsInfoMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		GroupConfig gcp=new GroupConfig();
		gcp.setGroupType(0);
		List<GroupConfig> gcs=iGroupConfiMapper.select(gcp);
		Map<Integer,String> maps=new HashMap<Integer,String>();
		maps.put(0, "");
		if(gcs.size()>0) {
			maps=gcs.stream().collect(Collectors.toMap(GroupConfig::getGroupId, GroupConfig::getGroupName));
		}
		for(NewsInfo t:lst) {
			NewsInfoVO vo = this.structDetailData(t);
			if (vo != null) {
				vo.setNewsGroupName(maps.get(vo.getNewsGroupId()));
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
	public ResultObject delNewsInfo(NewsInfo newsInfo) {
		int i = iNewsInfoMapper.updateByPrimaryKeySelective(newsInfo);
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
	public ResultObject modNewsInfo(NewsInfo newsInfo) {
		int i = iNewsInfoMapper.updateByPrimaryKeySelective(newsInfo);
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
	public ResultObject addNewNewsInfo(NewsInfo newsInfo) {
		if (newsInfo.getNewsGroupId() == null) {
			return ResultUtil.error("分组信息未选择");
		}
		newsInfo.setCreateTime(TimeUtils.getCurrentTime());
		int i = iNewsInfoMapper.insertSelective(newsInfo);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private NewsInfoVO structDetailData(NewsInfo newsInfo) {
		if (newsInfo == null) {
			return null;
		}
		NewsInfoVO vo = EntityChangeRquestView.createEntityToVO(newsInfo, new NewsInfoVO());
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(NewsInfoDO newsInfoDO) {
		Example example = new Example(NewsInfo.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(newsInfoDO, new NewsInfo()));
		return example;
	}

	@Override
	public List<NewsInfoVO> getRandomNewsInfoList(int count) {
		// TODO Auto-generated method stub
		List<NewsInfo> lst=iNewsInfoMapper.getRandomNewsInfoList(count);

		List<NewsInfoVO> lstVO = new ArrayList<NewsInfoVO>();
		lst.forEach(t -> {
			NewsInfoVO vo = this.structDetailData(t);
			if (vo != null) {
				lstVO.add(vo);
			}
		});
		return lstVO;
	}
}
