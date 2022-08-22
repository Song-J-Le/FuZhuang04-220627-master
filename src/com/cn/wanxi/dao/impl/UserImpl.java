package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.UserDao;
import com.cn.wanxi.model.UserModel;
import com.cn.wanxi.util.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDao {
    @Override
    public int add(UserModel model) {
        String sql = "INSERT INTO `user` (" +
                "`username`, `address`, `create_time`, " +
                "`email`, `enable`, `password`," +
                " `phone`, `sex`, `update_time`, " +
                "`hobby`, `birthday`, `remark`) VALUES (" +
                "'" + model.getName() + "', '" + model.getAddress() + "', now(), " +
                "'" + model.getEmail() + "', '" + model.getEnable() + "', '" + model.getPassword() + "'," +
                " '" + model.getPhone() + "', '" + model.getSex() + "', now()," +
                " '" + model.getHobby() + "', '" + model.getBirthday() + "', '');\n";
        return JDBC.update(sql);
    }

    @Override
    public int delete(UserModel model) {
        String sql = "delete from user where id=" + model.getId();
        return JDBC.update(sql);
    }

    @Override
    public int getCount(UserModel model) {
        String sql = "select count(*) count from user";
        return JDBC.getCount(sql);
    }

    @Override
    public List<UserModel> findAll(UserModel model) {
        String sql = "select *,date_format(birthday,'%Y/%m/%d') temp from user";
        sql += " limit " + (model.getPage() - 1) * model.getLimit() + "," + model.getLimit() + ";";
        return getList(sql);
    }

    public List<UserModel> getList(String sql) {
        List<UserModel> list = new ArrayList<>();
        ResultSet resultSet = JDBC.query(sql);
        try {
            while (resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setName(resultSet.getString("username"));
                userModel.setPhone(resultSet.getString("phone"));
                userModel.setSex(resultSet.getString("sex"));
                userModel.setEnable(resultSet.getInt("enable"));
                userModel.setAddress(resultSet.getString("address"));
                userModel.setBirthday(resultSet.getString("birthday"));
                userModel.setBirthday(resultSet.getString("temp"));
                userModel.setHobby(resultSet.getString("hobby"));
                userModel.setEmail(resultSet.getString("email"));

                list.add(userModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public UserModel findById(UserModel model) {
        String sql = "select *,date_format(birthday,'%Y/%m/%d') temp from user where id=" + model.getId();
//        ResultSet resultSet = JDBC.query(sql);
//        UserModel userModel = new UserModel();
//
//        try {
//            while (resultSet.next()) {
//                userModel.setId(resultSet.getInt("id"));
//                userModel.setName(resultSet.getString("username"));
//                userModel.setId(resultSet.getInt("id"));
//                userModel.setPhone(resultSet.getString("phone"));
//                userModel.setSex(resultSet.getString("sex"));
//                userModel.setEnable(resultSet.getInt("enable"));
//                userModel.setAddress(resultSet.getString("address"));
//                userModel.setBirthday(resultSet.getString("birthday"));
//                userModel.setHobby(resultSet.getString("hobby"));
//                userModel.setEmail(resultSet.getString("email"));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userModel;
//        如果getList(sql)这里面没有数据，则get(0)就会报错，
//        所以，需要判断是否有值，才能进行get(0)
        List<UserModel> list = getList(sql);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int update(UserModel model) {
        String sql = "UPDATE `user` SET ";
        if (!"".equals(model.getName())) {
            sql += "`username` = '" + model.getName() + "',";
        }
        if (!"".equals(model.getAddress())) {
            sql += " `address` = '" + model.getAddress() + "',";
        }
        if (!"".equals(model.getEmail())) {
            sql += " `email` = '" + model.getEmail() + "',";
        }
        if (model.getEnable() != -1) {
            sql += " `enable` = '" + model.getEnable() + "', ";
        }
        if (!"".equals(model.getPhone())) {
            sql += "`phone` = '" + model.getPhone() + "', ";
        }
        if (!"".equals(model.getBirthday())) {
            sql += "`birthday` = '" + model.getBirthday() + "',";
        }
        if (!"".equals(model.getHobby())) {
            sql += "`hobby` = '" + model.getHobby() + "', ";
        }
        if (!"".equals(model.getSex())) {
            sql += "`sex` = '" + model.getSex() + "', ";
        }
        sql += "`update_time` = now(), " +
                " `remark` = '' " +
                "WHERE (`id` = '" + model.getId() + "');\n";
        return JDBC.update(sql);
    }

    @Override
    public int password(UserModel model) {
        String sql = "update user set password='" + model.getPassword() + "' where id=" + model.getId();
        return JDBC.update(sql);
    }

    @Override
    public UserModel findByUsernameAndPassword(UserModel model) {
        String sql = "select * from user where username ='" + model.getName() + "' and password='" + model.getPassword() + "'";
        ;
        UserModel userModel = null;
        ResultSet resultSet = JDBC.query(sql);
        try {
            while (resultSet.next()) {
                userModel = new UserModel();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }

    @Override
    public int enable(UserModel model) {
        String sql = "update user set enable=enable^1 where id=" + model.getId();
        return JDBC.update(sql);
    }
}
