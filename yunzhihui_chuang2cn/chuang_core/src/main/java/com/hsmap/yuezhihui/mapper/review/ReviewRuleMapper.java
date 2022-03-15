package com.hsmap.yuezhihui.mapper.review;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.review.ReviewRule;
import com.hsmap.yuezhihui.entity.review.ReviewRule;
import com.hsmap.yuezhihui.entity.review.ReviewRuleExample;
import com.hsmap.yuezhihui.entity.review.ReviewRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRuleMapper  extends BaseMapper<ReviewRule,Integer, ReviewRuleExample> {

}
