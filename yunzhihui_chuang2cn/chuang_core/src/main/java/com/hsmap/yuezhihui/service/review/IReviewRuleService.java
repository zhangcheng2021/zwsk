package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewRule;
import com.hsmap.yuezhihui.entity.review.ReviewRuleExample;

import java.util.List;

/**
 * 评分规则service接口
 */
public interface IReviewRuleService extends IBaseService<ReviewRule,Integer, ReviewRuleExample> {

    PageInfo<ReviewRule> pageList(ReviewRule model, Pageable pageable);

    List<ReviewRule> listByModel(ReviewRule model);


    List<ReviewRule> findByAll();

}
