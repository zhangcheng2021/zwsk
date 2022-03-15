package com.hsmap.yuezhihui.entity.review;

public class ComplexReviewResultDo {
    private String groupName;
    private Integer countAll;

    private Integer countA;
    private Integer countB;
    private Integer countC;
    private Integer countNo;

    //入选数 countA+countB+countC
    public Integer getCountIn() {
        return countA + countB + countC;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getCountAll() {
        return countAll;
    }

    public void setCountAll(Integer countAll) {
        this.countAll = countAll;
    }

    public Integer getCountA() {
        return countA;
    }

    public void setCountA(Integer countA) {
        this.countA = countA;
    }

    public Integer getCountB() {
        return countB;
    }

    public void setCountB(Integer countB) {
        this.countB = countB;
    }

    public Integer getCountC() {
        return countC;
    }

    public void setCountC(Integer countC) {
        this.countC = countC;
    }

    public Integer getCountNo() {
        return countNo;
    }

    public void setCountNo(Integer countNo) {
        this.countNo = countNo;
    }
}
