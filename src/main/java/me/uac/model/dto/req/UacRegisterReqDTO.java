package me.uac.model.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * <p>Title: UacRegisterReqDTO. </p>
 * <p>Description 用户注册请求DTO </p>
 * @author dragon
 * @date 2018/4/8 上午10:49
 */
@Data
@ApiModel(value = "用户注册请求DTO")
public class UacRegisterReqDTO implements Serializable {
    private static final long serialVersionUID = -7725863748385936973L;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    @NotBlank(message = "用户名不能为空")
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String loginPwd;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    @NotBlank(message = "确认密码不能为空")
    private String confirmPwd;

    /**
     * 系统标识
     */
    @ApiModelProperty(value = "系统标识")
    @NotBlank(message = "系统标识不能为空")
    private String systemId;
}
