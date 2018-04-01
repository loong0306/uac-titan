package me.uac.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.dragon.exception.BusinessException;
import me.dragon.wrap.WrapMapper;
import me.dragon.wrap.Wrapper;
import me.uac.enums.UacExceptionEnums;
import me.uac.service.UacTokenService;
import me.uac.utils.RandomUtil;
import org.springframework.stereotype.Service;

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
        return  WrapMapper.wrap(Wrapper.SUCCESS_CODE, "获取加密TOKEN成功", secretToken);
    }

}
