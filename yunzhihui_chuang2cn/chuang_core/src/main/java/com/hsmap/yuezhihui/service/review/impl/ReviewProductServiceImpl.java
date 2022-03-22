package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.product.ProductInfo;
import com.hsmap.yuezhihui.entity.review.*;
import com.hsmap.yuezhihui.mapper.review.ReviewProductMapper;
import com.hsmap.yuezhihui.service.product.IProductInfoService;
import com.hsmap.yuezhihui.service.review.IReviewBatchRuleService;
import com.hsmap.yuezhihui.service.review.IReviewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewProductServiceImpl extends BaseServiceImpl<ReviewProduct, Integer, ReviewProductExample> implements IReviewProductService {

    @Autowired
    ReviewProductMapper ReviewProductMapper;
    @Autowired
    IProductInfoService productInfoService;
    @Autowired
    IReviewBatchRuleService reviewBatchRuleService;

    @Override
    public BaseMapper<ReviewProduct, Integer, ReviewProductExample> getMapper() {
        return ReviewProductMapper;
    }

    @Override
    public PageInfo<ReviewProduct> pageList(ReviewProduct model, Pageable pageable) {
        ReviewProductExample example = new ReviewProductExample();
        getBaseExample(example, model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example, pageable);
    }

    @Override
    public List<ReviewProduct> listByModel(ReviewProduct model) {
        ReviewProductExample example = new ReviewProductExample();
        getBaseExample(example, model);
        return find(example);
    }

    @Override
    public List<ReviewProduct> findByAll() {
        ReviewProduct model = new ReviewProduct();
        return listByModel(model);
    }

    @Override
    public List<ReviewProduct> findByGroupId(int groupId) {
        ReviewProduct model = new ReviewProduct();
        model.setGroupId(groupId);
        return listByModel(model);
    }

    @Override
    public void saveReviewProductInfo(ReviewGroup group, int productId, int specCount) {
        ReviewProduct reviewProduct = new ReviewProduct();
        ProductInfo productInfo = productInfoService.findById(productId);

        if (productInfo == null) {
            LOGGER.error("productInfo is null");
            return;
        }
        List<ReviewBatchRule> batchRuleList = reviewBatchRuleService.findByBatchAndProjectType(group.getBatchId(), productInfo.getTypeId());
        if (batchRuleList == null || batchRuleList.size() < 1) {
            LOGGER.error("batchRuleList is null ");
            return;
        }
        ReviewBatchRule batchRule = batchRuleList.get(0);
        reviewProduct.setProductId(productInfo.getId());
        reviewProduct.setProductName(productInfo.getProductName());
        reviewProduct.setGroupId(group.getId());
        reviewProduct.setGroupName(group.getName());
        reviewProduct.setRuleId(batchRule.getRuleId());
        reviewProduct.setRuleName(batchRule.getRuleName());
        reviewProduct.setSpecCount(specCount);
        save(reviewProduct);
    }

    @Override
    public void batchSaveReviewProductInfo(ReviewGroup group, int[] productIdArray, int specCount) {
        for (int id : productIdArray) {
            saveReviewProductInfo(group, id, specCount);
        }
    }


    private ReviewProductExample.Criteria getBaseExample(ReviewProductExample example, ReviewProduct model) {
        ReviewProductExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        return criteria;
    }

    /**
     * 根据提供的Product+Group 更新status , score
     *
     * @param product
     * @return
     */
    @Override
    public int updateByGroupAndProduct(ReviewProduct product) {
        return ReviewProductMapper.updateByGroupAndProduct(product);
    }

    public  List<ReviewProduct> findUnderReviewProductList(){
        return  ReviewProductMapper.findUnderReviewProductList();
    }
}
