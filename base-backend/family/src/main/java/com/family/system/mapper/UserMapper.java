package com.family.system.mapper; 
import org.apache.ibatis.annotations.Select;

import com.family.system.entity.User; 
import tk.mybatis.mapper.common.Mapper; 
/** 
* User数据层 
* Auther:feng
* Date:2020-09-17 15:13:13
*/ 
@org.apache.ibatis.annotations.Mapper 
public interface UserMapper extends Mapper<User> {  

}
