package com.hsmap.yuezhihui.mapper.review;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.review.ReviewRuleDetail;
import com.hsmap.yuezhihui.entity.review.ReviewRuleDetailExample;
import com.hsmap.yuezhihui.entity.review.ReviewRuleDetail;
import com.hsmap.yuezhihui.entity.review.ReviewRuleDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRuleDetailMapper extends BaseMapper<ReviewRuleDetail,Integer, ReviewRuleDetailExample> {

    void deleteByRuleId(int ruleId);

}
