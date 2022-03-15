package com.hsmap.yuezhihui.base.entity;

public class BaseExample {
    private int pageNumber;
    private int pageSize;

    private String groupByClause;

    /**
     * 组数据域
     */
    private String groupDataScore;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize =1;
        }
        this.pageSize = pageSize;
    }

    public String getGroupByClause() {
        return groupByClause;
    }

    public void setGroupByClause(String groupByClause) {
        this.groupByClause = groupByClause;
    }

    public String getGroupDataScore() {
        return groupDataScore;
    }

    public void setGroupDataScore(String groupDataScore) {
        this.groupDataScore = groupDataScore;
    }
}
