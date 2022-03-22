package com.hsmap.yuezhihui.mapper.review;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.review.ReviewProduct;
import com.hsmap.yuezhihui.entity.review.ReviewProductExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewProductMapper extends BaseMapper<ReviewProduct,Integer, ReviewProductExample> {
    /**
     * 根据提供的Product+Group 更新status , score
     *
     * @param product
     * @return
     */
    int updateByGroupAndProduct(ReviewProduct product);

    List<ReviewProduct> findUnderReviewProductList();
}
