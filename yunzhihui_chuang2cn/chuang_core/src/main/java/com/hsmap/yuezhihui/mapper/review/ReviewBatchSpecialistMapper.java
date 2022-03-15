package com.hsmap.yuezhihui.mapper.review;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.review.ReviewBatchSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewBatchSpecialistExample;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewBatchSpecialistMapper extends BaseMapper<ReviewBatchSpecialist, Integer, ReviewBatchSpecialistExample> {
    /**
     * 批次ID和专家ID更新专家的使用标志
     *
     * @param model
     * @return
     */
    int updateUseByBatchIdAndSpecId(ReviewBatchSpecialist model);
}

