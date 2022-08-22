package com.cn.wanxi.servlet.news;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.model.NewsModel;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.service.NewsService;
import com.cn.wanxi.service.impl.NewsServiceImpl;
import com.cn.wanxi.util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/back/news/enable")
public class NewsUpdateEnableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        1.乱码处理
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
//        2.得到前端的数据
        String id = req.getParameter("id");

//        3.封装mdoel
        NewsModel model = new NewsModel();
        model.setId(Tool.strToInt(id));

//        4.调用服务逻辑层
        NewsService newsService = new NewsServiceImpl();
//        5.得到结果信息
        ResultModel resultModel = newsService.enable(model);
//        6.返回前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
 }
}
