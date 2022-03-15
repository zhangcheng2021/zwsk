package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.review.ReviewWeight;
import com.hsmap.yuezhihui.entity.review.ReviewWeightExample;
import com.hsmap.yuezhihui.mapper.review.ReviewWeightMapper;
import com.hsmap.yuezhihui.service.review.IReviewWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewWeightServiceImpl extends BaseServiceImpl<ReviewWeight,Integer, ReviewWeightExample> implements IReviewWeightService {

    @Autowired
    ReviewWeightMapper ReviewWeightMapper;

    private final String _WEIGHT_REDIS_OBJ = "_RESD231_WEIGHT_REDIS_S%S_P%S";

    @Override
    public BaseMapper<ReviewWeight, Integer, ReviewWeightExample> getMapper() {
        return ReviewWeightMapper;
    }

    @Override
    public PageInfo<ReviewWeight> pageList(ReviewWeight model, Pageable pageable) {
        ReviewWeightExample example  = new ReviewWeightExample();
        getBaseExample(example,model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example,pageable);
    }

    @Override
    public List<ReviewWeight> listByModel(ReviewWeight model) {
        ReviewWeightExample example  = new ReviewWeightExample();
        getBaseExample(example,model);
        return find(example);
    }

    @Override
    public List<ReviewWeight> findByAll() {
        ReviewWeight model = new ReviewWeight();
        return listByModel(model);
    }

    @Override
    public List<ReviewWeight> findBySpecTypeId(int specTypeId) {
        ReviewWeight model = new ReviewWeight();
        model.setSpecTypeId(String.valueOf(specTypeId));
        return listByModel(model);
    }

    @Override
    public List<ReviewWeight> findByProjectTypeId(int projectTypeId) {
        ReviewWeight model = new ReviewWeight();
        model.setProjectTypeId(String.valueOf(projectTypeId));
        return listByModel(model);
    }

    @Override
    public void pushAllToRedis() {
        List<ReviewWeight> list = findByAll();
        for(ReviewWeight weight:list){
            setJsonToRedis(String.format(_WEIGHT_REDIS_OBJ,weight.getSpecTypeId(),weight.getProjectTypeId()),weight);
        }

    }

    @Override
    public ReviewWeight getRedisBySpecAndProject(int specTypeId, int projectTypeId) {
        LOGGER.info("specTypeId:{},projectTypeId:{}",specTypeId,projectTypeId);
        String key  = String.format(_WEIGHT_REDIS_OBJ,specTypeId,projectTypeId);
        LOGGER.info("---json key {}",key);
        return getObjectByRedis(key,ReviewWeight.class);
    }

    @Override
    public int getRedisWeightBySpecAndProject(int specTypeId, int projectTypeId) {
        int weight = 100 ;
        ReviewWeight reviewWeight = getRedisBySpecAndProject(specTypeId,projectTypeId);
        if(reviewWeight!=null){
            weight = reviewWeight.getWeight();
        }
        return weight;
    }


    private ReviewWeightExample.Criteria getBaseExample(ReviewWeightExample example, ReviewWeight model) {
        ReviewWeightExample.Criteria criteria = example.createCriteria();
//        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if(!CommonUtil.isEmpty(model.getProjectTypeId())){
            criteria.andProjectTypeIdEqualTo(model.getProjectTypeId());
        }
        if(!CommonUtil.isEmpty(model.getSpecTypeId())){
            criteria.andSpecTypeIdEqualTo(model.getSpecTypeId());
        }
        return criteria;
    }
}
