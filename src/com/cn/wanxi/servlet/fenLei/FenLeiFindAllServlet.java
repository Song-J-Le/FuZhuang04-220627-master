package com.cn.wanxi.servlet.fenLei;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.model.FenLeiModel;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.service.FenLeiService;
import com.cn.wanxi.service.impl.FenLeiServiceImpl;
import com.cn.wanxi.util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/back/FenLei/findAll")
public class FenLeiFindAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        1.乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //        2.得到前端传递过来的数据
        String isShow = req.getParameter("isShow");
        //        3.将这些封装成对象
        FenLeiModel model = new FenLeiModel();
        model.setIsShow(Tool.strToInt(isShow));
        //        4.调用服务逻辑层
        FenLeiService FenLeiService=new FenLeiServiceImpl();
        //        5.得到结果信息
        ResultModel resultModel = FenLeiService.findAll(model);
//        6.返回前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));

    }
}