package com.cn.wanxi.servlet.online;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.model.OnlineModel;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.service.OnlineService;
import com.cn.wanxi.service.impl.OnlineServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/back/online/count")
public class OnlineGetCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        得到前端的数据
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String content = req.getParameter("content");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
//        封装数据
        OnlineModel onlineModel = new OnlineModel();
        onlineModel.setName(name);
        onlineModel.setEmail(email);
        onlineModel.setContent(content);
        onlineModel.setPhone(phone);
//        调用服务逻辑层
        OnlineService onlineService = new OnlineServiceImpl();
        ResultModel result = onlineService.getCount(onlineModel);
//        返回页面
        resp.getWriter().println(JSONObject.toJSONString(result));
    }

}
