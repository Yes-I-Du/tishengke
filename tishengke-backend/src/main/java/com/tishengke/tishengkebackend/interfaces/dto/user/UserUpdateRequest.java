package com.tishengke.tishengkebackend.interfaces.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户管理-更新用户
 *
 * @author dmz xxx@163.com
 * @version 2025/5/19 22:00
 * @since JDK17
 */
@Data
public class UserUpdateRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /*用户ID*/
    private Long id;

    /*用户昵称*/
    private String userName;

    /*用户头像*/
    private String userAvatar;

    /*用户简介*/
    private String userProfile;

    /*用户角色*/
    private String userRole;
}

