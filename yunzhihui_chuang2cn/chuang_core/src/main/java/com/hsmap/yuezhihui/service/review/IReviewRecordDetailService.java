package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewRecordDetail;
import com.hsmap.yuezhihui.entity.review.ReviewRecordDetailExample;

import java.util.List;

/**
 * 评分记录详情service接口
 */
public interface IReviewRecordDetailService extends IBaseService<ReviewRecordDetail,Integer, ReviewRecordDetailExample> {

    PageInfo<ReviewRecordDetail> pageList(ReviewRecordDetail model, Pageable pageable);

    List<ReviewRecordDetail> listByModel(ReviewRecordDetail model);


    List<ReviewRecordDetail> findByAll();

    ReviewRecordDetail findByDetailId(int recordId,int ruleDetailId);

}
