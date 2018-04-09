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
 * <p>Title: UacUserRegisterLog. </p>
 * <p>Description 用户注册日志 </p>
 * @author dragon
 * @date 2018/4/9 上午10:28
 */
@Data
@Table(name = "uac_user_register_log")
public class UacUserRegisterLog implements Serializable {
    private static final long serialVersionUID = -6160891542771833676L;

    /**
     * 用户注册日志ID
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @ApiModelProperty(value = "用户注册日志ID")
    private String id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @Column(name = "user_id")
    private String userId;

    /**
     * 系统标识
     */
    @ApiModelProperty(value = "系统标识")
    @Column(name = "system_id")
    private String systemId;

    /**
     * 注册IP地址
     */
    @ApiModelProperty(value = "注册IP地址")
    @Column(name = "register_ip")
    private String registerIp;

    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间")
    @Column(name = "register_time")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registerTime;
}
