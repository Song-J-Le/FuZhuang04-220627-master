package com.cn.wanxi.dao;

import com.cn.wanxi.model.OnlineModel;

import java.util.List;

public interface BaseDao<E> {
    int add(E e);

    int delete(E e);

    int update(E e);

    List<E> findAll(E e);

    E findById(E e);

    int getCount(E e);
}
