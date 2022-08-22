package com.cn.wanxi.service.impl;

import com.cn.wanxi.dao.ProductDao;
import com.cn.wanxi.dao.impl.ProductImpl;
import com.cn.wanxi.model.ProductModel;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.service.ProductService;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao=new ProductImpl();
    @Override
    public ResultModel add(ProductModel model) {
        return null;
    }

    @Override
    public ResultModel delete(ProductModel model) {
        return null;
    }

    @Override
    public ResultModel findAll(ProductModel model) {
        return ResultModel.getResultModel(productDao.getCount(model),productDao.findAll(model));
    }

    @Override
    public ResultModel findById(ProductModel model) {
        return ResultModel.getResultModel(productDao.findById(model));
    }

    @Override
    public ResultModel getCount(ProductModel model) {
        return null;
    }

    @Override
    public ResultModel update(ProductModel model) {
        return null;
    }

    @Override
    public ResultModel enable(ProductModel model) {
        return ResultModel.getResultModel(productDao.enable(model));
    }

}
