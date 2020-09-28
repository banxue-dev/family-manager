package com.family.system.entity.VO; 

import java.util.List;

import com.family.system.entity.Menu;
import com.family.utils.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/** 
* Menu实体层 
* Auther:feng
* Date:2020-09-16 16:04:43
*/ 

@ApiModel("菜单前端展示表")
public class TreeMenuVO {
	private static final long serialVersionUID = 1L;
	

	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "标题")
	private String title;
	@ApiModelProperty(value = "id")
	private int id;
	@ApiModelProperty(value = "是否展开")
	private boolean spread=false;
	@ApiModelProperty(value = "是否展开")
	private boolean checked=false;

	@ApiModelProperty(value = "图标")
	private String icon;

	@ApiModelProperty(value = "上级id",example="1")
	private Integer parentId;

	@ApiModelProperty(value = "jump")
	private String jump;


	@ApiModelProperty(value = "子菜单列表",example="1")
	private List<TreeMenuVO> children;

	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isSpread() {
		return spread;
	}
	public void setSpread(boolean spread) {
		this.spread = spread;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<TreeMenuVO> getChildren() {
		return children;
	}
	public void setChildren(List<TreeMenuVO> children) {
		this.children = children;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getJump() {
		return jump;
	}
	public void setJump(String jump) {
		this.jump = jump;
	}
	
}

