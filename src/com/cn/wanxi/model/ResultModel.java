package com.cn.wanxi.model;

public class ResultModel {
    //    编码：0表示成功，1表示失败等
    private Integer code;
    //    返回的信息数据
    private String msg;
    //    影响的条数
    private Integer count;
    //    查询出的数据
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 通过影响的条数得到返回结果
     * 一般用于跟新数据或者查询总条数
     *
     * @param count
     * @return
     */
    public static ResultModel getResultModel(int count) {
//        ResultModel resultModel = new ResultModel();
//        resultModel.setCount(count);
//        resultModel.setMsg("");
//        resultModel.setCode(0);
//        return resultModel;
        return getResultModel(count, 0, "", null);
    }

    /**
     * 通过查询的结果得到返回结果
     * 一般用于根据id查询数据
     *
     * @param data
     * @return
     */
    public static ResultModel getResultModel(Object data) {
//        ResultModel resultModel = new ResultModel();
//        resultModel.setCount(1);
//        resultModel.setMsg("");
//        resultModel.setCode(0);
//        resultModel.setData(data);
//        return resultModel;
        return getResultModel(0, 0, "", data);
    }

    /**
     * 通过影响的条数和查询的结果得到返回结果
     * 一般用于查询所有
     *
     * @param count
     * @param data
     * @return
     */
    public static ResultModel getResultModel(int count, Object data) {
//        ResultModel resultModel = new ResultModel();
//        resultModel.setCount(count);
//        resultModel.setMsg("");
//        resultModel.setCode(0);
//        resultModel.setData(data);
//        return resultModel;
        return getResultModel(count, 0, "", data);
    }

    public static ResultModel getResultModel(String msg) {

        return getResultModel(0, 0, msg, null);
    }

    /**
     * 根据所有属性得到返回结果
     *
     * @param count
     * @param code
     * @param msg
     * @param data
     * @return
     */
    private static ResultModel getResultModel(int count, int code, String msg, Object data) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCount(count);
        resultModel.setMsg(msg);
        resultModel.setCode(code);
        resultModel.setData(data);
        return resultModel;
    }
}
