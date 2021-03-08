package com.family.mall.service.Impl;

import com.family.mall.entity.GoodsInfo;
import com.family.mall.mapper.GoodsInfoMapper;
import com.family.mall.service.IGoodsInfoService;
import org.springframework.stereotype.Service;
import com.family.utils.EntityChangeRquestView;
import com.family.mall.entity.VO.GoodsInfoVO;
import com.family.mall.entity.DO.GoodsInfoDO;
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
 * GoodsInfo服务层 Auther:feng Date:2021-03-08 17:10:28
 */
@Service
public class GoodsInfoServiceImpl implements IGoodsInfoService {

	@Autowired
	private GoodsInfoMapper iGoodsInfoMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public GoodsInfoVO getSingleInfo(GoodsInfoDO goodsInfoDO) {
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo = iGoodsInfoMapper.selectOne(EntityChangeRquestView.createDOToEntity(goodsInfoDO, new GoodsInfo()));
		return this.structDetailData(goodsInfo);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public GoodsInfoVO getSingleInfoById(Long goodsId) {
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo = iGoodsInfoMapper.selectByPrimaryKey(goodsId);
		return this.structDetailData(goodsInfo);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<GoodsInfoVO> getGoodsInfoList(GoodsInfoDO goodsInfoDO) {
		Example example = getPublicExample(goodsInfoDO);
		List<GoodsInfoVO> lstVO = new ArrayList<GoodsInfoVO>();
		List<GoodsInfo> lst = iGoodsInfoMapper.selectByExample(example);
		lst.forEach(t -> {
			GoodsInfoVO vo = this.structDetailData(t);
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
	public LayuiPage<GoodsInfoVO> getGoodsInfoListByPage(GoodsInfoDO goodsInfoDO, LayuiPage<GoodsInfoVO> layuiPage) {
		Example example = getPublicExample(goodsInfoDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<GoodsInfoVO> lstVO = new ArrayList<GoodsInfoVO>();
		List<GoodsInfo> lst = iGoodsInfoMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		lst.forEach(t -> {
			GoodsInfoVO vo = this.structDetailData(t);
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
	public ResultObject delGoodsInfo(GoodsInfo goodsInfo) {
		int i = iGoodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
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
	public ResultObject modGoodsInfo(GoodsInfo goodsInfo) {
		int i = iGoodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
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
	public ResultObject addNewGoodsInfo(GoodsInfo goodsInfo) {
		goodsInfo.setCreateTime(TimeUtils.getCurrentTime());
		int i = iGoodsInfoMapper.insertSelective(goodsInfo);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private GoodsInfoVO structDetailData(GoodsInfo goodsInfo) {
		if (goodsInfo == null) {
			return null;
		}
		GoodsInfoVO vo = EntityChangeRquestView.createEntityToVO(goodsInfo, new GoodsInfoVO());
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(GoodsInfoDO goodsInfoDO) {
		Example example = new Example(GoodsInfo.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(goodsInfoDO, new GoodsInfo()));
		return example;
	}
}
