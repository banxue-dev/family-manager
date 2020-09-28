package com.family.gold.service; 
import com.family.gold.entity.ChangePrice; 
import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo; 
import com.family.utils.LayuiPage; 
import com.family.utils.ResultObject; 
import com.family.gold.entity.VO.ChangePriceVO; 
import com.family.gold.entity.DO.ChangePriceDO; 
import java.util.List;
/** 
* ChangePrice服务接口层 
* Auther:feng
*/ 
public interface IChangePriceService  {  

	/** 
	* 获取单页记录 
	* Auther:feng
	*/ 
	public ChangePriceVO getSingleInfo(ChangePriceDO changePriceDO);
	/** 
	* 根据id获取数据 
	* Auther:feng
	*/ 
	ChangePriceVO getSingleInfoById(Integer changePriceId);
	/** 
	* 获取列表记录 
	* Auther:feng
	*/ 
	public List<ChangePriceVO> getChangePriceList(ChangePriceDO changePriceDO);
	/** 
	* 获取分页记录 
	* Auther:feng
	*/ 
	public LayuiPage<ChangePriceVO> getChangePriceListByPage(ChangePriceDO changePriceDO, LayuiPage<ChangePriceVO> layuiPage);
	/** 
	* 删除记录 
	* Auther:feng
	*/ 
	public ResultObject delChangePrice(ChangePrice changePrice); 
	/** 
	* 修改信息 
	* Auther:feng
	*/ 
	public ResultObject modChangePrice(ChangePrice changePrice);
	/** 
	* 添加信息 
	* Auther:feng
	*/ 
	public ResultObject addNewChangePrice(ChangePrice changePrice);
}
