package com.family.utils;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Layui分页实体类
 *
 * @param <T>
 */
public class LayuiPage<T> {
    private final static int INIT_SIZE = 10; //初始化size
    private int limit = INIT_SIZE;//每页显示记录的条数
    private int page;//当前页面
    private int count;//总条数
    private int start;//开始索引
    private int end;//结束索引
    private String sort;
    private String dire="desc";
    private int totalPage;//页面总数
    private String unit = "条";//单位
    private List<T> data;//数据

    public LayuiPage() {
    }

    public LayuiPage(PageInfo<T> info) {
        this.page = info.getPageNum();
        this.limit = info.getPageSize();
        this.count = (int) info.getTotal();
        this.data = info.getList();
        this.totalPage = info.getPages();

    }

    //layui所需字段
    private String code="000000";
    private String msg = "";

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    
    public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDire() {
		return dire;
	}

	public void setDire(String dire) {
		this.dire = dire;
	}

	public void setMsg(String msg) {
        this.msg = msg;
    }
}