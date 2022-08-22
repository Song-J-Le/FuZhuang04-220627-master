package com.cn.wanxi.service;

import com.cn.wanxi.model.NewsModel;
import com.cn.wanxi.model.ResultModel;

public interface NewsService extends BaseService<NewsModel>{
    ResultModel enable(NewsModel model);
}
