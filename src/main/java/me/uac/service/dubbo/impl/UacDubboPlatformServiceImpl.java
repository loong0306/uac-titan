package me.uac.service.dubbo.impl;

import me.uac.service.dubbo.UacDubboPlatformService;
import org.springframework.stereotype.Service;

/**
 * <p>Title: UacDubboPlatformService. </p>
 * <p>Description 用户账户中心，平台信息Dubbo服务 </p>
 * @author dragon
 * @date 2018/4/2 下午2:23
 */
@Service
public class UacDubboPlatformServiceImpl implements UacDubboPlatformService {

    /**
     * <p>Title: getUacPlatformName. </p>
     * <p>获取Uac平台名称 </p>
     * @author dragon
     * @date 2018/4/2 下午2:35
     * @return java.lang.String
     */
    @Override
    public String getUacPlatformName() {
        return "UAC-TITAN";
    }
}
