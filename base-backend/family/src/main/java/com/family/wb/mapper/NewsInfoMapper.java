package com.family.wb.mapper; 
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.family.wb.entity.NewsInfo;

import tk.mybatis.mapper.common.Mapper; 
/** 
* NewsInfo数据层 
* Auther:feng
* Date:2020-12-16 15:41:23
*/ 
@org.apache.ibatis.annotations.Mapper 
public interface NewsInfoMapper extends Mapper<NewsInfo> {  


	@Select("select * from wb_news_info order by rand() limit #{param1}")
	List<NewsInfo> getRandomNewsInfoList(int count);
	@Select("select * from wb_news_info where news_id<#{param1} order by news_id desc limit 1")
	NewsInfo getPrevDataById(Integer count);
	@Select("select * from wb_news_info where news_id>#{param1} order by news_id  limit 1")
	NewsInfo getNextDataById(Integer count);
}
