package me.uac.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.dragon.utils.CommUsualUtils;
import me.uac.domain.UacUser;
import me.uac.domain.UacUserLoginLog;
import me.uac.enums.UacExceptionEnums;
import me.uac.mapper.UacUserLoginLogMapper;
import me.uac.mapper.UacUserMapper;
import me.uac.model.dto.req.UacLoginReqDTO;
import me.uac.model.dto.res.UacLoginResDTO;
import me.uac.service.UacLoginService;
import me.uac.utils.AesUtil;
import me.uac.utils.CheckArgumentUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

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
    @Resource
    private UacUserLoginLogMapper uacUserLoginLogMapper;

    /**
     * <p>Title: doLogin. </p>
     * <p>用户登录 </p>
     * @param uacLoginReqDTO
     * @author dragon
     * @date 2018/4/4 下午3:47
     * @return me.uac.model.dto.res.UacLoginResDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
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
        if (!CommUsualUtils.isOEmptyOrNull(uacUser)) {
            String userId = uacUser.getId();
            String serialNo = uacUser.getSerialNo();
            loginName = uacUser.getLoginName();
            // 插入用户日志表
            insertUserLoginLog(userId, uacLoginReqDTO);
            // 返回数据，作为用户base数据
            UacLoginResDTO uacLoginResDTO = new UacLoginResDTO();
            uacLoginResDTO.setId(userId);
            uacLoginResDTO.setSerialNo(serialNo);
            uacLoginResDTO.setLoginName(loginName);
            return uacLoginResDTO;
        }
        return null;
    }

    /**
     * <p>Title: insertUserLoginLog. </p>
     * <p>插入用户日志表 </p>
     * @param userId
     * @param uacLoginReqDTO
     * @author dragon
     * @date 2018/4/8 下午3:01
     */
    private void insertUserLoginLog(String userId, UacLoginReqDTO uacLoginReqDTO) {
        UacUserLoginLog uacUserLoginLog = new UacUserLoginLog();
        uacUserLoginLog.setUserId(userId);
        uacUserLoginLog.setLoginName(uacLoginReqDTO.getLoginName());
        uacUserLoginLog.setLoginIp(uacLoginReqDTO.getIp());
        uacUserLoginLog.setSystemId(uacLoginReqDTO.getSystemId());
        uacUserLoginLog.setLoginTime(new Date());
        uacUserLoginLogMapper.insert(uacUserLoginLog);
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
        return uacUser;
    }
}
