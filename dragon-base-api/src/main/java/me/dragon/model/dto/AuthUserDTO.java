package me.dragon.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: AuthUserDTO. </p>
 * <p>Description 用户全局信息DTO </p>
 * @author dragon
 * @date 2018/4/9 下午4:41
 */
@Data
public class AuthUserDTO implements Serializable {
    private static final long serialVersionUID = -7801329721725356863L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;
}
