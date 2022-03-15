package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewRecord;
import com.hsmap.yuezhihui.entity.review.ReviewRecordExample;

import java.util.List;

/**
 * 评分记录service接口
 */
public interface IReviewRecordService extends IBaseService<ReviewRecord,Integer, ReviewRecordExample> {

    PageInfo<ReviewRecord> pageList(ReviewRecord model, Pageable pageable);

    List<ReviewRecord> listByModel(ReviewRecord model);


    List<ReviewRecord> findByAll();

    ReviewRecord findByGroupId(int groupId,int projectId,int spceId);

    int getWeightSorce(ReviewRecord record);

}
