package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.product.ProductInfo;
import com.hsmap.yuezhihui.entity.review.ReviewRecord;
import com.hsmap.yuezhihui.entity.review.ReviewRecordExample;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.mapper.review.ReviewRecordMapper;
import com.hsmap.yuezhihui.service.product.IProductInfoService;
import com.hsmap.yuezhihui.service.review.IReviewRecordService;
import com.hsmap.yuezhihui.service.spec.ISpecInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewRecordServiceImpl extends BaseServiceImpl<ReviewRecord,Integer, ReviewRecordExample> implements IReviewRecordService {

    @Autowired
    ReviewRecordMapper ReviewRecordMapper;

    @Autowired
    IProductInfoService productInfoService;
    @Autowired
    ISpecInfoService specInfoService;

    @Override
    public BaseMapper<ReviewRecord, Integer, ReviewRecordExample> getMapper() {
        return ReviewRecordMapper;
    }

    @Override
    public PageInfo<ReviewRecord> pageList(ReviewRecord model, Pageable pageable) {
        ReviewRecordExample example  = new ReviewRecordExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<ReviewRecord> listByModel(ReviewRecord model) {
        ReviewRecordExample example  = new ReviewRecordExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<ReviewRecord> findByAll() {
        ReviewRecord model = new ReviewRecord();
        return listByModel(model);
    }

    @Override
    public ReviewRecord findByGroupId(int groupId, int projectId, int spceId) {
        ReviewRecord model = new ReviewRecord();
        model.setGroupId(groupId);
        model.setProjectId(projectId);
        model.setSpceId(spceId);
        List<ReviewRecord> list = listByModel(model);
        if(list ==null || list.size()<1){
            return null;
        }
        return list.get(0);
    }

    @Override
    public int getWeightSorce(ReviewRecord record) {
        ProductInfo productInfo = productInfoService.findById(record.getProjectId());
        SpecInfo specInfo = specInfoService.findById(record.getSpceId());
        int weightScore =  productInfoService.getWeightSorce(productInfo.getTypeId(),specInfo.getSpecTypeId(),record.getScore());
        return weightScore;
    }


    private ReviewRecordExample.Criteria getBaseExample(ReviewRecordExample example, ReviewRecord model) {
        ReviewRecordExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if(model.getGroupId()!=null && model.getGroupId()>0){
            criteria.andGroupIdEqualTo(model.getGroupId());
        }
        if(model.getProjectId()!=null && model.getProjectId()>0){
            criteria.andProjectIdEqualTo(model.getProjectId());
        }
        if(model.getSpceId()!=null && model.getSpceId()>0){
            criteria.andSpceIdEqualTo(model.getSpceId());
        }
        return criteria;
    }
}
