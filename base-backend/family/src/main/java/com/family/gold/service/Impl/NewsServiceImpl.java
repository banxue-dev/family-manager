package com.family.gold.service.Impl; 
import com.family.gold.entity.News; 
import com.family.gold.mapper.NewsMapper; 
import com.family.gold.service.INewsService; 
import org.springframework.stereotype.Service; 
import com.family.utils.EntityChangeRquestView; 
import com.family.gold.entity.VO.NewsVO; 
import com.family.gold.entity.DO.NewsDO; 
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
* News服务层 
* Auther:feng
* Date:2020-09-22 17:59:24
*/ 
@Service 
public class NewsServiceImpl implements INewsService { 

	@Autowired
	private NewsMapper iNewsMapper;

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public NewsVO getSingleInfo(NewsDO newsDO) {
		News news=new News();
		news= iNewsMapper.selectOne(EntityChangeRquestView.createDOToEntity(newsDO,new News()));
		return this.structDetailData(news);
	}
	/** 
	* 依据ID获取单页记录 
	* Auther:feng
	*/ 
	@Override
	public NewsVO getSingleInfoById(Integer newsId) {
		News news=new News();
		news= iNewsMapper.selectByPrimaryKey(newsId);
		return this.structDetailData(news);
	}
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	@Override
	public List<NewsVO> getNewsList(NewsDO newsDO) {
		  Example example = getPublicExample(newsDO);
		  List<NewsVO> lstVO = new ArrayList<NewsVO>();
		  List<News> lst = iNewsMapper.selectByExample(example); 
		lst.forEach(t->{
		  NewsVO vo=this.structDetailData(t);
		  if(vo!=null) {
		  	lstVO.add(vo);
		  }
		});
	return lstVO;
	} 
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public LayuiPage<NewsVO> getNewsListByPage(NewsDO newsDO, LayuiPage<NewsVO> layuiPage){
		  Example example = getPublicExample(newsDO);
		 if(StringUtils.isNotNull(layuiPage.getSort())) {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit()).setOrderBy(layuiPage.getSort() + " " + layuiPage.getDire());		 }else {		 	PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());		 }		  List<NewsVO> lstVO = new ArrayList<NewsVO>();
		  List<News> lst = iNewsMapper.selectByExample(example); 
		PageInfo pageInfo=PageInfo.of(lst);
		  lst.forEach(t->{
		  NewsVO vo=this.structDetailData(t);
		  if(vo!=null) {
		  	lstVO.add(vo);
		  }
		});
		pageInfo.setList(lstVO);
		layuiPage = new LayuiPage<>(pageInfo);
		  return layuiPage; 
	}
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public ResultObject delNews(News news) {
		 int i= iNewsMapper.updateByPrimaryKeySelective(news);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public ResultObject modNews(News news) {
		int i= iNewsMapper.updateByPrimaryKeySelective(news);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	@Override
	@Transactional
	public ResultObject addNewNews(News news) {
		news.setCreateTime(TimeUtils.getCurrentTime());
		int i= iNewsMapper.insertSelective(news);
		if(i<1) {
			return ResultUtil.error("更新失败");
		}
		return ResultUtil.success("成功");
	}
	/** 
	* 构造返回的数据 
	* Auther:feng
	*/ 
	private NewsVO structDetailData(News news) { 
		 if(news==null){ 
			 return null; 
		} 
		NewsVO vo= EntityChangeRquestView.createEntityToVO(news,new NewsVO()); 
		return vo; 
	}
	/** 
	* 构造请求的条件 
	* Auther:feng
	*/ 
	private Example getPublicExample(NewsDO newsDO) { 
		Example example = new Example(News.class); 
		Example.Criteria criteria = example.createCriteria(); 
		criteria.andEqualTo(EntityChangeRquestView.createDOToEntity(newsDO,new News())); 
		return example; 
	}
}
