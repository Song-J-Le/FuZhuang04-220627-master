package com.cn.wanxi.dao;

import com.cn.wanxi.model.ProductModel;

public interface ProductDao extends BaseDao<ProductModel> {
    int enable(ProductModel model);
}
