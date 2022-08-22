package com.cn.wanxi.model;

import java.math.BigDecimal;

/**
 * 每一个表，建议有哪些列？
 * 命名规则：一般情况下，数据不区分大小写，所如果有两个单词用下划线
 * market_price
 * java小驼峰命名
 * java              mysql
 * int                int
 * String              varchar  char text datetime
 * BigDecimal           decimal
 * Date                    datetime
 * boolean              tinyint
 * byte                  tinyint
 * short                    smallint
 * long                   bigint
 * float                  decimal(5,3) 5代表五个有效数字，3代表3位小数
 * mysql  没有布尔
 *
 */
public class ProductModel extends BaseModel {
    private String imgHref;
    private String content;
    private BigDecimal marketPrice;
    private BigDecimal normalPrice;
    private String FenLeiName;
    private Integer fenLeiId;
    private Integer isShow;
    private Integer isRecommend;

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getFenLeiName() {
        return FenLeiName;
    }

    public void setFenLeiName(String FenLeiName) {
        this.FenLeiName = FenLeiName;
    }

    public Integer getFenLeiId() {
        return fenLeiId;
    }

    public void setFenLeiId(Integer fenLeiId) {
        this.fenLeiId = fenLeiId;
    }

    public String getImgHref() {
        return imgHref;
    }

    public void setImgHref(String imgHref) {
        this.imgHref = imgHref;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(BigDecimal normalPrice) {
        this.normalPrice = normalPrice;
    }
}
