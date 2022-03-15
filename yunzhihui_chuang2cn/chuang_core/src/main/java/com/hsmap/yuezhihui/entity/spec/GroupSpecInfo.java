package com.hsmap.yuezhihui.entity.spec;

import com.hsmap.yuezhihui.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class GroupSpecInfo extends SpecInfo {
    private int hasLogin = 0;
    private Integer reviewSpeciaId;

    private Integer queryGroupId;

    public Integer getReviewSpeciaId() {
        return reviewSpeciaId;
    }

    public void setReviewSpeciaId(Integer reviewSpeciaId) {
        this.reviewSpeciaId = reviewSpeciaId;
    }

    public int getHasLogin() {
        return hasLogin;
    }

    public void setHasLogin(int hasLogin) {
        this.hasLogin = hasLogin;
    }

    public Integer getQueryGroupId() {
        return queryGroupId;
    }

    public void setQueryGroupId(Integer queryGroupId) {
        this.queryGroupId = queryGroupId;
    }
}
