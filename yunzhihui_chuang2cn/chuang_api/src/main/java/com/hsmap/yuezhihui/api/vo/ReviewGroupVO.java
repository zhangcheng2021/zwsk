package com.hsmap.yuezhihui.api.vo;

import com.hsmap.yuezhihui.entity.review.ReviewGroup;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ReviewGroupVO {

    private Integer id;

    private String name;

    private Integer specCount;

    private Integer productCount;

    private String address;

    private String amStartTime;

    private String amEndTime;

    private String pmStartTime;

    private String pmEndTime;

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

    public Integer getSpecCount() {
        return specCount;
    }

    public void setSpecCount(Integer specCount) {
        this.specCount = specCount;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmStartTime() {
        return amStartTime;
    }

    public void setAmStartTime(String amStartTime) {
        this.amStartTime = amStartTime;
    }

    public String getAmEndTime() {
        return amEndTime;
    }

    public void setAmEndTime(String amEndTime) {
        this.amEndTime = amEndTime;
    }

    public String getPmStartTime() {
        return pmStartTime;
    }

    public void setPmStartTime(String pmStartTime) {
        this.pmStartTime = pmStartTime;
    }

    public String getPmEndTime() {
        return pmEndTime;
    }

    public void setPmEndTime(String pmEndTime) {
        this.pmEndTime = pmEndTime;
    }

    public static List<ReviewGroupVO> list2vo(List<ReviewGroup> list) {
        List<ReviewGroupVO> voList = new ArrayList<>();
        for (ReviewGroup bean : list) {
            voList.add(obj2vo(bean));
        }
        return voList;
    }

    public static ReviewGroupVO obj2vo(ReviewGroup bean) {
        ReviewGroupVO vo = new ReviewGroupVO();
        BeanUtils.copyProperties(bean, vo);
        return vo;
    }
}
