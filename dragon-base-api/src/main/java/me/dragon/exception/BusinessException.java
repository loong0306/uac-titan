package me.dragon.exception;

import lombok.Data;

/**
 * <p>Title: BusinessException </p>
 * <p>Description 业务异常 </p>
 * @author dragon
 */
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -5498349181411634088L;

    /**
     * 异常码
     */
    private String code;

    /**
     * 异常描述
     */
    private String msg;

    /**
     * 构造异常
     * @param code 异常码
     * @param msg 异常描述
     */
    public BusinessException(String code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造异常
     * @param code 异常码
     * @param msg 异常描述
     */
    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

}
