package me.uac.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: UacLoginTestBO. </p>
 * <p>Description 用户登录加密性测试BO </p>
 * @author dragon
 * @date 2018/4/9 上午11:40
 */
@Data
@ApiModel(value = "用户登录请求BO")
public class UacLoginTestBO implements Serializable {
    private static final long serialVersionUID = 6013800085428085705L;

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
     * 登录秘钥
     */
    @ApiModelProperty(value = "登录秘钥")
    private String secretToken;
}
