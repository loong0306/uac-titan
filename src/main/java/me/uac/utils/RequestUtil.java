package me.uac.utils;

import me.dragon.utils.CommUsualUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: RequestUtil</p>
 * <p>Description 获取请求参数</p>
 */
public class RequestUtil {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request){
        String remoteAddress = request.getHeader("X-Real-IP");
        if (!CommUsualUtils.isSEmptyOrNull(remoteAddress)) {
            remoteAddress = request.getHeader("X-Forwarded-For");
        } else if (!CommUsualUtils.isSEmptyOrNull(remoteAddress)) {
            remoteAddress = request.getHeader("Proxy-Client-IP");
        } else if (!CommUsualUtils.isSEmptyOrNull(remoteAddress)) {
            remoteAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(CommUsualUtils.isEmpty(remoteAddress)){
            remoteAddress = request.getRemoteAddr();
            if(!CommUsualUtils.isSEmptyOrNull(remoteAddress) && "0:0:0:0:0:0:0:1".equals(remoteAddress)){
                remoteAddress = "127.0.0.1";
            }else {
                remoteAddress = request.getRemoteAddr();
            }
        }
        return remoteAddress;
    }
}
