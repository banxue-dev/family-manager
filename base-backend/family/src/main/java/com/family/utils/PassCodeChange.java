package com.family.utils;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 组织代码生成器
 * @author tWX932595
 *
 */
public class PassCodeChange {

	private static Logger logger=LoggerFactory.getLogger(PassCodeChange.class);
	private static String[] passa= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z" };
	private static String[] passb= {"G","H","I","o","p","J","D","E","F","s","t","u","v","w","x","y","z","X","A","Y","Z","a","b","c","d","e","f","g","h","i","j","k","K","L","M","N","O","P","Q","R","S","T","U","V","W","B","C","l","m","n","q","r" };
	private static String[] pass1= {"1","5","2","0","9","7","6","3","4","8"};
	private static String[] pass2= {"4","5","6","2","1","3","7","0","9","8"};
	private static List<String> passaArr=Arrays.asList(passa);
	private static List<String> pass1Arr=Arrays.asList(pass1);
	
	/**
	 * 加密
	 * @param source
	 * @return
	 */
	public static String encode(String source) {
		String res="";
		try {
			char[]  c = source.toCharArray();
			int d=0;
			String t="";
			for(int i=0;i < c.length;i++) {
				t=String.valueOf(c[i]);
				d=passaArr.indexOf(t);
				if(d<0) {
					d=pass1Arr.indexOf(t);
				}else {
					res+=passb[d];
					continue;
				}
				if(d>0) {
					res+=pass2[d];
					continue;
				}
				res+=t;
			}
		}catch(Exception e) {
			res="-1";
			logger.error("错误"+e);
		}
		return res;
	}
	
	public static void main(String[] args) {
		String ec=encode("123456");
		System.out.println(ec);
	}
	
}

