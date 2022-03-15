package com.hsmap.yuezhihui.service.review;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.review.ReviewBatchSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewGroup;
import com.hsmap.yuezhihui.entity.review.ReviewProduct;
import com.hsmap.yuezhihui.entity.review.ReviewProductExample;

import java.util.List;

/**
 * 评审分组service接口
 */
public interface IReviewProductService extends IBaseService<ReviewProduct,Integer, ReviewProductExample> {

    PageInfo<ReviewProduct> pageList(ReviewProduct model, Pageable pageable);

    List<ReviewProduct> listByModel(ReviewProduct model);


    List<ReviewProduct> findByAll();

    List<ReviewProduct> findByGroupId(int id);


    void saveReviewProductInfo(ReviewGroup group,int productIdArray,int specCount);

    void batchSaveReviewProductInfo(ReviewGroup group,int[] productIdArray,int specCount);

    /**
     * 根据提供的Product+Group 更新status , score
     * @param product
     * @return
     */
    int updateByGroupAndProduct(ReviewProduct product);


}
