package com.github.yc_39.entity;


/**
 * 接口返回对象
 */
public class ResultData {

    /**
     * 成功
     */
    public static final String SUCCESS = "0";
    public static final String MSG_SUCCESS = "成功";
    /**
     * 错误
     */
    public static final String ERROR = "1";
    public static final String MSG_ERROR = "失败";
    /**
     * 未登陆
     */
    public static final String UNLOGIN = "2";
    public static final String MSG_UNLOGIN = "未登陆";

    public ResultData() {
        this.code = SUCCESS;
        this.msg = MSG_SUCCESS;
    }

    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
