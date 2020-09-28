package com.family.gold.service.Impl;

import com.family.gold.entity.ChangePrice;
import com.family.gold.mapper.ChangePriceMapper;
import com.family.gold.service.IChangePriceService;
import com.family.system.mapper.OrgMapper;

import org.springframework.stereotype.Service;
import com.family.utils.EntityChangeRquestView;
import com.family.gold.entity.VO.ChangePriceVO;
import com.family.gold.entity.DO.ChangePriceDO;
import com.github.pagehelper.PageHelper;
import com.family.utils.ResultUtil;
import com.family.utils.ResultObject;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import com.github.pagehelper.PageInfo;
import com.family.utils.LayuiPage;
import com.family.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.List;

/**
 * ChangePrice服务层 Auther:feng Date:2020-09-21 15:07:56
 */
@Service
public class ChangePriceServiceImpl implements IChangePriceService {

	@Autowired
	private ChangePriceMapper iChangePriceMapper;
	@Autowired
	private OrgMapper iOrgMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public ChangePriceVO getSingleInfo(ChangePriceDO changePriceDO) {
		ChangePrice changePrice = new ChangePrice();
		changePrice = iChangePriceMapper
				.selectOne(EntityChangeRquestView.createDOToEntity(changePriceDO, new ChangePrice()));
		return this.structDetailData(changePrice);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public ChangePriceVO getSingleInfoById(Integer changePriceId) {
		ChangePrice changePrice = new ChangePrice();
		changePrice = iChangePriceMapper.selectByPrimaryKey(changePriceId);
		return this.structDetailData(changePrice);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<ChangePriceVO> getChangePriceList(ChangePriceDO changePriceDO) {
		Example example = getPublicExample(changePriceDO);
		List<ChangePriceVO> lstVO = new ArrayList<ChangePriceVO>();
		List<ChangePrice> lst = iChangePriceMapper.selectByExample(example);
		lst.forEach(t -> {
			ChangePriceVO vo = this.structDetailData(t);
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
	public LayuiPage<ChangePriceVO> getChangePriceListByPage(ChangePriceDO changePriceDO,
			LayuiPage<ChangePriceVO> layuiPage) {
		Example example = getPublicExample(changePriceDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<ChangePriceVO> lstVO = new ArrayList<ChangePriceVO>();
		List<ChangePrice> lst = iChangePriceMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		lst.forEach(t -> {
			ChangePriceVO vo = this.structDetailData(t);
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
	public ResultObject delChangePrice(ChangePrice changePrice) {
		int i = iChangePriceMapper.updateByPrimaryKeySelective(changePrice);
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
	public ResultObject modChangePrice(ChangePrice changePrice) {
		ChangePrice old = new ChangePrice();
		String decode = changePrice.getOrgCode();
		if (decode != null && decode.contentEquals("-1")) {
			return ResultUtil.error("非法操作");
		}
		old.setOrgCode(changePrice.getOrgCode());
		int oldi = iChangePriceMapper.selectCount(old);
		if (oldi < 1) {
			iChangePriceMapper.insertSelective(changePrice);
			return ResultUtil.success("成功");
		}
		Example example = new Example(ChangePrice.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("orgCode", changePrice.getOrgCode());
		changePrice.setOrgCode(null);
		int i = iChangePriceMapper.updateByExampleSelective(changePrice, example);
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
	public ResultObject addNewChangePrice(ChangePrice changePrice) {
		int i = iChangePriceMapper.insertSelective(changePrice);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private ChangePriceVO structDetailData(ChangePrice changePrice) {
		if (changePrice == null) {
			return null;
		}
		ChangePriceVO vo = EntityChangeRquestView.createEntityToVO(changePrice, new ChangePriceVO());
		vo.setOrgCodeName(iOrgMapper.getOrgNameByCode(changePrice.getOrgCode()));
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(ChangePriceDO changePriceDO) {
		Example example = new Example(ChangePrice.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(changePriceDO, new ChangePrice()));
		return example;
	}
}
