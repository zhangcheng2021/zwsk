package com.hsmap.yuezhihui.api.vo;

import com.hsmap.yuezhihui.entity.review.ReviewSpecialist;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ReviewSpecialistVO {

    private Integer id;

    private Integer groupId;

    private String groupName;

    private Integer specId;

    private String specName;

    private Integer productCount;

    private Integer reviewCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public static List<ReviewSpecialistVO> list2vo(List<ReviewSpecialist> list){
        List<ReviewSpecialistVO> voList = new ArrayList<>();
        for (ReviewSpecialist bean:list) {
            voList.add(obj2vo(bean));
        }
        return voList;
    }

    public static ReviewSpecialistVO obj2vo(ReviewSpecialist bean){
        ReviewSpecialistVO vo = new ReviewSpecialistVO();
        BeanUtils.copyProperties(bean,vo);
        return vo;
    }
}
