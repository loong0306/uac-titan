package me.uac.enums;


/**
 * <p>Title: UacExceptionEnums. </p>
 * <p>Description 用户账户中心错误代码 </p>
 * @author dragon
 * @date 2018/3/30 下午10:26
 */
public enum UacExceptionEnums {

    UAC_TOKEN_ERROR_10001(10001, "UAC_TOKEN_ERROR_10001", "获取TOKEN出错"),
    UAC_TOKEN_ERROR_10002(10002, "UAC_TOKEN_ERROR_10002", "Redis获取TOKEN为空"),
    UAC_LOGIN_ERROR_10003(10003, "UAC_LOGIN_ERROR_10003", "获取用户信息为空"),
    UAC_LOGIN_ERROR_10004(10004, "UAC_LOGIN_ERROR_10004", "用户名为空"),
    UAC_LOGIN_ERROR_10005(10005, "UAC_LOGIN_ERROR_10005", "用户密码为空");

    private int code;
    private String msg;
    private String errorMsg;

    UacExceptionEnums(String msg) {
        this.msg = msg;
    }

    UacExceptionEnums(int code, String msg, String errorMsg) {
        this.code = code;
        this.msg = msg;
        this.errorMsg = errorMsg;
    }

    public String msg() {
        return msg;
    }

    public String code() {
        return this.name();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
