package me.uac.enums;


/**
 * <p>Title: UacExceptionEnums. </p>
 * <p>Description 用户账户中心错误代码 </p>
 * @author dragon
 * @date 2018/3/30 下午10:26
 */
public enum UacExceptionEnums {

    // 获取TOKEN出错
    UAC_TOKEN_ERROR_10001(10001, "UAC_TOKEN_ERROR_10001")
    ;

    private int code;
    private String msg;

    UacExceptionEnums(String msg) {
        this.msg = msg;
    }

    UacExceptionEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
