package com.hsmap.yuezhihui.service.review.impl;

import com.hsmap.yuezhihui.Constants;
import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.ArraySplit;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.config.SysConfig;
import com.hsmap.yuezhihui.entity.product.ProductInfo;
import com.hsmap.yuezhihui.entity.review.ReviewBatch;
import com.hsmap.yuezhihui.entity.review.ReviewBatchSpecialist;
import com.hsmap.yuezhihui.entity.review.ReviewGroup;
import com.hsmap.yuezhihui.entity.review.ReviewGroupExample;
import com.hsmap.yuezhihui.mapper.review.ReviewGroupMapper;
import com.hsmap.yuezhihui.service.product.IProductInfoService;
import com.hsmap.yuezhihui.service.review.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewGroupServiceImpl extends BaseServiceImpl<ReviewGroup, Integer, ReviewGroupExample> implements IReviewGroupService {

    @Autowired
    ReviewGroupMapper ReviewGroupMapper;
    @Autowired
    IProductInfoService productInfoService;
    @Autowired
    IReviewBatchService reviewBatchService;
    @Autowired
    IReviewBatchRuleService batchRuleService;
    @Autowired
    IReviewProductService reviewProductService;
    @Autowired
    IReviewSpecialistService reviewSpecialistService;
    @Autowired
    IReviewBatchSpecialistService batchSpecialistService;
    @Autowired
    private SysConfig sysConfig;

    @Override
    public BaseMapper<ReviewGroup, Integer, ReviewGroupExample> getMapper() {
        return ReviewGroupMapper;
    }

    @Override
    public PageInfo<ReviewGroup> pageList(ReviewGroup model, Pageable pageable) {
        ReviewGroupExample example = new ReviewGroupExample();
        getBaseExample(example, model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example, pageable);
    }

    @Override
    public List<ReviewGroup> listByModel(ReviewGroup model) {
        ReviewGroupExample example = new ReviewGroupExample();
        getBaseExample(example, model);
        return find(example);
    }

    @Override
    public List<ReviewGroup> findByAll() {
        ReviewGroup model = new ReviewGroup();
        return listByModel(model);
    }

    @Override
    public List<ReviewGroup> findByBatchId(int batchId) {
        ReviewGroup model = new ReviewGroup();
        model.setBatchId(batchId);
        return listByModel(model);
    }

    @Override
    public void addGroup(List<ReviewGroup> groupList) {
        //name id  ids batchId
        int batchId = 0;
        for (ReviewGroup reviewGroup : groupList) {
            //创建分组
            grouping(reviewGroup);
            batchId = reviewGroup.getBatchId();
        }
        if (batchId > 0) {
            ReviewBatch batch = reviewBatchService.findById(batchId);
            if (batch == null) {
                return;
            }
            batch.setStatus(1);
            reviewBatchService.update(batch);
        }
    }


    /***
     * 创建分组
     * @param reviewGroup
     */
    private void grouping(ReviewGroup reviewGroup) {

        int batchId = reviewGroup.getBatchId();
        ReviewBatch reviewBatch = reviewBatchService.findById(batchId);
        String territoryIds = reviewGroup.getTerritoryIds();
        String name = reviewGroup.getName();
        //计算需要分几组
        List<ProductInfo> productInfoList = productInfoService.findByBatchId(batchId, CommonUtil.idsToArray(territoryIds));
        int productCount = productInfoList.size();
        //计算分组数
        int groupCount = CommonUtil.ceil(productCount, Constants._GROUP_PRODUCT_COUNT);
        int groupProjcetCount = CommonUtil.ceil(productCount, groupCount);//；每组人数
        //分配每组成员
        Object array[] = ArraySplit.splitAry(idArrayByList(productInfoList), groupProjcetCount);
        // //根据批次分配专家

        int __index = 1;

        for (Object obj : array) {
            String newName = name;
            if (groupCount > 1) {
                newName = name.replaceAll("组", "");
                newName += (__index++) + "组";
            }
            int[] productIdArray = (int[]) obj;
            LOGGER.info("productIdArray:" + productIdArray.length);
            List<ReviewBatchSpecialist> specialistList = batchSpecialistService.allotSpec(batchId, CommonUtil.idsToArray(territoryIds));
            ReviewGroup group = new ReviewGroup();
            ;
            group.setId(0);
            if (reviewGroup.getId() > 0) {
                group = findById(reviewGroup.getId());
                if (group == null) {
                    group = new ReviewGroup();
                    group.setId(0);
                }
            }
            group.setProductCount(productIdArray.length);
            group.setSpecCount(specialistList.size());
            group.setTerritoryIds(territoryIds);
            group.setBatchId(batchId);
            group.setBatchName(reviewBatch.getName());
            group.setAddress(reviewBatch.getAddress());
            group.setName(newName);
            if (group.getId() > 0) {
                update(group);
            } else {
                save(group);
            }
            //保存分组中的项目列表
            reviewProductService.batchSaveReviewProductInfo(group, productIdArray, specialistList.size());
            //保存分组中的专家列表
            reviewSpecialistService.batchSaveReviewSpec(group, specialistList, productIdArray.length);
        }
    }


    @Override
    public int countProductByBatchId(int batchId, String territoryIds) {
        long count = productInfoService.countBybatchId(batchId, CommonUtil.idsToArray(territoryIds));
        return (int) count;
//        return Integer.parseInt(CommonUtil.getRandomByCount(2));
    }



    private ReviewGroupExample.Criteria getBaseExample(ReviewGroupExample example, ReviewGroup model) {
        ReviewGroupExample.Criteria criteria = example.createCriteria();
//        example.setOrderByClause("sort_ desc,id desc");
        criteria.andIsDelEqualTo(0);
        if (!CommonUtil.isEmpty(model.getName())) {
            criteria.andNameLike("%" + model.getName() + "%");
        }
        if (model.getBatchId() != null && model.getBatchId() > 0) {
            criteria.andBatchIdEqualTo(model.getBatchId());
        }
        if (sysConfig.isRequireGroupScope()) {
            //关联评审组数据域
            if (sysConfig.getGrantGroupIdList() != null) {
                //防止非拦截登录的请求，如api的数据接口
                criteria.andIdIn(sysConfig.getGrantGroupIdList());
            }
        }
        if (model.getId() != null && model.getId() > 0) {
            criteria.andIdEqualTo(model.getId());
        }
        return criteria;
    }


    private int[] idArrayByList(List<ProductInfo> list) {
        int[] idArray = new int[list.size()];
        int index = 0;
        for (ProductInfo productInfo : list) {
            idArray[index++] = productInfo.getId();
        }
        return idArray;
    }

}
