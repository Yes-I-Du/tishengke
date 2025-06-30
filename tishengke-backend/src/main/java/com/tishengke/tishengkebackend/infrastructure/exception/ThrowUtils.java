package com.tishengke.tishengkebackend.infrastructure.exception;

import com.tishengke.tishengkebackend.infrastructure.common.RespCode;

/**
 * 自定义异常工具处理类
 *
 * @author dmz xxx@163.com
 * @version 2025/6/22 18:52
 * @since JDK17
 */
public class ThrowUtils {
    /**
     * 条件成立则抛出异常
     *
     * @param condition 判断条件
     * @param exception 异常
     */
    public static void throwIf(boolean condition, RuntimeException exception) {
        if (condition) {
            throw exception;
        }
    }

    /**
     * 条件成立则抛出异常
     *
     * @param condition 判断条件
     * @param respCode  状态码
     */
    public static void throwIf(boolean condition, RespCode respCode) {
        throwIf(condition, new BusinessException(respCode));
    }

    /**
     * 条件成立则抛出异常
     *
     * @param condition 判断条件
     * @param respCode  状态码
     * @param message   错误信息
     */
    public static void throwIf(boolean condition, RespCode respCode, String message) {
        throwIf(condition, new BusinessException(respCode, message));
    }
}