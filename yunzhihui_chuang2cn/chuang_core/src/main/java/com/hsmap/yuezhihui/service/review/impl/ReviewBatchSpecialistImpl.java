package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.entity.review.ReviewBatchSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewBatchSpecialistExample;
import com.hsmap.yuezhihui.entity.spec.SpecInfo;
import com.hsmap.yuezhihui.entity.spec.SpecInfoExample;
import com.hsmap.yuezhihui.mapper.review.ReviewBatchSpecialistMapper;
import com.hsmap.yuezhihui.service.review.IReviewBatchSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewBatchSpecialistImpl extends BaseServiceImpl<ReviewBatchSpecialist, Integer, ReviewBatchSpecialistExample> implements IReviewBatchSpecialistService {

    @Autowired
    ReviewBatchSpecialistMapper ReviewBatchSpecialistMapper;

    @Override
    public BaseMapper<ReviewBatchSpecialist, Integer, ReviewBatchSpecialistExample> getMapper() {
        return ReviewBatchSpecialistMapper;
    }

    @Override
    public PageInfo<ReviewBatchSpecialist> pageList(ReviewBatchSpecialist model, Pageable pageable) {
        ReviewBatchSpecialistExample example = new ReviewBatchSpecialistExample();
        getBaseExample(example, model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example, pageable);
    }

    @Override
    public List<ReviewBatchSpecialist> listByModel(ReviewBatchSpecialist model) {
        ReviewBatchSpecialistExample example = new ReviewBatchSpecialistExample();
        getBaseExample(example, model);
        return find(example);
    }

    @Override
    public List<ReviewBatchSpecialist> findByAll() {
        ReviewBatchSpecialist model = new ReviewBatchSpecialist();
        return listByModel(model);
    }


    @Override
    public List<ReviewBatchSpecialist> allotSpec(int batchId, List<Integer> territoryList) {
        //类型 风投4 专业2 企业1
        List<ReviewBatchSpecialist> list1 = findByType(batchId, territoryList, 1, 4);
        List<ReviewBatchSpecialist> list2 = findByType(batchId, territoryList, 2, 2);
        List<ReviewBatchSpecialist> list3 = findByType(batchId, territoryList, 3, 1);
        List<ReviewBatchSpecialist> list = new ArrayList<>();
        if (list1 != null && list1.size() > 0) {
            list.addAll(list1);
        }
        if (list2 != null && list2.size() > 0) {
            list.addAll(list2);
        }
        if (list3 != null && list3.size() > 0) {
            list.addAll(list3);
        }
        return list;
    }

    @Override
    public List<ReviewBatchSpecialist> findByIdList(List<Integer> idList) {
        if (idList == null || idList.size() < 1) {
            return null;
        }
        ReviewBatchSpecialist model = new ReviewBatchSpecialist();

        ReviewBatchSpecialistExample example = new ReviewBatchSpecialistExample();
        ReviewBatchSpecialistExample.Criteria criteria = getBaseExample(example, model);
        criteria.andSpecIdIn(idList);


        return find(example);
    }

    @Override
    public List<ReviewBatchSpecialist> findByIdList(List<Integer> idList, Integer batchId) {
        if (idList == null || idList.size() < 1) {
            return null;
        }
        ReviewBatchSpecialist model = new ReviewBatchSpecialist();

        ReviewBatchSpecialistExample example = new ReviewBatchSpecialistExample();
        ReviewBatchSpecialistExample.Criteria criteria = getBaseExample(example, model);
        criteria.andSpecIdIn(idList);
        criteria.andBatchIdEqualTo(batchId);

        return find(example);
    }


    private ReviewBatchSpecialistExample.Criteria getBaseExample(ReviewBatchSpecialistExample example, ReviewBatchSpecialist model) {
        ReviewBatchSpecialistExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("v.sort_ desc,v.id desc");
        criteria.andIsDelEqualTo(0);
        if (model.getBatchId() != null && model.getBatchId() > 0) {
            criteria.andBatchIdEqualTo(model.getBatchId());
        }
        if (model.getTerritoryId() != null && model.getTerritoryId() > 0) {
            criteria.andTerritoryIdEqualTo(model.getTerritoryId());
        }
        if (model.getSpecTypeId() != null && model.getSpecTypeId() > 0) {
            criteria.andSpecTypeIdEqualTo(model.getSpecTypeId());
        }
        if (model.getIsUse() != null && model.getIsUse() > -1) {
            criteria.andIsUseEqualTo(model.getIsUse());
        }

        return criteria;
    }

    private List<ReviewBatchSpecialist> findByType(int batchId, List<Integer> territoryList, int typeId, int pageSize) {
        ReviewBatchSpecialistExample example = new ReviewBatchSpecialistExample();
        ReviewBatchSpecialist model = new ReviewBatchSpecialist();
        model.setBatchId(batchId);
//        model.setTerritoryId(territoryId);
        model.setSpecTypeId(typeId);
        model.setIsUse(0);
        ReviewBatchSpecialistExample.Criteria criteria = getBaseExample(example, model);
        if (territoryList != null && territoryList.size() > 0) {
            criteria.andTerritoryIdIn(territoryList);
        }
        example.setPageNumber(1);
        example.setPageSize(pageSize);
        Pageable pageable = new Pageable(1, pageSize);
        PageInfo<ReviewBatchSpecialist> pageInfo = findByPage(example, pageable);
        return pageInfo.getContent();

    }

    /**
     * 批次ID和专家ID更新专家的使用标志
     *
     * @param model
     * @return
     */
    @Override
    public int updateUseByBatchIdAndSpecId(ReviewBatchSpecialist model) {
        return ReviewBatchSpecialistMapper.updateUseByBatchIdAndSpecId(model);
    }

}
