package com.cn.wanxi.servlet.product;

import com.alibaba.fastjson.JSONObject;
import com.cn.wanxi.model.ProductModel;
import com.cn.wanxi.model.ResultModel;
import com.cn.wanxi.service.ProductService;
import com.cn.wanxi.service.impl.ProductServiceImpl;
import com.cn.wanxi.util.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/back/product/findAll")
public class ProductFindAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        1.乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
//        2.得到签到传递过来的数据
        String name = req.getParameter("name");
        String marketPrice = req.getParameter("marketPrice");
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
//        3.将这些封装成对象
        ProductModel model = new ProductModel();
        model.setName(Tool.nullToString(name));
        model.setPage(Tool.strToPage(page));
        model.setLimit(Tool.strToLimit(limit));
        model.setMarketPrice(Tool.nullToBigDecimal(marketPrice));
//        4.调用服务逻辑层
        ProductService productService = new ProductServiceImpl();
//        5.得到结果信息
        ResultModel resultModel = productService.findAll(model);
//        6.返回前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));

    }
}
