package com.cn.wanxi.service.impl;

import com.cn.wanxi.model.ImgModel;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.service.UploadService;

public class UploadServiceImpl implements UploadService {
    @Override
    public ResultModel upload() {
        ImgModel imgModel = new ImgModel();
        imgModel.setSrc("afdsa");
        return ResultModel.getResultModel(imgModel);
    }
}
