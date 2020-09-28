package com.family.gold.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.gold.entity.GoodsInStorageRecord;
import com.family.gold.entity.GoodsStorage;
import com.family.gold.entity.DO.GoodsInStorageRecordDO;
import com.family.gold.entity.VO.GoodsInStorageRecordVO;
import com.family.gold.entity.VO.GoodsStorageVO;
import com.family.gold.mapper.GoodsInStorageRecordMapper;
import com.family.gold.mapper.GoodsStorageMapper;
import com.family.gold.service.IGoodsInStorageRecordService;
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
 * GoodsInStorageRecord服务层 Auther:feng Date:2020-09-24 09:35:31
 */
@Service
public class GoodsInStorageRecordServiceImpl implements IGoodsInStorageRecordService {

	@Autowired
	private GoodsInStorageRecordMapper iGoodsInStorageRecordMapper;

	@Autowired
	private GoodsStorageMapper goodsStorageMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public GoodsInStorageRecordVO getSingleInfo(GoodsInStorageRecordDO goodsInStorageRecordDO) {
		GoodsInStorageRecord goodsInStorageRecord = new GoodsInStorageRecord();
		goodsInStorageRecord = iGoodsInStorageRecordMapper
				.selectOne(EntityChangeRquestView.createDOToEntity(goodsInStorageRecordDO, new GoodsInStorageRecord()));
		return this.structDetailData(goodsInStorageRecord);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public GoodsInStorageRecordVO getSingleInfoById(Integer goodsInStorageId) {
		GoodsInStorageRecord goodsInStorageRecord = new GoodsInStorageRecord();
		goodsInStorageRecord = iGoodsInStorageRecordMapper.selectByPrimaryKey(goodsInStorageId);
		return this.structDetailData(goodsInStorageRecord);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<GoodsInStorageRecordVO> getGoodsInStorageRecordList(GoodsInStorageRecordDO goodsInStorageRecordDO) {
		Example example = getPublicExample(goodsInStorageRecordDO);
		List<GoodsInStorageRecordVO> lstVO = new ArrayList<GoodsInStorageRecordVO>();
		List<GoodsInStorageRecord> lst = iGoodsInStorageRecordMapper.selectByExample(example);
		lst.forEach(t -> {
			GoodsInStorageRecordVO vo = this.structDetailData(t);
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
	public LayuiPage<GoodsInStorageRecordVO> getGoodsInStorageRecordListByPage(
			GoodsInStorageRecordDO goodsInStorageRecordDO, LayuiPage<GoodsInStorageRecordVO> layuiPage) {
		Example example = getPublicExample(goodsInStorageRecordDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<GoodsInStorageRecordVO> lstVO = new ArrayList<GoodsInStorageRecordVO>();
		List<GoodsInStorageRecord> lst = iGoodsInStorageRecordMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		lst.forEach(t -> {
			GoodsInStorageRecordVO vo = this.structDetailData(t);
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
	public ResultObject delGoodsInStorageRecord(GoodsInStorageRecord goodsInStorageRecord) {
		int i = iGoodsInStorageRecordMapper.updateByPrimaryKeySelective(goodsInStorageRecord);
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
	public ResultObject modGoodsInStorageRecord(GoodsInStorageRecord goodsInStorageRecord) {
		int i = iGoodsInStorageRecordMapper.updateByPrimaryKeySelective(goodsInStorageRecord);
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
	public ResultObject addNewGoodsInStorageRecord(GoodsInStorageRecord goodsInStorageRecord) {
		GoodsStorage gs=goodsStorageMapper.selectByPrimaryKey(goodsInStorageRecord.getGoodsStorageId());
		if(gs==null) {
			return ResultUtil.error("商品有误");
		}
		GoodsStorage od =new GoodsStorage();
		od.setGoodsStorageId(gs.getGoodsStorageId());
		od.setGoodsSurplusStorage(gs.getGoodsSurplusStorage()+goodsInStorageRecord.getBuyCount());
		goodsStorageMapper.updateByPrimaryKeySelective(od);
		goodsInStorageRecord.setCreateTime(TimeUtils.getCurrentTime());
		int i = iGoodsInStorageRecordMapper.insertSelective(goodsInStorageRecord);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private GoodsInStorageRecordVO structDetailData(GoodsInStorageRecord goodsInStorageRecord) {
		if (goodsInStorageRecord == null) {
			return null;
		}
		
		GoodsInStorageRecordVO vo = EntityChangeRquestView.createEntityToVO(goodsInStorageRecord,
				new GoodsInStorageRecordVO());

		GoodsStorage gs=goodsStorageMapper.selectByPrimaryKey(goodsInStorageRecord.getGoodsStorageId());
		GoodsStorage t=new GoodsStorage();
		t.setGoodsName(gs.getGoodsName());
		vo.setGoods(EntityChangeRquestView.createEntityToVO(t, new GoodsStorageVO()));
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(GoodsInStorageRecordDO goodsInStorageRecordDO) {
		Example example = new Example(GoodsInStorageRecord.class);
		Example.Criteria criteria = example.createCriteria();
		if(StringUtils.isNotNull(goodsInStorageRecordDO.getGoodsName())) {
			List<Integer> goodsIds=goodsStorageMapper.getGoodsIdByName(goodsInStorageRecordDO.getGoodsName());
			if(goodsIds.size()<1) {
				goodsIds.add(-1);
			}
			criteria.andIn("goodsStorageId", goodsIds);
		}
		criteria.andEqualTo(
				EntityChangeRquestView.createDOToEntity(goodsInStorageRecordDO, new GoodsInStorageRecord()));
		return example;
	}
}
