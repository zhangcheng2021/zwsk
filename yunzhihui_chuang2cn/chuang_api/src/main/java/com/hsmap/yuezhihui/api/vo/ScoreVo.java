package com.hsmap.yuezhihui.api.vo;

public class ScoreVo {
    private Integer spceId;
    private Integer projectId;
    private Integer groupId;
    private Integer ruleId;
    private String signImg;
    private Integer score;
    private String remark;

    public Integer getSpceId() {
        return spceId;
    }

    public void setSpceId(Integer spceId) {
        this.spceId = spceId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ScoreVo{" +
                "spceId=" + spceId +
                ", projectId=" + projectId +
                ", groupId=" + groupId +
                ", ruleId=" + ruleId +
                ", signImg=" + signImg +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String toString2() {
        return "ScoreVo{" +
                "spceId=" + spceId +
                ", projectId=" + projectId +
                ", groupId=" + groupId +
                ", ruleId=" + ruleId +
                ", score=" + score +
                ", remark='" + remark + '\'' +
                '}';
    }
}
