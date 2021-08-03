package com.family.gold.mapper; 
import com.family.gold.entity.PersonalInfo;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper; 
/** 
* PersonalInfo数据层 
* Auther:feng
* Date:2020-09-28 15:37:24
*/ 
@org.apache.ibatis.annotations.Mapper 
public interface PersonalInfoMapper extends Mapper<PersonalInfo>,InsertListMapper<PersonalInfo> {  

}
