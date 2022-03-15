package com.hsmap.yuezhihui.admin.vo;

import com.hsmap.yuezhihui.entity.review.ReviewRule;
import com.hsmap.yuezhihui.entity.review.ReviewRuleDetail;

import java.util.List;

public class ReviewRuleVO {
    private Integer id;

    private String name;

    private Integer projectTypeId;

    private String projectTypeName;

    private Integer duration;

    private Integer scoreType;

    List<ReviewRuleDetail> detailList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Integer projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getScoreType() {
        return scoreType;
    }

    public void setScoreType(Integer scoreType) {
        this.scoreType = scoreType;
    }

    public List<ReviewRuleDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ReviewRuleDetail> detailList) {
        this.detailList = detailList;
    }
}
