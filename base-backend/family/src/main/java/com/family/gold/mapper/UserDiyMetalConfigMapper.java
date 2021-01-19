package com.family.gold.mapper; 
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.family.gold.entity.UserDiyMetalConfig;
import com.family.gold.entity.VO.UserDiyMetalConfigVO;

import tk.mybatis.mapper.common.Mapper; 
/** 
* UserDiyMetalConfig数据层 
* Auther:feng
* Date:2020-10-22 09:02:06
*/ 
@org.apache.ibatis.annotations.Mapper 
public interface UserDiyMetalConfigMapper extends Mapper<UserDiyMetalConfig> {  

	@Select("select m.gold_user_diy_metal_config_id,m.buy_back_water,m.sale_water,m.source_meta_id,s.metal_name,s.metal_code,m.up_down_rate,m.new_name,m.constraint_len,m.group_id,m.pc_group_id from gold_user_diy_metal_config m left join gold_source_metal_config s on s.metal_id=m.source_meta_id where m.org_code=#{param1} order by m.sort asc")
	List<UserDiyMetalConfigVO> getSingleInfoByOut(String orgCode);
}
