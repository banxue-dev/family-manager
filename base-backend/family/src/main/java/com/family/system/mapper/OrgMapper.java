package com.family.system.mapper; 
import org.apache.ibatis.annotations.Select;

import com.family.system.entity.Org;

import tk.mybatis.mapper.common.Mapper; 
/** 
* Org数据层 
* Auther:feng
* Date:2020-09-18 14:50:41
*/ 
@org.apache.ibatis.annotations.Mapper 
public interface OrgMapper extends Mapper<Org> {  

	@Select("select org_name from sys_org where org_code=#{param1}")
	String getOrgNameByCode(String code);
}
