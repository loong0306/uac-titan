package me.uac.model.dto.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: UacLoginReqDTO. </p>
 * <p>Description 用户登录DTO </p>
 * @author dragon
 * @date 2018/4/4 上午10:58
 */
@Data
@ApiModel(value = "用户登录请求DTO")
public class UacLoginReqDTO implements Serializable {
    private static final long serialVersionUID = -8401025340397650871L;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String loginPwd;

    /**
     * 系统标识
     */
    @ApiModelProperty(value = "系统标识")
    private String systemId;

    /**
     * 登录秘钥
     */
    @ApiModelProperty(value = "登录秘钥")
    private String secretToken;

    /**
     * token秘钥
     */
    @ApiModelProperty(value = "token秘钥")
    private String tokenKey;
}
