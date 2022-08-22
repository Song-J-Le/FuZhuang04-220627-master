package com.cn.wanxi.dao;

import com.cn.wanxi.model.UserModel;

import java.util.List;

public interface UserDao extends BaseDao<UserModel>{


    int password(UserModel model);

    UserModel findByUsernameAndPassword(UserModel model);

    int enable(UserModel model);
}
