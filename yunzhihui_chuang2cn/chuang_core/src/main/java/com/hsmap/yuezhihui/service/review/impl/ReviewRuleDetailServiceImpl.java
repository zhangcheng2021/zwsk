package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.review.ReviewRuleDetail;
import com.hsmap.yuezhihui.entity.review.ReviewRuleDetailExample;
import com.hsmap.yuezhihui.mapper.review.ReviewRuleDetailMapper;
import com.hsmap.yuezhihui.service.review.IReviewRuleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewRuleDetailServiceImpl extends BaseServiceImpl<ReviewRuleDetail,Integer, ReviewRuleDetailExample> implements IReviewRuleDetailService {

    @Autowired
    ReviewRuleDetailMapper ReviewRuleDetailMapper;

    @Override
    public BaseMapper<ReviewRuleDetail, Integer, ReviewRuleDetailExample> getMapper() {
        return ReviewRuleDetailMapper;
    }

    @Override
    public PageInfo<ReviewRuleDetail> pageList(ReviewRuleDetail model, Pageable pageable) {
        ReviewRuleDetailExample example  = new ReviewRuleDetailExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<ReviewRuleDetail> listByModel(ReviewRuleDetail model) {
        ReviewRuleDetailExample example  = new ReviewRuleDetailExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<ReviewRuleDetail> findByAll() {
        ReviewRuleDetail model = new ReviewRuleDetail();
        return listByModel(model);
    }

    @Override
    public List<ReviewRuleDetail> findByRuleId(int ruleId) {
        ReviewRuleDetail model = new ReviewRuleDetail();
        model.setRuleId(ruleId);
        return listByModel(model);
    }

    @Override
    public void deleteByRuleId(int ruleId) {
        ReviewRuleDetailMapper.deleteByRuleId(ruleId);
    }


    private ReviewRuleDetailExample.Criteria getBaseExample(ReviewRuleDetailExample example, ReviewRuleDetail model) {
        ReviewRuleDetailExample.Criteria criteria = example.createCriteria();
//        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if(model.getRuleId()!=null && model.getRuleId()>0){
            criteria.andRuleIdEqualTo(model.getRuleId());
        }
        return criteria;
    }
}
