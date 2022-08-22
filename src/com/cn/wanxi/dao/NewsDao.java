package com.cn.wanxi.dao;

import com.cn.wanxi.model.NewsModel;

public interface NewsDao extends BaseDao<NewsModel> {
     int enable(NewsModel model);
}
