package com.cn.wanxi.servlet;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.model.ImgModel;
import com.cn.wanxi.model.ResultModel;

import com.cn.wanxi.util.UpLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        ImgModel imgModel = UpLoad.load(req);
        ResultModel resultModel = ResultModel.getResultModel(imgModel);

        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }

}
