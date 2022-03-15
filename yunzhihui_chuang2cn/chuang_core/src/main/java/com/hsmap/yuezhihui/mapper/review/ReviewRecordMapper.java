package com.hsmap.yuezhihui.mapper.review;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.review.ReviewRecord;
import com.hsmap.yuezhihui.entity.review.ReviewRecordExample;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRecordMapper extends BaseMapper<ReviewRecord,Integer, ReviewRecordExample> {

}
