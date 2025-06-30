package com.tishengke.tishengkebackend.domain.user.valueObject;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

/**
 * 用户角色枚举类
 *
 * @author Dmz Email:  * @since 2025/05/18 20:22
 */
@Getter
public enum UserRoleEnum {

    USER("普通用户", "user"), ADMIN("管理员", "admin");

    private final String text;

    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据value获取枚举
     *
     * @param value
     * @return
     */
    public static UserRoleEnum getUserRoleEnumByValue(String value) {
        // 如果value为空或者value==null,返回null
        if (ObjUtil.isNull(value) || ObjUtil.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if (userRoleEnum.getValue().equals(value)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
