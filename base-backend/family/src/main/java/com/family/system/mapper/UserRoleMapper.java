package com.family.system.mapper; 
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.family.system.entity.UserRole;

import tk.mybatis.mapper.common.Mapper; 
/** 
* UserRole数据层 
* Auther:feng
* Date:2020-09-17 15:13:14
*/ 
@org.apache.ibatis.annotations.Mapper 
public interface UserRoleMapper extends Mapper<UserRole> {  

	@Select("select role_id from sys_user_role where user_id=#{param1}")
	List<Integer> getUserRoleByUserId(Integer userId);
}
