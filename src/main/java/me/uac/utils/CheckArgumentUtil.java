package me.uac.utils;

import me.dragon.exception.BusinessException;
import me.uac.enums.UacExceptionEnums;

/**
 * <p>Title: CheckArgumentUtil. </p>
 * <p>Description 参数校验：业务异常 </p>
 * @author dragon
 * @date 2018/4/4 下午6:53
 */
public class CheckArgumentUtil {
    /**
     * 校验
     * @param result 正确结果
     * @param message 异常信息
     */
    public static void checkArgument(boolean result, String message){
        if(!result){
            throw new BusinessException(message);
        }
    }

    /**
     * 校验
     * @param result 正确结果
     * @param message 异常信息
     */
    public static void checkArgument(boolean result, UacExceptionEnums message){
        if(!result){
            throw new BusinessException(message.code(), message.msg());
        }
    }
}
