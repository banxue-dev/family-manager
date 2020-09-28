package com.family.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * 包括空字符串和"null" 及null
	 *
	 * @param value
	 * @return 2019年8月29日 作者：fengchase
	 */
	public static boolean isNull(String value) {
		if (value == null || "".equals(value) || "null".equals(value)) {
			return true;
		}
		return false;
	}
	/**
	 * 包括空字符串和"null" 及null
	 *
	 * @param value
	 * @return 2019年8月29日 作者：fengchase
	 */
	public static boolean isNotNull(String value) {
		if (isNull(value)) {
			return false;
		}
		return true;
	}

	/**
	 * 验证是否为空，只要这些有一个为空，就会返回true
	 * @param objs
	 * @return
	 * 2019年9月25日
	 * 作者：fengchase
	 */
	public static boolean isNulls(Object ... objs) {
		for(Object obj:objs) {
			if(obj==null) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 验证是否为空，只要这些有一个为空，就会返回true
	 * @param objs
	 * @return
	 * 2019年9月25日
	 * 作者：fengchase
	 */
	public static boolean isNullStrings(String ... objs) {
		for(String obj:objs) {
			if(isNull(obj)) {
				return true;
			}
		}
		return false;
	}
	public static String getNull(String value, String defalut_value) {
		if (isNull(value)) {
			return defalut_value;
		}
		return value;
	}

	private static Pattern linePattern = Pattern.compile("_(\\w)");

	/** 下划线转驼峰 */
	public static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	private static Pattern humpPattern = Pattern.compile("[A-Z]");

	/** 驼峰转下划线,效率比上面高 */
	public static String humpToLine2(String str) {
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 构造like语句sql
	 * 就是增加一个%号的包围
	 * @param souce
	 * @return
	 * 2019年10月14日
	 * 作者：fengchase
	 */
	public static String structLikeSql(String souce) {
		return "%"+souce+"%";
	}
	public static void main(String[] args) {
		System.out.println(getNull(null + "", "2112"));
	}

	/**
	 * 将符号分隔的字符串转为list 符号包含：逗号、#号
	 * @param string
	 * @return
	 * 2019年9月3日
	 * 作者：fengchase
	 */
	public static <T> List<T> StringsToList(String string){
		if(isNull(string)) {
			return null;
		}
		string=string.replace("#", ",");
		String[] strs=string.split(",");
		List<T> lst=new ArrayList<T>();
		for(String s:strs) {
			lst.add((T) s);
		}
		return lst;
	}
	/**
	 * 将集合转为逗号分隔的字符串，只能是int等基本类型的集合
	 * @param string
	 * @return
	 * 2019年9月3日
	 * 作者：fengchase
	 */
	public static <T> String ListToString(List<T> lst){
		String res="";
		if(lst==null || lst.size()<1) {
			return res;
		}
		for(int i=0;i<lst.size();i++) {
			res+=lst.get(i);
			if(i<lst.size()-1) {
				res+=",";
			}
		}
		return res;
	}
	/**
	 * 是否是json字符串
	 * @param jsonstr
	 * @return
	 * 2019年10月23日
	 * fengchaseyou
	 */
	public static boolean isJsonStr(String jsonstr) {
		if(isNullStrings(jsonstr)) {
			return false;
		}
		if(jsonstr.startsWith("[") || jsonstr.startsWith("{")) {
			return true;
		}
		return false;
	}
	/**
	 * 去掉字符串最后一个符号
	 * @param str 目标字符串
	 * @param rep 符号 eg:逗号、#号
	 * 2019年10月15日
	 * 作者：fengchase
	 */
	public static String ClearLastComma(String str,String rep) {
		if(isNullStrings(str,rep)) {
			return null;
		}
		//如果没有对应的符号，就不截取
		if(str.lastIndexOf(rep) < 0){
			return str;
		}
		return str.substring(0,str.lastIndexOf(rep));
	}

	/**
	 * 两个字段是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static Boolean matchTwoStrEq(String str1,String str2) {
		if(str1==null && str2==null) {
			return true;
		}
		if(str1.equals(str2)) {
			return true;
		}
		return false;
	}
}
