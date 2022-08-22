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

@WebServlet("/back/product/add")
public class ProductAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        1.乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
//        2.得到签到传递过来的数据
        String name = req.getParameter("name");
        String imgHref = req.getParameter("imgHref");
        String content = req.getParameter("content");
        String marketPrice = req.getParameter("marketPrice");
        String normalPrice = req.getParameter("normalPrice");
//        3.将这些封装成对象
        ProductModel model = new ProductModel();
        model.setName(name);
        model.setImgHref(imgHref);
        model.setContent(content);
//        将字符串强制转换为BigDecimal，那就表示里面有方法，并且方法的返回值为BigDecimal
        model.setMarketPrice(Tool.nullToBigDecimal(marketPrice));
        model.setNormalPrice(Tool.nullToBigDecimal(normalPrice));
//        4.调用服务逻辑层
        ProductService productService = new ProductServiceImpl();
//        5.得到结果信息
        ResultModel resultModel = productService.add(model);
//        6.返回前端
        resp.getWriter().println(JSONObject.toJSONString(resultModel));
    }
}
