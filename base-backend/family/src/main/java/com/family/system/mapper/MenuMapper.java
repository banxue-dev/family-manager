package com.family.system.mapper; 
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.family.system.entity.Menu;

import tk.mybatis.mapper.common.Mapper; 
/** 
* Menu数据层 
* Auther:feng
* Date:2020-09-16 16:04:43
*/ 
@org.apache.ibatis.annotations.Mapper 
public interface MenuMapper extends Mapper<Menu> {  

	/**
	 * 查询用户是否有指定权限
	 * @param uid
	 * @param str
	 * @return
	 */
	@Select("select count(*) from sys_user_role ur where ur.role_id in ( select rm.role_id from sys_role_menu rm where rm.menu_id in (   select m.menu_id from sys_menu m where m.menu_name=#{param2})) and ur.user_id=#{param1}")
	int getUserIsHaveMenuByUserId(Integer uid,String str);
	@Select("select menu_name from sys_menu sm where sm.menu_id in (select rm.menu_id from sys_role_menu rm where rm.role_id in (select r.role_id from sys_user_role r where r.user_id=#{param1}))")
	List<String> getUserHaveAuth(Integer userId);
}
