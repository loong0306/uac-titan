package me.uac.service;


import me.dragon.wrap.Wrapper;

/**
 * <p>Title: UacTokenService. </p>
 * <p>Description 用户账户TokenService </p>
 * @author dragon
 * @date 2018/3/30 下午10:13
 */
public interface UacTokenService {

    /**
     * <p>Title: getSecretToken. </p>
     * <p>获取加密TOKEN </p>
     * @author dragon
     * @date 2018/3/30 下午10:17
     * @return Wrapper.String
     */
    Wrapper<String> getSecretToken();
}
