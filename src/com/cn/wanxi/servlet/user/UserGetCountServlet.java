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

@WebServlet("/back/user/count")
public class UserGetCountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        1.乱码处理
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
//        2.得到前端的数据
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String hobby = req.getParameter("hobby");
        String birthday = req.getParameter("birthday");
        String address = req.getParameter("address");
        String enable = req.getParameter("enable");
//        3.封装mdoel
        UserModel model = new UserModel();
        model.setName(Tool.nullToString(name));
        model.setPassword(Tool.nullToString(password));
        model.setPhone(Tool.nullToString(phone));
        model.setEmail(Tool.nullToString(email));
        model.setHobby(Tool.nullToString(hobby));
        model.setBirthday(Tool.nullToDate(birthday));
        model.setEnable(Tool.strToInt(enable));
        model.setAddress(Tool.nullToString(address));
//        4.调用服务逻辑层
        UserService userService = new UserServiceImpl();
//        5.得到返回的结果
        ResultModel resultModel = userService.getCount(model);
//        6.将结果返回给前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
