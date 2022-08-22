package com.cn.wanxi.service.impl;

import com.cn.wanxi.dao.NewsDao;
import com.cn.wanxi.dao.ProductDao;
import com.cn.wanxi.dao.impl.NewsImpl;
import com.cn.wanxi.dao.impl.ProductImpl;
import com.cn.wanxi.model.NewsModel;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.service.NewsService;

public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao=new NewsImpl();

    @Override
    public ResultModel add(NewsModel model) {
        return null;
    }

    @Override
    public ResultModel delete(NewsModel model) {
        return null;
    }

    @Override
    public ResultModel findAll(NewsModel model) {
        return ResultModel.getResultModel(newsDao.getCount(model),newsDao.findAll(model));
    }

    @Override
    public ResultModel findById(NewsModel model) {
        return ResultModel.getResultModel(newsDao.findById(model));
    }

    @Override
    public ResultModel getCount(NewsModel model) {
        return null;
    }

    @Override
    public ResultModel update(NewsModel model) {
        return null;
    }

    @Override
    public ResultModel enable(NewsModel model) {
        return ResultModel.getResultModel(newsDao.enable(model));
    }
}
