package com.family.gold.service.Impl;

import com.family.gold.entity.GoodsStorage;
import com.family.gold.mapper.GoodsStorageMapper;
import com.family.gold.service.IGoodsStorageService;
import org.springframework.stereotype.Service;
import com.family.utils.EntityChangeRquestView;
import com.family.gold.entity.VO.GoodsStorageVO;
import com.family.gold.entity.DO.GoodsStorageDO;
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
 * GoodsStorage服务层 Auther:feng Date:2020-09-24 09:35:33
 */
@Service
public class GoodsStorageServiceImpl implements IGoodsStorageService {

	@Autowired
	private GoodsStorageMapper iGoodsStorageMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public GoodsStorageVO getSingleInfo(GoodsStorageDO goodsStorageDO) {
		GoodsStorage goodsStorage = new GoodsStorage();
		goodsStorage = iGoodsStorageMapper
				.selectOne(EntityChangeRquestView.createDOToEntity(goodsStorageDO, new GoodsStorage()));
		return this.structDetailData(goodsStorage);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public GoodsStorageVO getSingleInfoById(Integer goodsStorageId) {
		GoodsStorage goodsStorage = new GoodsStorage();
		goodsStorage = iGoodsStorageMapper.selectByPrimaryKey(goodsStorageId);
		return this.structDetailData(goodsStorage);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<GoodsStorageVO> getGoodsStorageList(GoodsStorageDO goodsStorageDO) {
		Example example = getPublicExample(goodsStorageDO);
		List<GoodsStorageVO> lstVO = new ArrayList<GoodsStorageVO>();
		List<GoodsStorage> lst = iGoodsStorageMapper.selectByExample(example);
		lst.forEach(t -> {
			GoodsStorageVO vo = this.structDetailData(t);
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
	public LayuiPage<GoodsStorageVO> getGoodsStorageListByPage(GoodsStorageDO goodsStorageDO,
			LayuiPage<GoodsStorageVO> layuiPage) {
		Example example = getPublicExample(goodsStorageDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<GoodsStorageVO> lstVO = new ArrayList<GoodsStorageVO>();
		List<GoodsStorage> lst = iGoodsStorageMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		lst.forEach(t -> {
			GoodsStorageVO vo = this.structDetailData(t);
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
	public ResultObject delGoodsStorage(GoodsStorage goodsStorage) {
		int i = iGoodsStorageMapper.updateByPrimaryKeySelective(goodsStorage);
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
	public ResultObject modGoodsStorage(GoodsStorage goodsStorage) {
		int i = iGoodsStorageMapper.updateByPrimaryKeySelective(goodsStorage);
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
	public ResultObject addNewGoodsStorage(GoodsStorage goodsStorage) {
		goodsStorage.setCreateTime(TimeUtils.getCurrentTime());
		int i = iGoodsStorageMapper.insertSelective(goodsStorage);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private GoodsStorageVO structDetailData(GoodsStorage goodsStorage) {
		if (goodsStorage == null) {
			return null;
		}
		GoodsStorageVO vo = EntityChangeRquestView.createEntityToVO(goodsStorage, new GoodsStorageVO());
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(GoodsStorageDO goodsStorageDO) {
		Example example = new Example(GoodsStorage.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(goodsStorageDO, new GoodsStorage()));
		return example;
	}
}
