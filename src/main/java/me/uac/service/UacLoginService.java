package me.uac.service;

import me.uac.model.dto.req.UacLoginReqDTO;
import me.uac.model.dto.res.UacLoginResDTO;

/**
 * <p>Title: UacLoginService. </p>
 * <p>Description 用户账户中心登录Service </p>
 * @author dragon
 * @date 2018/4/4 下午3:40
 */
public interface UacLoginService {
    /**
     * <p>Title: doLogin. </p>
     * <p>用户登录 </p>
     * @param uacLoginReqDTO
     * @author dragon
     * @date 2018/4/4 下午3:47
     * @return me.uac.model.dto.res.UacLoginResDTO
     */
    UacLoginResDTO doLogin(UacLoginReqDTO uacLoginReqDTO);
}
