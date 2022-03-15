package com.hsmap.yuezhihui.service.product;

import com.hsmap.yuezhihui.base.page.PageInfo;
import com.hsmap.yuezhihui.base.page.Pageable;
import com.hsmap.yuezhihui.base.service.IBaseService;
import com.hsmap.yuezhihui.entity.product.ProductDetail;
import com.hsmap.yuezhihui.entity.product.ProductDetailExample;

import java.util.List;

/**
 * 项目详情service接口
 */
public interface IProductDetailService extends IBaseService<ProductDetail,Integer, ProductDetailExample> {

    PageInfo<ProductDetail> pageList(ProductDetail model, Pageable pageable);

    List<ProductDetail> listByModel(ProductDetail model);


    List<ProductDetail> findByAll();

}
