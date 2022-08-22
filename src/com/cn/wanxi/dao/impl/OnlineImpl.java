package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.OnlineDao;
import com.cn.wanxi.model.OnlineModel;
import com.cn.wanxi.util.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OnlineImpl implements OnlineDao {
    @Override
    public int add(OnlineModel onlineModel) {
        String sql = "insert into online (name,phone,email,content) values(" +
                "'" + onlineModel.getName() + "'," +
                "'" + onlineModel.getPhone() + "'," +
                "'" + onlineModel.getEmail() + "'," +
                "'" + onlineModel.getContent() + "')";
        return JDBC.update(sql);
    }

    @Override
    public int delete(OnlineModel onlineModel) {
        String sql = "delete from online where id=" + onlineModel.getId();
        return JDBC.update(sql);
    }

    @Override
    public int update(OnlineModel onlineModel) {
        String sql = "";
        return JDBC.update(sql);
    }

    @Override
    public List<OnlineModel> findAll(OnlineModel onlineModel) {
        String sql = "select * from online ";
//        需要将结果集进行解析，解析成List<OnlineModel>
        List<OnlineModel> list = new ArrayList<>();
        ResultSet resultSet = JDBC.query(sql);
        try {
//            resultSet.next()判断resultSet是否由下一条数据
            while (resultSet.next()) {
                OnlineModel model = new OnlineModel();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("name"));
                model.setPhone(resultSet.getString("phone"));
                model.setContent(resultSet.getString("content"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public OnlineModel findById(OnlineModel onlineModel) {
        String sql = "select * from online where id=" + onlineModel.getId();
        ResultSet resultSet = JDBC.query(sql);
        OnlineModel model = new OnlineModel();
        try {
//            resultSet.next()判断resultSet是否由下一条数据
            while (resultSet.next()) {
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("name"));
                model.setPhone(resultSet.getString("phone"));
                model.setContent(resultSet.getString("content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public int getCount(OnlineModel onlineModel) {
        String sql = "select count(*) count from online";
        return JDBC.getCount(sql);
    }
}
