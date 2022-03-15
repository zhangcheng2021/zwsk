package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.review.ReviewBatch;
import com.hsmap.yuezhihui.entity.review.ReviewBatchExample;
import com.hsmap.yuezhihui.mapper.review.ReviewBatchMapper;
import com.hsmap.yuezhihui.service.review.IReviewBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewBatchServiceImpl extends BaseServiceImpl<ReviewBatch, Integer, ReviewBatchExample> implements IReviewBatchService {

    @Autowired
    ReviewBatchMapper reviewBatchMapper;

    @Override
    public BaseMapper<ReviewBatch, Integer, ReviewBatchExample> getMapper() {
        return reviewBatchMapper;
    }

    @Override
    public PageInfo<ReviewBatch> pageList(ReviewBatch model, Pageable pageable) {
        ReviewBatchExample example = new ReviewBatchExample();
        getBaseExample(example, model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example, pageable);
    }

    @Override
    public List<ReviewBatch> listByModel(ReviewBatch model) {
        ReviewBatchExample example = new ReviewBatchExample();
        getBaseExample(example, model);
        return find(example);
    }

    @Override
    public List<ReviewBatch> findByAll() {
        ReviewBatch model = new ReviewBatch();
        return listByModel(model);
    }

    @Override
    public void restart(int id) {

        reviewBatchMapper.restart(id);

    }

    @Override
    public List<ReviewBatch> findByStatus(int status) {
        ReviewBatch model = new ReviewBatch();
        model.setStatus(status);
        return listByModel(model);
    }

    @Override
    public ReviewBatch findByValid() {
        List<ReviewBatch> batchList = findByStatus(1);
        ReviewBatch batch = null;
        if (batchList != null && batchList.size() > 0) {
            batch = batchList.get(0);
        }
        if (batch == null) {
            batch.setId(0);
            batch.setContent("");
        }
        return batch;
    }


    private ReviewBatchExample.Criteria getBaseExample(ReviewBatchExample example, ReviewBatch model) {
        ReviewBatchExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if (model.getStatus() != null) {
            criteria.andStatusEqualTo(model.getStatus());
        }
        if (!CommonUtil.isEmpty(model.getName())) {
            criteria.andNameLike("%" + model.getName() + "%");
        }
        return criteria;
    }
}
