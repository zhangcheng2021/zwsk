package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewBatchSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewBatchSpecialistExample;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;

import java.util.List;

/**
 * 专家批次service接口
 */
public interface IReviewBatchSpecialistService extends IBaseService<ReviewBatchSpecialist, Integer, ReviewBatchSpecialistExample> {

    PageInfo<ReviewBatchSpecialist> pageList(ReviewBatchSpecialist model, Pageable pageable);

    List<ReviewBatchSpecialist> listByModel(ReviewBatchSpecialist model);

    List<ReviewBatchSpecialist> findByAll();

    List<ReviewBatchSpecialist> allotSpec(int batchId, List<Integer> territoryList);

    List<ReviewBatchSpecialist> findByIdList(List<Integer> idList);

    List<ReviewBatchSpecialist> findByIdList(List<Integer> idList, Integer batchId);

    /**
     * 批次ID和专家ID更新专家的使用标志
     *
     * @param model
     * @return
     */
    int updateUseByBatchIdAndSpecId(ReviewBatchSpecialist model);

}
