package com.hsmap.yuezhihui.mapper.review;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.review.ReviewBatch;
import com.hsmap.yuezhihui.entity.review.ReviewBatchExample;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewBatchMapper extends BaseMapper<ReviewBatch,Integer, ReviewBatchExample> {
    void restart(int id);

}
