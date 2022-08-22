package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.NewsDao;
import com.cn.wanxi.model.NewsModel;
import com.cn.wanxi.model.ProductModel;
import com.cn.wanxi.util.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsImpl implements NewsDao {
    @Override
    public int add(NewsModel model) {
        return 0;
    }

    @Override
    public int delete(NewsModel model) {
        return 0;
    }

    @Override
    public int update(NewsModel model) {
        return 0;
    }

    @Override
    public List<NewsModel> findAll(NewsModel model) {
        String sql = "select * from " +
                " (select p.*,c.name FenLeiName from news p" +
                " left join FenLei c " +
                " on p.FenLei_id=c.id)  temp  where true ";
        sql += getSql(model);
        sql += " order by id desc ";
        sql += " limit " + (model.getPage() - 1) * model.getLimit() + "," + model.getLimit() + ";";
        return getList(sql);
    }

    private List<NewsModel> getList(String sql) {
        ResultSet resultSet = JDBC.query(sql);
        List<NewsModel> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                NewsModel model = new NewsModel();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("name"));
                model.setImgHref(resultSet.getString("img_href"));
                try {
                    model.setFenLeiName(resultSet.getString("fenLeiName"));
                } catch (Exception e) {
                }
                model.setIsShow(resultSet.getInt("is_show"));
                model.setIsRecommend(resultSet.getInt("is_recommend"));
                model.setContent(resultSet.getString("content"));
                model.setFenLeiId(resultSet.getInt("FenLei_id"));
                model.setEnable(resultSet.getInt("enable"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String getSql(NewsModel model) {
        String sql = "";
        if (!"".equals(model.getName())) {
            sql += " and name like '%" + model.getName() + "%'";
        }
        return sql;
    }

    @Override
    public NewsModel findById(NewsModel model) {
        String sql = "select * from news where id =" + model.getId();
        List<NewsModel> list = getList(sql);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int getCount(NewsModel model) {
        String sql = "select count(*) count from " +
                " (select p.*,c.name FenLeiName from news p" +
                " left join FenLei c " +
                " on p.FenLei_id=c.id)  temp where true ";
        sql += getSql(model);
        return JDBC.getCount(sql);
    }

    @Override
    public int enable(NewsModel model) {
        String sql = " update news set " +
                " enable =" +
                " case " +
                "     when 1 then 0 " +
                "     when 0 then 1 " +
                "     else 0" +
                "     end " +
                "     where id=" + model.getId();
        return JDBC.update(sql);
    }
}