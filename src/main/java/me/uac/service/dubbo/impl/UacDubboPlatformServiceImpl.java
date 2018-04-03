package me.uac.service.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import me.uac.service.dubbo.UacDubboPlatformService;

/**
 * <p>Title: UacDubboPlatformService. </p>
 * <p>Description 用户账户中心，平台信息Dubbo服务 </p>
 * @author dragon
 * @date 2018/4/2 下午2:23
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
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
