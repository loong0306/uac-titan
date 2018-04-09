package me.uac.service;


import me.dragon.model.dto.AuthUserDTO;
import me.dragon.wrap.Wrapper;

import javax.servlet.http.HttpServletResponse;

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

    /**
     * <p>Title: getAuthUserByToken. </p>
     * <p>通过JWT_TOKEN获取用户全局通用信息 </p>
     * @param response
     * @param token
     * @author dragon
     * @date 2018/4/9 下午4:54
     * @return AuthUserDTO
     */
    AuthUserDTO getAuthUserByToken(HttpServletResponse response, String token);

    /**
     * <p>Title: encodeToken. </p>
     * <p>成功登录后，获取TOKEN </p>
     * @param authUserDTO
     * @author dragon
     * @date 2018/4/9 下午5:37
     * @return java.lang.String TOKEN
     */
    String encodeToken(AuthUserDTO authUserDTO);
}
