package com.tishengke.tishengkebackend.interfaces.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 脱敏用户信息
 *
 * @author dmz xxx@163.com
 * @version 2025/5/19 22:05
 * @since JDK17
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /*用户ID*/
    private Long id;

    /*用户账号*/
    private String userAccount;

    /*用户昵称*/
    private String userName;

    /*用户头像*/
    private String userAvatar;

    /*用户简介*/
    private String userProfile;

    /*用户角色*/
    private String userRole;

    /*创建时间*/
    private Date createTime;
}

