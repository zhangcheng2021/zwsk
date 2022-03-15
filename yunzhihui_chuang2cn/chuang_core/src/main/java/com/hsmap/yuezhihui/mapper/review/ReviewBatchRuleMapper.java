
package com.hsmap.yuezhihui.mapper.review;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.review.ReviewBatchRule;
import com.hsmap.yuezhihui.entity.review.ReviewBatchRuleExample;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewBatchRuleMapper extends BaseMapper<ReviewBatchRule,Integer, ReviewBatchRuleExample> {

    void deleteByBatchId(int batchId);
}
