package com.cn.wanxi.model;

/**
 * 基类，父类
 */
public class BaseModel {
    private Integer id;//主键，自增,唯一
    private String createTime;//
    private String updateTime;//什么时候修改了这条数据
    private Integer enable;//当前这条数据是否有用
    private String remark;// 备注：什么原因修改了这条数据，这条数据有什么用
    private String name;
    private Integer page;//分页：第几页
    private Integer limit;//每一页显示的条数

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
