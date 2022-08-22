package com.cn.wanxi.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.model.UserModel;
import com.cn.wanxi.service.UserService;
import com.cn.wanxi.service.impl.UserServiceImpl;
import com.cn.wanxi.util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/back/user/delete")
public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        1.乱码处理
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
//        2.得到前端的数据
        String id = req.getParameter("id");

//        3.封装mdoel
        UserModel model = new UserModel();
        model.setId(Tool.strToInt(id));

//        4.调用服务逻辑层
        UserService userService = new UserServiceImpl();
//        5.得到返回的结果
        ResultModel resultModel = userService.delete(model);
//        6.将结果返回给前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
