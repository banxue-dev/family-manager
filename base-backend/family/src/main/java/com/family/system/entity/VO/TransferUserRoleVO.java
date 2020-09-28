package com.family.system.entity.VO;

import java.util.List;

import com.family.system.entity.Role;

/**
 * 传递给前端的用户角色赋权对象
 * @author tWX932595
 *
 */
public class TransferUserRoleVO {

	private List<Role> roleList;
	private List<Integer> haveValue;
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public List<Integer> getHaveValue() {
		return haveValue;
	}
	public void setHaveValue(List<Integer> haveValue) {
		this.haveValue = haveValue;
	}

	
	
	
}
