package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.entity.review.ReviewBatchRule;
import com.hsmap.yuezhihui.entity.review.ReviewBatchRuleExample;
import com.hsmap.yuezhihui.mapper.review.ReviewBatchRuleMapper;
import com.hsmap.yuezhihui.service.review.IReviewBatchRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewBatchRuleServiceImpl extends BaseServiceImpl<ReviewBatchRule,Integer, ReviewBatchRuleExample> implements IReviewBatchRuleService {

    @Autowired
    ReviewBatchRuleMapper reviewBatchRuleMapper;

    @Override
    public BaseMapper<ReviewBatchRule, Integer, ReviewBatchRuleExample> getMapper() {
        return reviewBatchRuleMapper;
    }

    @Override
    public PageInfo<ReviewBatchRule> pageList(ReviewBatchRule model, Pageable pageable) {
        ReviewBatchRuleExample example  = new ReviewBatchRuleExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<ReviewBatchRule> listByModel(ReviewBatchRule model) {
        ReviewBatchRuleExample example  = new ReviewBatchRuleExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<ReviewBatchRule> findByAll() {
        ReviewBatchRule model = new ReviewBatchRule();
        return listByModel(model);
    }

    @Override
    public List<ReviewBatchRule> findByBatch(int batchId) {
        ReviewBatchRule model = new ReviewBatchRule();
        model.setBatchId(batchId);
        return listByModel(model);
    }

    @Override
    public List<ReviewBatchRule> findByBatchAndProjectType(int batchId, int projectTypeId) {
        ReviewBatchRule model = new ReviewBatchRule();
        model.setBatchId(batchId);
        model.setProjectTypeId(projectTypeId);
        return listByModel(model);
    }

    @Override
    public void deleteByBatchId(int batchId) {
        reviewBatchRuleMapper.deleteByBatchId(batchId);
    }


    private ReviewBatchRuleExample.Criteria getBaseExample(ReviewBatchRuleExample example, ReviewBatchRule model) {
        ReviewBatchRuleExample.Criteria criteria = example.createCriteria();
//        example.setOrderByClause("sort_ desc,id desc");
        if(model.getBatchId()!=null &&model.getBatchId()>0){
            criteria.andBatchIdEqualTo(model.getBatchId());
        }
        if(model.getProjectTypeId()!=null  && model.getProjectTypeId()>0){
            criteria.andProjectTypeIdEqualTo(model.getProjectTypeId());
        }
        criteria.andIsDelEqualTo(0);
        return criteria;
    }
}
