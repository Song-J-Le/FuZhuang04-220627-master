package com.cn.wanxi.service;

import com.cn.wanxi.model.ProductModel;
import com.cn.wanxi.model.ResultModel;

public interface ProductService extends BaseService<ProductModel> {
    ResultModel enable(ProductModel model);

//    ResultModel enbabke(ProductModel model);
}
