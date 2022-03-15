package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewBatch;
import com.hsmap.yuezhihui.entity.review.ReviewBatchExample;

import java.util.List;

/**
 * 评审批次service接口
 */
public interface IReviewBatchService extends IBaseService<ReviewBatch,Integer, ReviewBatchExample> {

    PageInfo<ReviewBatch> pageList(ReviewBatch model, Pageable pageable);

    List<ReviewBatch> listByModel(ReviewBatch model);


    List<ReviewBatch> findByAll();

    void restart(int id);

    List<ReviewBatch> findByStatus(int i);

    ReviewBatch findByValid();

}
