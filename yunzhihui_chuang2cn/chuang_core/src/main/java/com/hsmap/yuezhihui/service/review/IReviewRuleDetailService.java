package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewRuleDetail;
import com.hsmap.yuezhihui.entity.review.ReviewRuleDetailExample;

import java.util.List;

/**
 * 评分规则详情service接口
 */
public interface IReviewRuleDetailService extends IBaseService<ReviewRuleDetail,Integer, ReviewRuleDetailExample> {

    PageInfo<ReviewRuleDetail> pageList(ReviewRuleDetail model, Pageable pageable);

    List<ReviewRuleDetail> listByModel(ReviewRuleDetail model);


    List<ReviewRuleDetail> findByAll();

    List<ReviewRuleDetail> findByRuleId(int ruleId);

    void deleteByRuleId(int ruleId);
}
