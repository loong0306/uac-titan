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
 * <p>Title: UacUser. </p>
 * @author dragon
 * @date 2018/4/4 下午6:35
 */
@Data
@Table(name = "uac_user")
public class UacUser implements Serializable {
    private static final long serialVersionUID = -8175467814147528969L;

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @ApiModelProperty(value = "用户ID")
    private String id;

    /**
     * 流水号
     */
    @Column(name = "serial_no")
    @ApiModelProperty(value = "流水号")
    private String serialNo;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    @Column(name = "user_status")
    private String userStatus;

    /**
     * 最后登录IP地址
     */
    @ApiModelProperty(value = "最后登录IP地址")
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间")
    @Column(name = "last_login_time")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "created_time")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Integer version;

}
