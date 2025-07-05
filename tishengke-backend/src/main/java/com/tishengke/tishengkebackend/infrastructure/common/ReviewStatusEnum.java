package com.tishengke.tishengkebackend.infrastructure.common;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

/**
 * 审核状态枚举类
 *
 * @author Dmz Email:  * @since 2025/05/24 12:36
 */
@Getter
public enum ReviewStatusEnum {
    REVIEWING("审核中", 0), PASS("审核通过", 1), REJECT("审核拒绝", 2);

    private final String text;

    private final int value;

    ReviewStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据value获取枚举
     *
     * @param value
     * @return
     */
    public static ReviewStatusEnum getReviewStatusEnumByValue(Integer value) {
        // 如果value为空或者value==null,返回null
        if (ObjUtil.isNull(value) || ObjUtil.isEmpty(value)) {
            return null;
        }
        for (ReviewStatusEnum reviewStatusEnume : ReviewStatusEnum.values()) {
            if (reviewStatusEnume.value == value) {
                return reviewStatusEnume;
            }
        }
        return null;
    }
}
