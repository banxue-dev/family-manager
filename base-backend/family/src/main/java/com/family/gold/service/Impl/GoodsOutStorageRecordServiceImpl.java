package com.family.gold.service.Impl;

import com.family.gold.entity.GoodsOutStorageRecord;
import com.family.gold.entity.GoodsStorage;
import com.family.gold.mapper.GoodsOutStorageRecordMapper;
import com.family.gold.mapper.GoodsStorageMapper;
import com.family.gold.service.IGoodsOutStorageRecordService;
import org.springframework.stereotype.Service;
import com.family.utils.EntityChangeRquestView;
import com.family.gold.entity.VO.GoodsOutStorageRecordVO;
import com.family.gold.entity.VO.GoodsStorageVO;
import com.family.gold.entity.DO.GoodsOutStorageRecordDO;
import com.github.pagehelper.PageHelper;
import com.family.utils.ResultUtil;
import com.family.utils.ResultObject;
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
 * GoodsOutStorageRecord服务层 Auther:feng Date:2020-09-24 09:35:32
 */
@Service
public class GoodsOutStorageRecordServiceImpl implements IGoodsOutStorageRecordService {

	@Autowired
	private GoodsOutStorageRecordMapper iGoodsOutStorageRecordMapper;

	@Autowired
	private GoodsStorageMapper goodsStorageMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public GoodsOutStorageRecordVO getSingleInfo(GoodsOutStorageRecordDO goodsOutStorageRecordDO) {
		GoodsOutStorageRecord goodsOutStorageRecord = new GoodsOutStorageRecord();
		goodsOutStorageRecord = iGoodsOutStorageRecordMapper.selectOne(
				EntityChangeRquestView.createDOToEntity(goodsOutStorageRecordDO, new GoodsOutStorageRecord()));
		return this.structDetailData(goodsOutStorageRecord);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public GoodsOutStorageRecordVO getSingleInfoById(Integer goodsOutStorageId) {
		GoodsOutStorageRecord goodsOutStorageRecord = new GoodsOutStorageRecord();
		goodsOutStorageRecord = iGoodsOutStorageRecordMapper.selectByPrimaryKey(goodsOutStorageId);
		return this.structDetailData(goodsOutStorageRecord);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<GoodsOutStorageRecordVO> getGoodsOutStorageRecordList(GoodsOutStorageRecordDO goodsOutStorageRecordDO) {
		Example example = getPublicExample(goodsOutStorageRecordDO);
		List<GoodsOutStorageRecordVO> lstVO = new ArrayList<GoodsOutStorageRecordVO>();
		List<GoodsOutStorageRecord> lst = iGoodsOutStorageRecordMapper.selectByExample(example);
		lst.forEach(t -> {
			GoodsOutStorageRecordVO vo = this.structDetailData(t);
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
	public LayuiPage<GoodsOutStorageRecordVO> getGoodsOutStorageRecordListByPage(
			GoodsOutStorageRecordDO goodsOutStorageRecordDO, LayuiPage<GoodsOutStorageRecordVO> layuiPage) {
		Example example = getPublicExample(goodsOutStorageRecordDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<GoodsOutStorageRecordVO> lstVO = new ArrayList<GoodsOutStorageRecordVO>();
		List<GoodsOutStorageRecord> lst = iGoodsOutStorageRecordMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		lst.forEach(t -> {
			GoodsOutStorageRecordVO vo = this.structDetailData(t);
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
	public ResultObject delGoodsOutStorageRecord(GoodsOutStorageRecord goodsOutStorageRecord) {
		int i = iGoodsOutStorageRecordMapper.updateByPrimaryKeySelective(goodsOutStorageRecord);
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
	public ResultObject modGoodsOutStorageRecord(GoodsOutStorageRecord goodsOutStorageRecord) {
		int i = iGoodsOutStorageRecordMapper.updateByPrimaryKeySelective(goodsOutStorageRecord);
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
	public ResultObject addNewGoodsOutStorageRecord(GoodsOutStorageRecord goodsOutStorageRecord) {
		GoodsStorage gs=goodsStorageMapper.selectByPrimaryKey(goodsOutStorageRecord.getGoodsStorageId());
		if(gs==null) {
			return ResultUtil.error("商品有误");
		}
		if(gs.getGoodsSurplusStorage()<goodsOutStorageRecord.getSaleCount()) {
			return ResultUtil.error("商品库存不足，无法完成出库，请补充库存");
		}
		GoodsStorage od =new GoodsStorage();
		od.setGoodsStorageId(gs.getGoodsStorageId());
		od.setGoodsSurplusStorage(gs.getGoodsSurplusStorage()-goodsOutStorageRecord.getSaleCount());
		goodsStorageMapper.updateByPrimaryKeySelective(od);
		goodsOutStorageRecord.setCreateTime(TimeUtils.getCurrentTime());
		int i = iGoodsOutStorageRecordMapper.insertSelective(goodsOutStorageRecord);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private GoodsOutStorageRecordVO structDetailData(GoodsOutStorageRecord goodsOutStorageRecord) {
		if (goodsOutStorageRecord == null) {
			return null;
		}
		GoodsOutStorageRecordVO vo = EntityChangeRquestView.createEntityToVO(goodsOutStorageRecord,
				new GoodsOutStorageRecordVO());
		GoodsStorage gs = goodsStorageMapper.selectByPrimaryKey(goodsOutStorageRecord.getGoodsStorageId());
		GoodsStorage t = new GoodsStorage();
		t.setGoodsName(gs.getGoodsName());
		vo.setGoods(EntityChangeRquestView.createEntityToVO(t, new GoodsStorageVO()));
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(GoodsOutStorageRecordDO goodsOutStorageRecordDO) {
		Example example = new Example(GoodsOutStorageRecord.class);
		Example.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotNull(goodsOutStorageRecordDO.getGoodsName())) {
			List<Integer> goodsIds=goodsStorageMapper.getGoodsIdByName(goodsOutStorageRecordDO.getGoodsName());
			if(goodsIds.size()<1) {
				goodsIds.add(-1);
			}
			criteria.andIn("goodsStorageId", goodsIds);
		}
		criteria.andEqualTo(
				EntityChangeRquestView.createDOToEntity(goodsOutStorageRecordDO, new GoodsOutStorageRecord()));
		return example;
	}
}
