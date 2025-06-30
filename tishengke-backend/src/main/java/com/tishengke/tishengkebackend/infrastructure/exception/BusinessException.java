package com.tishengke.tishengkebackend.infrastructure.exception;

import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import lombok.Getter;

/**
 * 自定义业务异常
 *
 * @author dmz xxx@163.com
 * @version 2025/6/22 18:48
 * @since JDK17
 */
@Getter
public class BusinessException extends RuntimeException {
    /* 状态码 */
    private final Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(RespCode respCode) {
        super(respCode.getMessage());
        this.code = respCode.getCode();
    }

    public BusinessException(RespCode respCode, String message) {
        super(message);
        this.code = respCode.getCode();
    }
}