package com.cn.wanxi.dao.impl;

import com.cn.wanxi.dao.ProductDao;
import com.cn.wanxi.model.ProductModel;
import com.cn.wanxi.util.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements ProductDao {
    @Override
    public int add(ProductModel model) {
        String sql = "INSERT INTO `product` (" +
                "name, normal_price, create_time," +
                "is_show, img_href, content, " +
                "update_time, remark, enable, " +
                "FenLei_id) values(" +
                "'" + model.getName() + "', '" + model.getNormalPrice() + "', now(), " +
                "'" + model.getIsShow() + "', '" + model.getImgHref() + "', '" + model.getContent() + "'," +
                "'now()', '" + "'" + model.getRemark() + "', '" +model.getEnable() + "', " +
                "'" + model.getFenLeiId() + "',);\n";
        return JDBC.update(sql);
    }

    @Override
    public int delete(ProductModel model) {
        String sql = "delete from product where id=" + model.getId();
        return JDBC.update(sql);
    }

    @Override
    public int update(ProductModel model) {
        String sql = "UPDATE `product` SET ";
        if (!"".equals(model.getName())) {
            sql += "`name` = '" + model.getName() + "',";
        }
        if (!"".equals(model.getNormalPrice())) {
            sql += "`normal_price` = '" + model.getNormalPrice() + "',";
        }
        if (!"".equals(model.getContent())) {
            sql += "`content` = '" + model.getContent() + "',";
        }
        if (!"".equals(model.getIsShow())) {
            sql += "`is_show` = '" + model.getIsShow() + "',";
        }
        if (!"".equals(model.getImgHref())) {
            sql += "`img_href` = '" + model.getImgHref() + "',";
        }
        if (!"".equals(model.getFenLeiId())) {
            sql += "`FenLei_id` = '" + model.getFenLeiId() + "',";
        }

        sql += "`update_time` = now(), " +
                " `remark` = '' " +
                "WHERE (`id` = '" + model.getId() + "');\n";
        return JDBC.update(sql);
    }

    /**
     * 应该将所有的菜系和产品全部查询出来，如果产品没有关联菜系，则用null替换
     *
     * @param model
     * @return
     */
    @Override
    public List<ProductModel> findAll(ProductModel model) {
        String sql = "select * from " +
                " (select p.*,c.name FenLeiName from product p" +
                " left join FenLei c " +
                " on p.FenLei_id=c.id)  temp  where true ";
        sql += getSql(model);
        sql += " order by id desc ";
        sql += " limit " + (model.getPage() - 1) * model.getLimit() + "," + model.getLimit() + ";";
        return getList(sql);
    }

    private List<ProductModel> getList(String sql) {
        ResultSet resultSet = JDBC.query(sql);
        List<ProductModel> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                ProductModel model = new ProductModel();
                model.setId(resultSet.getInt("id"));
                model.setName(resultSet.getString("name"));
                model.setNormalPrice(resultSet.getBigDecimal("normal_price"));
                model.setMarketPrice(resultSet.getBigDecimal("price"));
                model.setImgHref(resultSet.getString("img_href"));
                try {
                    model.setFenLeiName(resultSet.getString("FenLeiName"));
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

    private String getSql(ProductModel model) {
        String sql = "";
        if (!"".equals(model.getName())) {
            sql += " and name like '%" + model.getName() + "%'";
        }
        return sql;
    }

    @Override
    public ProductModel findById(ProductModel model) {
        String sql = "select * from product where id =" + model.getId();
        List<ProductModel> list = getList(sql);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public int getCount(ProductModel model) {
        String sql = "select count(*) count from " +
                " (select p.*,c.name FenLeiName from product p" +
                " left join FenLei c " +
                " on p.FenLei_id=c.id)  temp where true ";
        sql += getSql(model);
        return JDBC.getCount(sql);
    }

    @Override
    public int enable(ProductModel model) {
        String sql = " update product set " +
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
