package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewBatchSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewGroup;
import com.hsmap.yuezhihui.entity.review.ReviewSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewSpecialistExample;

import java.util.List;

/**
 * 评审专家service接口
 */
public interface IReviewSpecialistService extends IBaseService<ReviewSpecialist, Integer, ReviewSpecialistExample> {

    PageInfo<ReviewSpecialist> pageList(ReviewSpecialist model, Pageable pageable);

    List<ReviewSpecialist> listByModel(ReviewSpecialist model);


    List<ReviewSpecialist> findByAll();

    void batchSaveReviewSpec(ReviewGroup group, List<ReviewBatchSpecialist> specialistList, int productCount);

    void saveReviewSpec(ReviewGroup group, ReviewBatchSpecialist specialistList, int productCount);

    /**
     * 新增专家到组，更新批次-专家的IS-Use
     *
     * @param specialistList
     * @param specialists
     */
    void saveSpecListUse(List<ReviewBatchSpecialist> specialistList, List<ReviewSpecialist> specialists);

    /**
     * 审批组移除专家
     */
    void removeGroupSpec(ReviewSpecialist specialist, ReviewBatchSpecialist batchSpecialist);

    /**
     * 级联查询所有专家
     * @return
     */
    List<ReviewSpecialist> selectAll();
}
