package com.family.normal.entity; 

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import com.family.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
/** 
* FileSystem实体层 
* Auther:feng
* Date:2021-06-01 10:15:41
*/ 

@Table(name = "normal_file_system")
@ApiModel("文件系统管理")
public class FileSystem {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name="normal_file_sys_id")
	@ApiModelProperty(value = "主键",example="1")
	private Integer normalFileSysId;

	@Column(name="file_name")
	@ApiModelProperty(value = "文件名")
	private String fileName;

	@Column(name="file_url")
	@ApiModelProperty(value = "文件访问地址")
	private String fileUrl;

	@Column(name="create_time")
	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@Column(name="file_source")
	@ApiModelProperty(value = "文件来源")
	private String fileSource;

	@Column(name="file_path")
	@ApiModelProperty(value = "文件物理路径")
	private String filePath;


	/**
	 *主键
	 */ 
	public void setNormalFileSysId(Integer normalFileSysId){
		this.normalFileSysId=normalFileSysId;
	}
	/**
	 *主键
	 */ 
	public Integer getNormalFileSysId(){
		return normalFileSysId;
	}
	/**
	 *文件名
	 */ 
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	/**
	 *文件名
	 */ 
	public String getFileName(){
		return fileName;
	}
	/**
	 *文件访问地址
	 */ 
	public void setFileUrl(String fileUrl){
		this.fileUrl=fileUrl;
	}
	/**
	 *文件访问地址
	 */ 
	public String getFileUrl(){
		return fileUrl;
	}
	/**
	 *创建时间
	 */ 
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}
	/**
	 *创建时间
	 */ 
	public String getCreateTime(){
		  if(StringUtils.isNotNull(createTime)) {if(createTime.contains(".0")) {return createTime.replace(".0", "");}}return createTime;
	}
	/**
	 *文件来源
	 */ 
	public void setFileSource(String fileSource){
		this.fileSource=fileSource;
	}
	/**
	 *文件来源
	 */ 
	public String getFileSource(){
		return fileSource;
	}
	/**
	 *文件物理路径
	 */ 
	public void setFilePath(String filePath){
		this.filePath=filePath;
	}
	/**
	 *文件物理路径
	 */ 
	public String getFilePath(){
		return filePath;
	}
}

