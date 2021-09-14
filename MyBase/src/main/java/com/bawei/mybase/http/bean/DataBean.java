package com.bawei.mybase.http.bean;

/**
 * +----------------------------------------------------------------------
 * | 项   目: MineStudy
 * +----------------------------------------------------------------------
 * | 包   名: com.bawei.mybase.http.bean
 * +----------------------------------------------------------------------
 * | 类   名: DataBean
 * +----------------------------------------------------------------------
 * | 时　　间: 2021/9/12 19:06
 * +----------------------------------------------------------------------
 * | 代码创建: 王益德
 * +----------------------------------------------------------------------
 * | 版本信息: V1.0.0
 * +----------------------------------------------------------------------
 * | 功能描述:
 * +----------------------------------------------------------------------
 **/
public class DataBean<T> {

    private int Code;
    private String msg;
    private T data;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DataBean(int code, String msg, T data) {
        Code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataBean() {
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "Code=" + Code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
