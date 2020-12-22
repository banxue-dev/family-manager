package com.family.wb.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.family.utils.EntityChangeRquestView;
import com.family.utils.LayuiPage;
import com.family.utils.ResultObject;
import com.family.utils.ResultUtil;
import com.family.utils.StringUtils;
import com.family.wb.entity.AboutOurInfo;
import com.family.wb.entity.DO.AboutOurInfoDO;
import com.family.wb.entity.VO.AboutOurInfoVO;
import com.family.wb.mapper.AboutOurInfoMapper;
import com.family.wb.service.IAboutOurInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * AboutOurInfo服务层 Auther:feng Date:2020-12-17 10:29:59
 */
@Service
public class AboutOurInfoServiceImpl implements IAboutOurInfoService {

	@Autowired
	private AboutOurInfoMapper iAboutOurInfoMapper;

	/**
	 * 获取单页记录 Auther:feng
	 */
	@Override
	public AboutOurInfoVO getSingleInfo(AboutOurInfoDO aboutOurInfoDO) {
		AboutOurInfo aboutOurInfo = new AboutOurInfo();
		aboutOurInfo = iAboutOurInfoMapper
				.selectOne(EntityChangeRquestView.createDOToEntity(aboutOurInfoDO, new AboutOurInfo()));
		return this.structDetailData(aboutOurInfo);
	}

	/**
	 * 依据ID获取单页记录 Auther:feng
	 */
	@Override
	public AboutOurInfoVO getSingleInfoById(Integer aboutOurId) {
		AboutOurInfo aboutOurInfo = new AboutOurInfo();
		aboutOurInfo = iAboutOurInfoMapper.selectByPrimaryKey(aboutOurId);
		return this.structDetailData(aboutOurInfo);
	}

	/**
	 * 获取列表记录 Auther:feng
	 */
	@Override
	public List<AboutOurInfoVO> getAboutOurInfoList(AboutOurInfoDO aboutOurInfoDO) {
		Example example = getPublicExample(aboutOurInfoDO);
		List<AboutOurInfoVO> lstVO = new ArrayList<AboutOurInfoVO>();
		List<AboutOurInfo> lst = iAboutOurInfoMapper.selectByExample(example);
		lst.forEach(t -> {
			AboutOurInfoVO vo = this.structDetailData(t);
			if (vo != null) {
				//vo.setOurIntroduce(vo.getOurIntroduce().substring(0,(vo.getOurIntroduce().length()>20?20:vo.getOurIntroduce().length())));
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
	public LayuiPage<AboutOurInfoVO> getAboutOurInfoListByPage(AboutOurInfoDO aboutOurInfoDO,
			LayuiPage<AboutOurInfoVO> layuiPage) {
		Example example = getPublicExample(aboutOurInfoDO);
		if (StringUtils.isNotNull(layuiPage.getSort())) {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit())
					.setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());
		} else {
			PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
		}
		List<AboutOurInfoVO> lstVO = new ArrayList<AboutOurInfoVO>();
		List<AboutOurInfo> lst = iAboutOurInfoMapper.selectByExample(example);
		PageInfo pageInfo = PageInfo.of(lst);
		lst.forEach(t -> {
			AboutOurInfoVO vo = this.structDetailData(t);
			if (vo != null) {
				//vo.setOurIntroduce(vo.getOurIntroduce().substring(0,(vo.getOurIntroduce().length()>20?20:vo.getOurIntroduce().length())));
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
	public ResultObject delAboutOurInfo(AboutOurInfo aboutOurInfo) {
		int i = iAboutOurInfoMapper.updateByPrimaryKeySelective(aboutOurInfo);
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
	public ResultObject modAboutOurInfo(AboutOurInfo aboutOurInfo) {
		int i = iAboutOurInfoMapper.updateByPrimaryKeySelective(aboutOurInfo);
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
	public ResultObject addNewAboutOurInfo(AboutOurInfo aboutOurInfo) {
		AboutOurInfo isHave=new AboutOurInfo();
		isHave.setOrgCode(aboutOurInfo.getOrgCode());
		isHave= iAboutOurInfoMapper.selectOne(isHave);
		if(isHave!=null) {
			aboutOurInfo.setAboutOurId(isHave.getAboutOurId());
			return this.modAboutOurInfo(aboutOurInfo);
		}
		int i = iAboutOurInfoMapper.insertSelective(aboutOurInfo);
		if (i < 1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}

	/**
	 * 构造返回的数据 Auther:feng
	 */
	private AboutOurInfoVO structDetailData(AboutOurInfo aboutOurInfo) {
		if (aboutOurInfo == null) {
			return null;
		}
		AboutOurInfoVO vo = EntityChangeRquestView.createEntityToVO(aboutOurInfo, new AboutOurInfoVO());
		return vo;
	}

	/**
	 * 构造请求的条件 Auther:feng
	 */
	private Example getPublicExample(AboutOurInfoDO aboutOurInfoDO) {
		Example example = new Example(AboutOurInfo.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(aboutOurInfoDO, new AboutOurInfo()));
		return example;
	}
}
