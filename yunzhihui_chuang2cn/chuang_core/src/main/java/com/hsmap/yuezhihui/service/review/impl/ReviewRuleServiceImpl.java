package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.review.ReviewRule;
import com.hsmap.yuezhihui.entity.review.ReviewRuleExample;
import com.hsmap.yuezhihui.mapper.review.ReviewRuleMapper;
import com.hsmap.yuezhihui.service.review.IReviewRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewRuleServiceImpl extends BaseServiceImpl<ReviewRule,Integer, ReviewRuleExample> implements IReviewRuleService {

    @Autowired
    ReviewRuleMapper ReviewRuleMapper;

    @Override
    public BaseMapper<ReviewRule, Integer, ReviewRuleExample> getMapper() {
        return ReviewRuleMapper;
    }

    @Override
    public PageInfo<ReviewRule> pageList(ReviewRule model, Pageable pageable) {
        ReviewRuleExample example  = new ReviewRuleExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<ReviewRule> listByModel(ReviewRule model) {
        ReviewRuleExample example  = new ReviewRuleExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<ReviewRule> findByAll() {
        ReviewRule model = new ReviewRule();
        return listByModel(model);
    }


    private ReviewRuleExample.Criteria getBaseExample(ReviewRuleExample example, ReviewRule model) {
        ReviewRuleExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if(!CommonUtil.isEmpty(model.getName())){
            criteria.andNameLike("%"+model.getName()+"%");
        }

        return criteria;
    }
}
