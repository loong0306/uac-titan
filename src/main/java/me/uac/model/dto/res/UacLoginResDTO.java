package me.uac.model.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Column;
import java.io.Serializable;

/**
 * <p>Title: UacLoginResDTO. </p>
 * <p>Description 用户账户中心登录响应DTO </p>
 * @author dragon
 * @date 2018/4/4 下午3:42
 */
@Data
public class UacLoginResDTO implements Serializable {
    private static final long serialVersionUID = -7169690337326094514L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String id;

    /**
     * 业务号
     */
    @ApiModelProperty(value = "业务号")
    private String serialNo;

    /**
     * 用户名
     */
    @Column(name = "login_name")
    private String loginName;
}
