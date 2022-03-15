package com.hsmap.yuezhihui.service.product;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.product.GroupProductInfo;
import com.hsmap.yuezhihui.entity.product.ProductInfo;
import com.hsmap.yuezhihui.entity.product.ProductInfoExample;

import java.util.List;

/**
 * 项目信息service接口
 */
public interface IProductInfoService extends IBaseService<ProductInfo,Integer, ProductInfoExample> {

    PageInfo<ProductInfo> pageList(ProductInfo model, Pageable pageable);

    List<ProductInfo> listByModel(ProductInfo model);


    List<ProductInfo> findByAll();

    long countBybatchId(int batchId,List<Integer> territoryIdList);

    List<ProductInfo> findByBatchId(int batchId,List<Integer> territoryIdList);

    int getWeightSorce(int productTypeId,int specTypeId,int sorce);


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
