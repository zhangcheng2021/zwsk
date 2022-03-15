package com.hsmap.yuezhihui.entity.spec;

public class BatchSpecInfo extends SpecInfo {
    private int pageNumber;
    private int pageSize;


    private Integer reviewBatchSpecId;
    private Integer queryBatchId;

    public Integer getReviewBatchSpecId() {
        return reviewBatchSpecId;
    }

    public void setReviewBatchSpecId(Integer reviewBatchSpecId) {
        this.reviewBatchSpecId = reviewBatchSpecId;
    }

    public Integer getQueryBatchId() {
        return queryBatchId;
    }

    public void setQueryBatchId(Integer queryBatchId) {
        this.queryBatchId = queryBatchId;
    }


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
            pageSize = 1;
        }
        this.pageSize = pageSize;
    }
}
