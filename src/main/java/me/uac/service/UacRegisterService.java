package me.uac.service;

import me.uac.model.dto.req.UacRegisterReqDTO;

/**
 * <p>Title: UacRegisterService. </p>
 * <p>Description 用户账户中心注册Service </p>
 * @author dragon
 * @date 2018/4/8 下午3:48
 */
public interface UacRegisterService {

    /**
     * <p>Title: doRegister. </p>
     * <p>用户注册Service </p>
     * @param uacRegisterReqDTO
     * @author dragon
     * @date 2018/4/8 下午3:48
     * @return java.lang.Integer
     */
    Integer doRegister(UacRegisterReqDTO uacRegisterReqDTO);
}
