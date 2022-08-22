package com.cn.wanxi.service;

import com.cn.wanxi.model.ResultModel;


public interface BaseService<E> {
    ResultModel add(E e);

    ResultModel delete(E e);

    ResultModel findAll(E e);

    ResultModel findById(E e);

    ResultModel getCount(E e);

    ResultModel update(E e);
}
