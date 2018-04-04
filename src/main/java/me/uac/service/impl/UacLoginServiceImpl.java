package me.uac.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.dragon.utils.CommUsualUtils;
import me.uac.domain.UacUser;
import me.uac.enums.UacExceptionEnums;
import me.uac.mapper.UacUserMapper;
import me.uac.model.dto.req.UacLoginReqDTO;
import me.uac.model.dto.res.UacLoginResDTO;
import me.uac.service.UacLoginService;
import me.uac.utils.AesUtil;
import me.uac.utils.CheckArgumentUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>Title: UacLoginService. </p>
 * <p>Description 用户账户中心登录Service </p>
 * @author dragon
 * @date 2018/4/4 下午3:40
 */
@Slf4j
@Service
public class UacLoginServiceImpl implements UacLoginService {

    @Resource
    private UacUserMapper uacUserMapper;

    /**
     * <p>Title: doLogin. </p>
     * <p>用户登录 </p>
     * @param uacLoginReqDTO
     * @author dragon
     * @date 2018/4/4 下午3:47
     * @return me.uac.model.dto.res.UacLoginResDTO
     */
    @Override
    public UacLoginResDTO doLogin(UacLoginReqDTO uacLoginReqDTO) {
        log.info("用户登录，登录信息为 = {}", uacLoginReqDTO.toString());
        // 通过加密的用户信息进行解密
        String loginName = uacLoginReqDTO.getLoginName();
        String loginPwd = uacLoginReqDTO.getLoginPwd();
        String secretToken = uacLoginReqDTO.getSecretToken();
        loginName = AesUtil.decrypt(loginName, secretToken, false, secretToken);
        loginPwd = AesUtil.decrypt(loginPwd, secretToken, false, secretToken);
        UacUser uacUser = new UacUser();
        uacUser.setLoginName(loginName);
        uacUser.setLoginPwd(loginPwd);
        // 查询UAC数据库是否数据吻合
        uacUser = uacDoLogin(uacUser);
        return null;
    }

    /**
     * <p>Title: uacDoLogin. </p>
     * <p>校验登录信息 </p>
     * @param uacUser
     * @author dragon
     * @date 2018/4/4 下午5:56
     * @return
     */
    private UacUser uacDoLogin(UacUser uacUser) {
        log.info("用户登录：登录参数 = {}", uacUser.toString());
        CheckArgumentUtil.checkArgument(!CommUsualUtils.isNull(uacUser.getLoginName()), UacExceptionEnums.UAC_LOGIN_ERROR_10004);
        CheckArgumentUtil.checkArgument(!CommUsualUtils.isNull(uacUser.getLoginPwd()), UacExceptionEnums.UAC_LOGIN_ERROR_10005);
        uacUser = uacUserMapper.selectOne(uacUser);
        log.info("用户登录：获取登录信息 = {}", uacUser.toString());
        return null;
    }
}
