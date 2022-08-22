package com.cn.wanxi.service;

import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.model.UserModel;

public interface UserService extends BaseService<UserModel> {


    ResultModel password(UserModel model);

    ResultModel login(UserModel model);

    ResultModel enable(UserModel model);
}
