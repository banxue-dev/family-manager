package com.family.wb.mapper; 
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.family.wb.entity.CaseInfo;

import tk.mybatis.mapper.common.Mapper; 
/** 
* CaseInfo数据层 
* Auther:feng
* Date:2020-12-16 15:41:18
*/ 
@org.apache.ibatis.annotations.Mapper 
public interface CaseInfoMapper extends Mapper<CaseInfo> {  

	@Select("select * from wb_case_info order by rand() limit #{param1}")
	List<CaseInfo> getRandomCaseInfoList(Integer count);
	@Select("select * from wb_case_info where case_id<#{param1} order by case_id desc limit 1")
	CaseInfo getPrevDataById(Integer count);
	@Select("select * from wb_case_info where case_id>#{param1} order by case_id  limit 1")
	CaseInfo getNextDataById(Integer count);
}
