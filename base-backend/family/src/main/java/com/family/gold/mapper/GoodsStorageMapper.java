package com.family.gold.mapper; 
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.family.gold.entity.GoodsStorage;

import tk.mybatis.mapper.common.Mapper; 
/** 
* GoodsStorage数据层 
* Auther:feng
* Date:2020-09-24 09:35:33
*/ 
@org.apache.ibatis.annotations.Mapper 
public interface GoodsStorageMapper extends Mapper<GoodsStorage> {  

	@Select("select goods_storage_id from gold_goods_storage where goods_name like '%${goodsName}%'")
	List<Integer> getGoodsIdByName(@Param("goodsName")String name);
}
