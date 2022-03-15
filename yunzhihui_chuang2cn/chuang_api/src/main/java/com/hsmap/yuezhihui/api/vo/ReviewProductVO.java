package com.hsmap.yuezhihui.api.vo;

import com.hsmap.yuezhihui.entity.review.ReviewProduct;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ReviewProductVO {

    private Integer id;

    private Integer productId;

    private String productName;

    private Integer groupId;

    private String groupName;

    private Integer ruleId;

    private String ruleName;

    private String duration;

    private Integer sort;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public static List<ReviewProductVO> list2vo(List<ReviewProduct> list){
        List<ReviewProductVO> voList = new ArrayList<>();
        for (ReviewProduct bean:list) {
            voList.add(obj2vo(bean));
        }
        return voList;
    }

    public static ReviewProductVO obj2vo(ReviewProduct bean){
        ReviewProductVO vo = new ReviewProductVO();
        BeanUtils.copyProperties(bean,vo);
        return vo;
    }
}
