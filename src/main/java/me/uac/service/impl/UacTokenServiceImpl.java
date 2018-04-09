package me.uac.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import me.dragon.exception.BusinessException;
import me.dragon.model.dto.AuthUserDTO;
import me.dragon.utils.CommUsualUtils;
import me.dragon.utils.JacksonUtil;
import me.dragon.wrap.WrapMapper;
import me.dragon.wrap.Wrapper;
import me.uac.constant.UacTokenConstants;
import me.uac.enums.UacExceptionEnums;
import me.uac.service.UacTokenService;
import me.uac.utils.PasswordUtils;
import me.uac.utils.RandomUtil;
import me.uac.utils.RedisOperationUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;

/**
 * <p>Title: UacTokenServiceImpl. </p>
 * <p>Description 用户账户TokenService </p>
 * @author dragon
 * @date 2018/3/30 下午10:18
 */
@Slf4j
@Service
public class UacTokenServiceImpl implements UacTokenService {

    /**
     * Token过期剩余时间,根据此变量设置续租
     */
    @Value("${uac.auth.expiredRemainMinutes}")
    private Integer expiredRemainMinutes;
    /**
     * Token过期分钟数
     */
    @Value("${uac.auth.expiredMinutes}")
    private Integer expiredMinutes;
    /**
     * DOMAIN
     */
    @Value("${uac.auth.domain}")
    private String domain;
    @Resource
    private StringRedisTemplate rt;
    @Resource
    private RedisOperationUtils redisOperationUtils;

    /**
     * <p>Title: getSecretToken. </p>
     * <p>获取加密TOKEN </p>
     * @author dragon
     * @date 2018/3/30 下午10:17
     * @return Wrapper.String
     */
    @Override
    public Wrapper<String> getSecretToken() {
        log.info("获取加密TOKEN");
        String secretToken;
        try {
            secretToken = RandomUtil.createComplexCode(16);
            log.info("获取加密TOKEN, 成功 secToken = {}", secretToken);
        }catch (BusinessException ex) {
            log.error("获取加密TOKEN, 出现异常={}", ex.getMessage(), ex);
            return WrapMapper.wrap(Wrapper.ERROR_CODE, UacExceptionEnums.UAC_TOKEN_ERROR_10001.getMsg());
        }catch (Exception ex) {
            log.error("获取加密TOKEN, 出现异常={}", ex.getMessage(), ex);
            return WrapMapper.error();
        }
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "获取加密TOKEN成功", secretToken);
    }

    /**
     * <p>Title: getAuthUserByToken. </p>
     * <p>通过JWT_TOKEN获取用户全局通用信息 </p>
     * @param response
     * @param token
     * @author dragon
     * @date 2018/4/9 下午4:54
     * @return AuthUserDTO
     */
    @Override
    public AuthUserDTO getAuthUserByToken(HttpServletResponse response, String token) {
        AuthUserDTO authUserDTO;
        try {
            String tokenKey = getPrivateKey();
            final Claims claims = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(token).getBody();
            Date expiration = claims.getExpiration();
            String authResDtoString = (String) claims.get("authUserDTO");
            DateTime nowDate = new DateTime();
            DateTime expireDate = new DateTime(expiration);
            authUserDTO = JacksonUtil.parseJson(authResDtoString, AuthUserDTO.class);
            if (nowDate.plusMinutes(expiredRemainMinutes).isAfter(expireDate)) {
                // 校验是否需要TOKEN续租
                String expiredToken = this.encodeToken(authUserDTO);
                response.setHeader("expiredToken", expiredToken);
                // 设置COOKIE
                Cookie loginCookie = new Cookie(UacTokenConstants.UAC_TOKEN, URLEncoder.encode(token, "UTF-8"));
                loginCookie.setDomain(domain);
                loginCookie.setPath("/");
                response.addCookie(loginCookie);
            }
        } catch (Exception ex) {
            log.error("通过JWT_TOKEN获取用户全局通用信息, 出现异常={}", ex.getMessage(), ex);
            throw new BusinessException(UacExceptionEnums.UAC_TOKEN_ERROR_10010.code(), UacExceptionEnums.UAC_TOKEN_ERROR_10010.msg());
        }
        return authUserDTO;
    }

    @Override
    public String encodeToken(AuthUserDTO authUserDTO) {
        log.info("开始生成TOKEN。用户 = {}", authUserDTO.toString());
        String token;
        try {
            String authUserDTOString = JacksonUtil.toJson(authUserDTO);
            DateTime nowDate = new DateTime();
            DateTime laterDate = nowDate.plusMinutes(expiredMinutes);
            String tokenKey = getPrivateKey();
            token = Jwts.builder()
                    .setSubject(authUserDTO.getUserId())
                    .claim("authUserDTO", authUserDTOString)
                    .setIssuedAt(nowDate.toDate())
                    .setExpiration(laterDate.toDate())
                    .signWith(SignatureAlgorithm.HS256, tokenKey)
                    .compact();
        } catch (Exception e) {
            log.error("用户登录，生成TOKEN出现异常，e = {}", e);
            throw new BusinessException(UacExceptionEnums.UAC_TOKEN_ERROR_10011.code(), UacExceptionEnums.UAC_TOKEN_ERROR_10011.msg());
        }
        return token;
    }

    /**
     * 获取Token密钥
     */
    public String getPrivateKey() throws Exception {
        ValueOperations<String, String> ops = rt.opsForValue();
        String decodeKey = ops.get(UacTokenConstants.TOKEN_KEY);
        if (CommUsualUtils.isNull(decodeKey)) {
            decodeKey = CommUsualUtils.getUUID();
            setTokenKey(decodeKey);
        }
        return decodeKey;
    }

    /**
     * <p>Title: setTokenKey. </p>
     * <p>设置TOKEN_KEY </p>
     * @param tokenKey
     * @author dragon
     * @date 2018/4/4 上午11:41
     */
    private void setTokenKey(String tokenKey) throws Exception {
        if (CommUsualUtils.isNull(tokenKey)) {
            log.error("TokenKey为空");
            throw new BusinessException("TokenKey为空");
        }
        redisOperationUtils.set(UacTokenConstants.TOKEN_KEY, PasswordUtils.encodeByAES(tokenKey));
    }

}
