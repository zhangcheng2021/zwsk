package com.hsmap.yuezhihui.mapper.review;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.review.ReviewRecordDetail;
import com.hsmap.yuezhihui.entity.review.ReviewRecordDetailExample;
import com.hsmap.yuezhihui.entity.review.ReviewRecordDetail;
import com.hsmap.yuezhihui.entity.review.ReviewRecordDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRecordDetailMapper extends BaseMapper<ReviewRecordDetail,Integer, ReviewRecordDetailExample> {

}
