package me.uac.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.dragon.exception.BusinessException;
import me.uac.constant.SystemBaseConstants;
import me.uac.domain.UacUser;
import me.uac.enums.UacExceptionEnums;
import me.uac.mapper.UacUserMapper;
import me.uac.model.dto.req.UacRegisterReqDTO;
import me.uac.service.UacRegisterService;
import me.uac.utils.PasswordUtils;
import me.uac.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>Title: UacRegisterServiceImpl. </p>
 * <p>Description 用户账户中心注册Service </p>
 * @author dragon
 * @date 2018/4/8 下午4:03
 */
@Slf4j
@Service
public class UacRegisterServiceImpl implements UacRegisterService {

    @Resource
    private UacUserMapper uacUserMapper;
    @Resource
    private RedisUtils redisUtils;

    /**
     * <p>Title: doRegister. </p>
     * <p>Description 用户注册 </p>
     * @author dragon
     * @date 2018/4/8 下午4:35
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer doRegister(UacRegisterReqDTO uacRegisterReqDTO) {
        validateRegisterInfo(uacRegisterReqDTO);
        UacUser uacUser = new UacUser();
        uacUser.setLoginName(uacRegisterReqDTO.getLoginName());
        uacUser.setLoginPwd(PasswordUtils.encodeByAES(uacRegisterReqDTO.getLoginPwd()));
        uacUser.setSerialNo(redisUtils.getUserSerialNo());
        uacUser.setUserStatus(SystemBaseConstants.Y);
        uacUser.setCreatedTime(new Date());
        uacUser.setVersion(SystemBaseConstants.VERSION_INIT);
        uacUserMapper.insert(uacUser);
        return 1;
    }

    /**
     * <p>Title: UacRegisterServiceImpl. </p>
     * <p>Description 检验用户是否存在 </p>
     * @author dragon
     * @date 2018/4/8 下午4:35
     */
    private void validateRegisterInfo(UacRegisterReqDTO uacRegisterReqDTO) {
        String loginName = uacRegisterReqDTO.getLoginName();
        UacUser uacUser = new UacUser();
        uacUser.setLoginName(loginName);
        int userCount = uacUserMapper.selectCount(uacUser);
        if (userCount > 0) {
            throw new BusinessException(UacExceptionEnums.UAC_REGISTER_ERROR_10007.code(), UacExceptionEnums.UAC_REGISTER_ERROR_10007.msg());
        }
    }
}
