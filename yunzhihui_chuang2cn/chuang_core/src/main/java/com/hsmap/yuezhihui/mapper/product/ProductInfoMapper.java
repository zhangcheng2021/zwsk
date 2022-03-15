package com.hsmap.yuezhihui.mapper.product;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.product.GroupProductInfo;
import com.hsmap.yuezhihui.entity.product.ProductInfo;
import com.hsmap.yuezhihui.entity.product.ProductInfoExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoMapper extends BaseMapper<ProductInfo,Integer, ProductInfoExample> {
    /**
     * 分组下的项目列表
     * @param query
     * @return
     */
    List<GroupProductInfo> selectGroupProductInfo(GroupProductInfo query);

    /**
     *  api product/productList 使用
     * @param batchId
     * @return
     */
    List<ProductInfo> selectProductGroupAll(Integer batchId);
}

