package com.hsmap.yuezhihui.entity.review;

public class ComplexRecommendDo {
    private String recommendName;

    private Integer countAll;
    //初审通过数 无数据
    private int review1 = 0;
    //复审通过数 无数据
    private int review2 = 0;
    //实际参评数 无数据
    private int review3 = 0;
    //现场评审入选数 countA+countB+countC

    private Integer countA;
    private Integer countB;
    private Integer countC;
    private Integer countNo;

    //入选数 countA+countB+countC
    public Integer getCountIn() {
        return countA + countB + countC;
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

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public int getReview1() {
        return review1;
    }

    public void setReview1(int review1) {
        this.review1 = review1;
    }

    public int getReview2() {
        return review2;
    }

    public void setReview2(int review2) {
        this.review2 = review2;
    }

    public int getReview3() {
        return review3;
    }

    public void setReview3(int review3) {
        this.review3 = review3;
    }
}
