package com.cn.wanxi.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.model.UserModel;
import com.cn.wanxi.service.UserService;
import com.cn.wanxi.service.impl.UserServiceImpl;
import com.cn.wanxi.util.Md5;
import com.cn.wanxi.util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * doget和dopost的区别？
 * 只要涉及到密码，流，安全全部用post
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        1.乱码处理
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
//        2.得到前端的数据
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        String sessionCode = (String) req.getSession().getAttribute("vCode");
//        3.封装mdoel
        UserModel model = new UserModel();
        model.setName(Tool.nullToString(name));
        model.setPassword(Md5.encoderByMd5(Tool.nullToString(password)));
        model.setCode(code);
        model.setSessionCode(sessionCode);
//        ResultModel resultModel = null;
//        if (!code.equals(sessionCode)) {
//            resultModel = ResultModel.getResultModel("error-code");
//        }else {
            //        4.调用服务逻辑层
            UserService userService = new UserServiceImpl();
//        5.得到返回的结果
        ResultModel    resultModel = userService.login(model);
//        }

//        6.将结果返回给前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));

    }
}
