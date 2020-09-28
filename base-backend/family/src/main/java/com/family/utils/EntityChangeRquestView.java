package com.family.utils;

import org.springframework.beans.BeanUtils;

/**
 * 作者：fengchase 时间：2019年9月24日 文件：EntityChangeRquestView.java 项目：shop-common
 */
public class EntityChangeRquestView {

	/**
	 * 从实体POJO转为显示POJO
	 * 
	 * @param E
	 *            实体POJO
	 * @param V
	 *            显示POJO  eg:new V();
	 * @return 2019年9月24日 作者：fengchase
	 */
	public static <E, V> V createEntityToVO(E e, V v) throws RuntimeException {
		try {
			if(e==null){
				return v;
			}
			BeanUtils.copyProperties(e, v);
			return v;
		} catch (Exception e1) {
			throw new RuntimeException("从实体POJO转为显示POJO时出现异常。"+e1);
		}
	}

	/**
	 * 从请求POJO参数转为实体POJO
	 * 
	 * @param D
	 *            请求POJO
	 * @param E
	 *            实体POJO eg:new E();
	 * @return 2019年9月24日 作者：fengchase
	 */
	public static <D, E> E createDOToEntity(D d, E e) throws RuntimeException {
		try {
			if(d==null){
				return e;
			}
			BeanUtils.copyProperties(d, e);
			return e;
		} catch (Exception e1) {
			throw new RuntimeException("从请求POJO参数转为实体POJO时出现异常。"+e1);
		}
	}
}
