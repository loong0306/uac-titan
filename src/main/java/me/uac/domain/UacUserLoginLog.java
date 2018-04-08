package me.uac.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: UacUserLoginLog. </p>
 * 用户登录系统日志
 * @author dragon
 * @date 2018/4/8 上午11:50
 */
@Data
@Table(name = "uac_user_login_log")
public class UacUserLoginLog implements Serializable {
    private static final long serialVersionUID = -1276873517391073323L;

    /**
     * 用户登录日志ID
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @ApiModelProperty(value = "用户登录日志ID")
    private String id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @Column(name = "login_name")
    private String loginName;

    /**
     * 系统标识
     */
    @ApiModelProperty(value = "系统标识")
    @Column(name = "system_id")
    private String systemId;

    /**
     * 登录IP地址
     */
    @ApiModelProperty(value = "登录IP地址")
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    @Column(name = "login_time")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;
}
