package com.cn.wanxi.model;

public class NewsModel extends BaseModel {
    private String imgHref;
    private String content;
    private String newsName;
    private Integer newsId;
    private Integer isShow;
    private Integer isRecommend;
    private Integer fenLeiId;
    private String fenLeiName;


    public Integer getFenLeiId() {
        return fenLeiId;
    }

    public void setFenLeiId(Integer fenLeiId) {
        this.fenLeiId = fenLeiId;
    }

    public String getFenLeiName() {
        return fenLeiName;
    }

    public void setFenLeiName(String fenLeiName) {
        this.fenLeiName = fenLeiName;
    }

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

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
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
}
