package com.hsmap.yuezhihui.service.product.impl;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.BaseServiceImpl;
import com.hsmap.yuezhihui.common.CommonUtil;
import com.hsmap.yuezhihui.entity.product.GroupProductInfo;
import com.hsmap.yuezhihui.entity.product.ProductInfo;
import com.hsmap.yuezhihui.entity.product.ProductInfoExample;
import com.hsmap.yuezhihui.mapper.product.ProductInfoMapper;
import com.hsmap.yuezhihui.service.product.IProductInfoService;
import com.hsmap.yuezhihui.service.review.IReviewWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl extends BaseServiceImpl<ProductInfo, Integer, ProductInfoExample> implements IProductInfoService {


    @Autowired
    ProductInfoMapper ProductInfoMapper;

    @Autowired
    IReviewWeightService weightService;

    @Override
    public BaseMapper<ProductInfo, Integer, ProductInfoExample> getMapper() {
        return ProductInfoMapper;
    }

    @Override
    public PageInfo<ProductInfo> pageList(ProductInfo model, Pageable pageable) {
        ProductInfoExample example = new ProductInfoExample();
        getBaseExample(example, model);
        example.setPageNumber(pageable.getPageNumber());
        example.setPageSize(pageable.getPageSize());
        return findByPage(example, pageable);
    }

    @Override
    public List<ProductInfo> listByModel(ProductInfo model) {
        ProductInfoExample example = new ProductInfoExample();
        getBaseExample(example, model);
        return find(example);
    }

    @Override
    public List<ProductInfo> findByAll() {
        ProductInfo model = new ProductInfo();
        return listByModel(model);
    }

    @Override
    public long countBybatchId(int batchId, List<Integer> territoryIdList) {
        ProductInfoExample example = new ProductInfoExample();
        ProductInfo model = new ProductInfo();
        model.setBatchId(batchId);
        ProductInfoExample.Criteria criteria = getBaseExample(example, model);
        if (territoryIdList != null && territoryIdList.size() > 0) {
            criteria.andTerritoryIdIn(territoryIdList);
        }
        return count(example);
    }

    @Override
    public List<ProductInfo> findByBatchId(int batchId, List<Integer> territoryIdList) {
        ProductInfoExample example = new ProductInfoExample();
        ProductInfo model = new ProductInfo();
        model.setBatchId(batchId);
        ProductInfoExample.Criteria criteria = getBaseExample(example, model);
        if (territoryIdList != null && territoryIdList.size() > 0) {
            criteria.andTerritoryIdIn(territoryIdList);
        }
        return find(example);
    }

    @Override
    public int getWeightSorce(int productTypeId, int specTypeId, int score) {
        LOGGER.info("productTypeId:{},specTypeId:{},score:{}", productTypeId, specTypeId, score);
        int weight = weightService.getRedisWeightBySpecAndProject(specTypeId, productTypeId);
        LOGGER.info("weight-------------------------------------------112211------------>" + weight);
        int weightScore = score * weight / 100;
        return weightScore;
    }


    private ProductInfoExample.Criteria getBaseExample(ProductInfoExample example, ProductInfo model) {
        ProductInfoExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" a.sort_ desc, a.id desc");
        criteria.andIsDelEqualTo(0);
        //人才类型
        if (model.getTypeId() != null && model.getTypeId() > 0) {
            criteria.andTypeIdEqualTo(model.getTypeId());
        }
        //批次
        if (model.getBatchId() != null && model.getBatchId() > 0) {
            criteria.andBatchIdEqualTo(model.getBatchId());
        }
        //专业领域
        if (model.getTerritoryId() != null && model.getTerritoryId() > 0) {
            criteria.andTerritoryIdEqualTo(model.getTerritoryId());
        }
        //申报人like
        if (model.getUserName() != null && model.getUserName().trim().length() > 0) {
            criteria.andUserNameLike("%" + model.getUserName().trim() + "%");
        }
        //申报项目like
        if (model.getProductName() != null && model.getProductName().trim().length() > 0) {
            criteria.andProductNameLike("%" + model.getProductName().trim() + "%");
        }
        return criteria;
    }

    /**
     * 分组下的项目列表
     *
     * @param query
     * @return
     */
    @Override
    public List<GroupProductInfo> selectGroupProductInfo(GroupProductInfo query) {
        return ProductInfoMapper.selectGroupProductInfo(query);
    }


    /**
     * api product/productList 使用
     *
     * @param batchId
     * @return
     */
    @Override
    public List<ProductInfo> selectProductGroupAll(Integer batchId) {
        return ProductInfoMapper.selectProductGroupAll(batchId);
    }
}
