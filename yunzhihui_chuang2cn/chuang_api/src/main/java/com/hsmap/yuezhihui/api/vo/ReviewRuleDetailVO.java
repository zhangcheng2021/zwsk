package com.hsmap.yuezhihui.api.vo;

import com.hsmap.yuezhihui.entity.review.ReviewRuleDetail;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ReviewRuleDetailVO {

    private Integer id;

    private Integer ruleId;

    private String type;

    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static List<ReviewRuleDetailVO> list2vo(List<ReviewRuleDetail> list){
        List<ReviewRuleDetailVO> voList = new ArrayList<>();
        for (ReviewRuleDetail bean:list) {
            voList.add(obj2vo(bean));
        }
        return voList;
    }

    public static ReviewRuleDetailVO obj2vo(ReviewRuleDetail bean){
        ReviewRuleDetailVO vo = new ReviewRuleDetailVO();
        BeanUtils.copyProperties(bean,vo);
        return vo;
    }
}
