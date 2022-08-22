package com.cn.wanxi.service.impl;

import com.cn.wanxi.dao.OnlineDao;
import com.cn.wanxi.dao.impl.OnlineImpl;
import com.cn.wanxi.model.OnlineModel;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.service.OnlineService;

import java.util.List;

/**
 * 为什么要把三元运算符写在这里？
 * 为什么步直接在dao层返回String？
 * 因为service才是逻辑层，才是写逻辑代码的地方
 */
public class OnlineServiceImpl implements OnlineService {
    private OnlineDao onlineDao = new OnlineImpl();

    @Override
    public ResultModel add(OnlineModel onlineModel) {

        int count = onlineDao.add(onlineModel);
//        ResultModel resultModel = new ResultModel();
//        resultModel.setCount(count);
//        resultModel.setMsg(count > 0 ? "success" : "error");
//        return resultModel;
        return ResultModel.getResultModel(count);
    }

    @Override
    public ResultModel delete(OnlineModel onlineModel) {
//        OnlineDao onlineDao = new OnlineImpl();
        int count = onlineDao.delete(onlineModel);
//        ResultModel resultModel = new ResultModel();
//        resultModel.setCount(count);
//        resultModel.setMsg(count > 0 ? "success" : "error");
//        return resultModel;
        return ResultModel.getResultModel(count);
    }

    @Override
    public ResultModel update(OnlineModel onlineModel) {
//        OnlineDao onlineDao = new OnlineImpl();
        int count = onlineDao.update(onlineModel);
//        ResultModel resultModel = new ResultModel();
//        resultModel.setCount(count);
//        resultModel.setMsg(count > 0 ? "success" : "error");
//        return resultModel;
        return ResultModel.getResultModel(count);
    }

    @Override
    public ResultModel findAll(OnlineModel onlineModel) {
//        OnlineDao onlineDao = new OnlineImpl();
        List<OnlineModel> all = onlineDao.findAll(onlineModel);
        int count = onlineDao.getCount(onlineModel);
//        ResultModel resultModel = new ResultModel();
//        resultModel.setCount(count);
//        resultModel.setMsg("");
//        resultModel.setCode(0);
//        resultModel.setData(all);
//        return resultModel;
        return ResultModel.getResultModel(count, all);
    }

    @Override
    public ResultModel findById(OnlineModel onlineModel) {
//        OnlineDao onlineDao = new OnlineImpl();
        OnlineModel model = onlineDao.findById(onlineModel);
//        ResultModel resultModel = new ResultModel();
//        resultModel.setCount(1);
//        resultModel.setMsg("");
//        resultModel.setCode(0);
//        resultModel.setData(model);
//        return resultModel;
        return ResultModel.getResultModel(model);

    }

    @Override
    public ResultModel getCount(OnlineModel onlineModel) {
//        OnlineDao onlineDao = new OnlineImpl();
        int count = onlineDao.getCount(onlineModel);
//        ResultModel resultModel = new ResultModel();
//        resultModel.setCount(count);
//        resultModel.setMsg("");
//        resultModel.setCode(0);
//        return resultModel;
        return ResultModel.getResultModel(count);
    }


}
