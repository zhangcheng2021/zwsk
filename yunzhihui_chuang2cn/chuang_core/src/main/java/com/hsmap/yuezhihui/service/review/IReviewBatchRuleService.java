package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewBatchRule;
import com.hsmap.yuezhihui.entity.review.ReviewBatchRuleExample;

import java.util.List;

/**
 * 评审批次类型规则关联service接口
 */
public interface IReviewBatchRuleService extends IBaseService<ReviewBatchRule,Integer, ReviewBatchRuleExample> {

    PageInfo<ReviewBatchRule> pageList(ReviewBatchRule model, Pageable pageable);

    List<ReviewBatchRule> listByModel(ReviewBatchRule model);


    List<ReviewBatchRule> findByAll();


    List<ReviewBatchRule> findByBatch(int batchId);

    List<ReviewBatchRule> findByBatchAndProjectType(int batchId,int projectTypeId);

    void deleteByBatchId(int batchId);
}
