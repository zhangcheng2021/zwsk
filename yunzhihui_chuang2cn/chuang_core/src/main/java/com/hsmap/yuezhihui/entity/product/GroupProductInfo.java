package com.hsmap.yuezhihui.entity.product;

import com.hsmap.yuezhihui.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class GroupProductInfo extends ProductInfo {
    private Integer groupOfStatus;
    private Integer groupOfSort;
    private Integer reviewProductId;

    private Integer queryGroupId;

    public Integer getReviewProductId() {
        return reviewProductId;
    }

    public void setReviewProductId(Integer reviewProductId) {
        this.reviewProductId = reviewProductId;
    }

    public Integer getGroupOfStatus() {
        return groupOfStatus;
    }

    public void setGroupOfStatus(Integer groupOfStatus) {
        this.groupOfStatus = groupOfStatus;
    }

    public Integer getGroupOfSort() {
        return groupOfSort;
    }

    public void setGroupOfSort(Integer groupOfSort) {
        this.groupOfSort = groupOfSort;
    }

    public Integer getQueryGroupId() {
        return queryGroupId;
    }

    public void setQueryGroupId(Integer queryGroupId) {
        this.queryGroupId = queryGroupId;
    }
}
