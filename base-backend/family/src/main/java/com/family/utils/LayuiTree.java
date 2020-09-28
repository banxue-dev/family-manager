package com.family.utils;

import java.util.ArrayList;
import java.util.List;

public class LayuiTree {
    private String id;//节点索引
    private String pid;//父节点索引
    private String title;//节点名称
    private List<LayuiTree> children;//子节点
    private String href;//点击节点弹出新窗口对应的 url
    private boolean disabled = false;//节点是否为禁用状态。默认 false
    private boolean spread = true;
    private String checkedSum;
    private Object checked;

    public static List<LayuiTree> toLayuiTree(List<LayuiTree> list, String pid) {
        List<LayuiTree> resultList = new ArrayList<LayuiTree>();
        if (list == null || list.size() == 0) {
            return null;
        }
        for (LayuiTree tree : list) {
            if (pid.equals(tree.getPid())) {
                resultList.add(tree);
                tree.setChildren(toLayuiTree(list, tree.getId()));
            }
        }
        return resultList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public String getCheckedSum() {
        return checkedSum;
    }

    public void setCheckedSum(String checkedSum) {
        this.checkedSum = checkedSum;
    }

    public Object getChecked() {
        return checked;
    }

    public void setChecked(Object checked) {
        this.checked = checked;
    }

    public List<LayuiTree> getChildren() {
        return children;
    }

    public void setChildren(List<LayuiTree> children) {
        this.children = children;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
