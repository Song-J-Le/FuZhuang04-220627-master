package com.cn.wanxi.service.impl;

import com.cn.wanxi.dao.UserDao;
import com.cn.wanxi.dao.impl.UserImpl;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.model.UserModel;
import com.cn.wanxi.service.UserService;
import com.cn.wanxi.util.Md5;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserImpl();

    @Override
    public ResultModel add(UserModel model) {
//        model.setPassword(Md5.encoderByMd5(model.getPassword()));
        return ResultModel.getResultModel(userDao.add(model));
    }

    @Override
    public ResultModel delete(UserModel model) {
        return ResultModel.getResultModel(userDao.delete(model));
    }

    @Override
    public ResultModel findAll(UserModel model) {
        return ResultModel.getResultModel(userDao.getCount(model), userDao.findAll(model));
    }

    @Override
    public ResultModel findById(UserModel model) {
        return ResultModel.getResultModel(userDao.findById(model));
    }

    @Override
    public ResultModel getCount(UserModel model) {
        return ResultModel.getResultModel(userDao.getCount(model));
    }

    @Override
    public ResultModel update(UserModel model) {
        return ResultModel.getResultModel(userDao.update(model));
    }

    @Override
    public ResultModel password(UserModel model) {
        return ResultModel.getResultModel(userDao.password(model));
    }

    @Override
    public ResultModel login(UserModel model) {

//        if (!model.getCode().equals(model.getSessionCode())) {
        if (false) {
//            直接返回验证码错误
            return ResultModel.getResultModel("error-code");
        }
        //            验证码正确，则查询数据库
//            根据用户名和秘密查询，返回的要么是一条数据，要么没有
        model.setPassword(Md5.encoderByMd5(model.getPassword()));
        UserModel userModel = userDao.findByUsernameAndPassword(model);
        if (userModel == null) {
            return ResultModel.getResultModel("error");
        }
        return ResultModel.getResultModel("success");


    }

    @Override
    public ResultModel enable(UserModel model) {

        return ResultModel.getResultModel(userDao.enable(model));
    }
}
