package com.cn.wanxi.service.impl;
import com.cn.wanxi.dao.FenLeiDao;
import com.cn.wanxi.dao.impl.FenLeiImpl;
import com.cn.wanxi.model.FenLeiModel;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.service.FenLeiService;

public class FenLeiServiceImpl implements FenLeiService {
    private FenLeiDao FenLeiDao = new FenLeiImpl();

    @Override
    public ResultModel add(FenLeiModel cuisineModel) {
        return null;
    }

    @Override
    public ResultModel update(FenLeiModel FenLeiModel) {
        return null;
    }

    @Override
    public ResultModel delete(FenLeiModel FenLeiModel) {
        return null;
    }

    @Override
    public ResultModel findById(FenLeiModel FenLeiModel) {
        return null;
    }

    @Override
    public ResultModel findAll(FenLeiModel FenLeiModel) {
        return ResultModel.getResultModel(FenLeiDao.getCount(FenLeiModel), FenLeiDao.findAll(FenLeiModel));
    }

    @Override
    public ResultModel getCount(FenLeiModel FenLeiModel) {
        return null;
    }
}
