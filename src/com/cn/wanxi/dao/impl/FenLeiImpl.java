package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.FenLeiDao;
import com.cn.wanxi.model.FenLeiModel;
import com.cn.wanxi.util.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FenLeiImpl implements FenLeiDao {
    @Override
    public int add(FenLeiModel cuisineModel) {
        return 0;
    }

    @Override
    public int getCount(FenLeiModel cuisineModel) {
        return 0;
    }

    @Override
    public List<FenLeiModel> findAll(FenLeiModel cuisineModel) {
        String sql = "select * from FenLei where is_show=" + cuisineModel.getIsShow();

        return getList(sql);
    }

    private List<FenLeiModel> getList(String sql) {
        List<FenLeiModel> list = new ArrayList<>();
        ResultSet resultSet = JDBC.query(sql);
        try {
            while (resultSet.next()) {
                FenLeiModel model = new FenLeiModel();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("name"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public FenLeiModel findById(FenLeiModel cuisineModel) {
        return null;
    }

    @Override
    public int delete(FenLeiModel cuisineModel) {
        return 0;
    }

    @Override
    public int update(FenLeiModel cuisineModel) {
        return 0;
    }
}
