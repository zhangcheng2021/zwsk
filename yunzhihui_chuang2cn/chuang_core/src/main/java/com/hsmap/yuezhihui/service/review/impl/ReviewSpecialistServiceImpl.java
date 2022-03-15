package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.review.ReviewBatchSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewGroup;
import com.hsmap.yuezhihui.entity.review.ReviewSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewSpecialistExample;
import com.hsmap.yuezhihui.mapper.review.ReviewSpecialistMapper;
import com.hsmap.yuezhihui.service.review.IReviewBatchSpecialistService;
import com.hsmap.yuezhihui.service.review.IReviewSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewSpecialistServiceImpl extends BaseServiceImpl<ReviewSpecialist, Integer, ReviewSpecialistExample> implements IReviewSpecialistService {

    @Autowired
    ReviewSpecialistMapper ReviewSpecialistMapper;
    @Autowired
    IReviewBatchSpecialistService reviewBatchSpecialistService;

    @Override
    public BaseMapper<ReviewSpecialist, Integer, ReviewSpecialistExample> getMapper() {
        return ReviewSpecialistMapper;
    }

    @Override
    public PageInfo<ReviewSpecialist> pageList(ReviewSpecialist model, Pageable pageable) {
        ReviewSpecialistExample example = new ReviewSpecialistExample();
        getBaseExample(example, model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example, pageable);
    }

    @Override
    public List<ReviewSpecialist> listByModel(ReviewSpecialist model) {
        ReviewSpecialistExample example = new ReviewSpecialistExample();
        getBaseExample(example, model);
        return find(example);
    }

    @Override
    public List<ReviewSpecialist> findByAll() {
        ReviewSpecialist model = new ReviewSpecialist();
        return listByModel(model);
    }

    @Override
    public void batchSaveReviewSpec(ReviewGroup group, List<ReviewBatchSpecialist> specialistList, int productCount) {
        for (ReviewBatchSpecialist model : specialistList) {
            saveReviewSpec(group, model, productCount);
        }
    }

    @Override
    public void saveReviewSpec(ReviewGroup group, ReviewBatchSpecialist spec, int productCount) {
        ReviewSpecialist model = new ReviewSpecialist();
        model.setGroupId(group.getId());
        model.setGroupName(group.getName());
        model.setSpecId(spec.getSpecId());
        model.setSpecName(spec.getSpecName());
        model.setProductCount(productCount);
        save(model);
        spec.setIsUse(1);
        reviewBatchSpecialistService.update(spec);


    }


    private ReviewSpecialistExample.Criteria getBaseExample(ReviewSpecialistExample example, ReviewSpecialist model) {
        ReviewSpecialistExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        return criteria;
    }

    /**
     * 新增专家到组，更新批次-专家的IS-Use
     *
     * @param specialistList
     * @param specialists
     */
    @Override
    @Transactional
    public void saveSpecListUse(List<ReviewBatchSpecialist> specialistList, List<ReviewSpecialist> specialists) {
        for (ReviewBatchSpecialist update : specialistList) {
            reviewBatchSpecialistService.update(update);
        }
        for (ReviewSpecialist add : specialists) {
            save(add);
        }
    }

    /**
     * 审批组移除专家
     */
    @Override
    @Transactional
    public void removeGroupSpec(ReviewSpecialist specialis, ReviewBatchSpecialist batchSpecialist) {
        update(specialis);
        //更新isUse
        reviewBatchSpecialistService.updateUseByBatchIdAndSpecId(batchSpecialist);
    }

    /**
     * 级联查询所有专家
     *
     * @return
     */
    @Override
    public List<ReviewSpecialist> selectAll() {
        return ReviewSpecialistMapper.selectAll();
    }
}
