package com.family.utils;

public class ResultUtil {

	public  static final String  Result_Code_LoginOut="100001";
	public  static final String  Result_Code_No_Authority="100002";
	public  static final String  Result_Code_No_OrgCode="100003";
	public static ResultObject  success(String msg) {
		ResultObject ro=new ResultObject();
		ro.setCode("000000");
		ro.setMsg(msg);
		return ro;
	}
	public static ResultObject  success(Object data) {
		ResultObject ro=ResultUtil.success();
		ro.setData(data);
		return ro;
	}
	public static ResultObject  success() {
		ResultObject ro=new ResultObject();
		ro.setCode("000000");
		ro.setMsg("成功");
		return ro;
	}
	public static ResultObject  error(String msg) {
		ResultObject ro=new ResultObject();
		ro.setCode("111111");
		ro.setMsg(msg);
		return ro;
	}
	public static ResultObject  error(String code,String msg) {
		ResultObject ro=new ResultObject();
		ro.setCode(code);
		ro.setMsg(msg);
		return ro;
	}
	public static ResultObject  successData(Object data) {
		ResultObject ro=new ResultObject();
		ro.setCode("000000");
		ro.setMsg("成功");
		ro.setData(data);
		return ro;
	}
}
