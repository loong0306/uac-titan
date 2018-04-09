package me.uac.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import me.dragon.exception.BusinessException;
import me.dragon.model.dto.AuthUserDTO;
import me.dragon.utils.CommUsualUtils;
import me.dragon.utils.ThreadLocalMap;
import me.uac.constant.UacTokenConstants;
import me.uac.enums.UacExceptionEnums;
import me.uac.service.UacTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * <p>Title: AuthInterceptor. </p>
 * <p>Description 系统权限拦截器 </p>
 * @author dragon
 * @date 2018/4/9 下午3:23
 */
@Service
public class AuthInterceptor implements HandlerInterceptor {

    private transient static final String AUTH = "/auth/";
    private transient static final String OPTIONS = "OPTIONS";
    private static final String ERRORMSG = "{\"code\":403 ,\"message\" :\"NOT AUTHORIZATION\"}";
    private static final String BEARER = "Bearer ";

    @Value("${uac.cookie.token}")
    private String tokenKey;

    @Resource
    private UacTokenService uacTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 登录注册跳过拦截
        if (request.getRequestURL().toString().contains(AUTH)) {
            return true;
        }
        // 预请求跳过拦截，判断跨域
        if (request.getMethod().toUpperCase().equals(OPTIONS)) {
            return true;
        }
        try {
            String authHeader = request.getHeader("Authorization");
            if (CommUsualUtils.isSEmptyOrNull(authHeader)
                    || !authHeader.startsWith(BEARER)
                    || (!CommUsualUtils.isSEmptyOrNull(authHeader) && authHeader.length() < 20)) {
                // 获取COOKIE；校验COOKIE是否允许通过
                Cookie[] cookies = request.getCookies();
                if (!CommUsualUtils.isOEmptyOrNull(cookies)) {
                    for (Cookie cookie : cookies) {
                        if (tokenKey.equals(cookie.getName())) {
                            authHeader = cookie.getValue();
                            break;
                        }
                    }
                } else {
                    throw new BusinessException(UacExceptionEnums.UAC_TOKEN_ERROR_10008.code(), UacExceptionEnums.UAC_TOKEN_ERROR_10008.msg());
                }
            }
            final String token = authHeader.substring(7);
            ThreadLocalMap.put(UacTokenConstants.JWT_TOKEN, token);
            AuthUserDTO authUserDTO = uacTokenService.getAuthUserByToken(response, token);
            Boolean isAllow = validateAndSettingValidateCode(authUserDTO);
            if (!isAllow) {
                throw new BusinessException(UacExceptionEnums.UAC_TOKEN_ERROR_10009.code(), UacExceptionEnums.UAC_TOKEN_ERROR_10009.msg());
            }
            ThreadLocalMap.put(UacTokenConstants.AUTH_USER_DTO, authUserDTO);
        } catch (Exception e) {
            handleException(request, response, ERRORMSG);
            return false;
        }
        return true;
    }

    /**
     * <p>Title: validateAndSettingValidateCode. </p>
     * <p>校验用户是否为机器攻击性调用 </p>
     * @param authUserDTO
     * @author dragon
     * @date 2018/4/9 下午4:50
     * @return java.lang.Boolean
     */
    private Boolean validateAndSettingValidateCode(AuthUserDTO authUserDTO) {
        return true;
    }

    private void handleException(HttpServletRequest request, HttpServletResponse res, String msg) throws IOException {
        request.getSession().removeAttribute("token");
        res.resetBuffer();
        StringBuffer allowOriginUrl = new StringBuffer().append("*");
        res.setHeader("Access-Control-Allow-Origin", allowOriginUrl.toString());
        res.setHeader("Access-Control-Allow-Credentials","true");
        res.setHeader("timeOut","true");
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(msg);
        res.flushBuffer();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
