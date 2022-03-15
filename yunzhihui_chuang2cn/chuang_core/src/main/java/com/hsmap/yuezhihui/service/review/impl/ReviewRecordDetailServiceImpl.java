package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.review.ReviewRecordDetail;
import com.hsmap.yuezhihui.entity.review.ReviewRecordDetailExample;
import com.hsmap.yuezhihui.mapper.review.ReviewRecordDetailMapper;
import com.hsmap.yuezhihui.service.review.IReviewRecordDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewRecordDetailServiceImpl extends BaseServiceImpl<ReviewRecordDetail,Integer, ReviewRecordDetailExample> implements IReviewRecordDetailService {

    @Autowired
    ReviewRecordDetailMapper ReviewRecordDetailMapper;

    @Override
    public BaseMapper<ReviewRecordDetail, Integer, ReviewRecordDetailExample> getMapper() {
        return ReviewRecordDetailMapper;
    }

    @Override
    public PageInfo<ReviewRecordDetail> pageList(ReviewRecordDetail model, Pageable pageable) {
        ReviewRecordDetailExample example  = new ReviewRecordDetailExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<ReviewRecordDetail> listByModel(ReviewRecordDetail model) {
        ReviewRecordDetailExample example  = new ReviewRecordDetailExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<ReviewRecordDetail> findByAll() {
        ReviewRecordDetail model = new ReviewRecordDetail();
        return listByModel(model);
    }

    @Override
    public ReviewRecordDetail findByDetailId(int recordId, int ruleDetailId) {
        ReviewRecordDetail model = new ReviewRecordDetail();
        model.setRecordId(recordId);
        model.setRuleDetailId(ruleDetailId);
        List<ReviewRecordDetail> list = listByModel(model);
        if(list == null || list.size()<1){
            return null;
        }

        return list.get(0);
    }


    private ReviewRecordDetailExample.Criteria getBaseExample(ReviewRecordDetailExample example, ReviewRecordDetail model) {
        ReviewRecordDetailExample.Criteria criteria = example.createCriteria();
//        example.setOrderByClause("sort_ desc,id");
        criteria.andIsDelEqualTo(0);
        if(model.getRuleDetailId()!=null && model.getRuleDetailId()>0){
            criteria.andRuleDetailIdEqualTo(model.getRuleDetailId());
        }

        if(model.getRecordId()!=null && model.getRecordId()>0){
            criteria.andRecordIdEqualTo(model.getRecordId());
        }
        return criteria;
    }
}
