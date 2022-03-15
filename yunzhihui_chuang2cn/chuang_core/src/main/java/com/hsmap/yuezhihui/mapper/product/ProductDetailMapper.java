package com.hsmap.yuezhihui.mapper.product;

import com.hsmap.yuezhihui.base.mapper.BaseMapper;
import com.hsmap.yuezhihui.entity.product.ProductDetail;
import com.hsmap.yuezhihui.entity.product.ProductDetailExample;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailMapper extends BaseMapper<ProductDetail,Integer, ProductDetailExample> {

}

