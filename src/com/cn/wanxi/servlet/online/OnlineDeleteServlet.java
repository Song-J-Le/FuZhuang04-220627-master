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

@WebServlet("/back/online/delete")
public class OnlineDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        得到前端的数据
        String id = req.getParameter("id");

//        封装数据
        OnlineModel onlineModel = new OnlineModel();
        onlineModel.setId(Integer.valueOf(id));

//        调用服务逻辑层
        OnlineService onlineService = new OnlineServiceImpl();
        ResultModel result = onlineService.delete(onlineModel);
//        返回页面

        resp.getWriter().println(JSONObject.toJSONString(result));
    }


}
