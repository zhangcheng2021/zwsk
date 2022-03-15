package com.hsmap.yuezhihui.mapper.review;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.review.ReviewWeight;
import com.hsmap.yuezhihui.entity.review.ReviewWeightExample;
import com.hsmap.yuezhihui.entity.review.ReviewWeight;
import com.hsmap.yuezhihui.entity.review.ReviewWeightExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewWeightMapper extends BaseMapper<ReviewWeight,Integer, ReviewWeightExample> {

}
