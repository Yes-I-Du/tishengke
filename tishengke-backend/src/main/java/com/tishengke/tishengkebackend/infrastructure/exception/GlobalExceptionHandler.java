package com.tishengke.tishengkebackend.infrastructure.exception;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.tishengke.tishengkebackend.infrastructure.common.BaseResponse;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author dmz xxx@163.com
 * @version 2025/6/22 18:45
 * @since JDK17
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(RespCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(NotLoginException.class)
    public BaseResponse<?> notLoginException(NotLoginException e) {
        log.error("NotLoginException", e);
        return ResultUtils.error(RespCode.NOT_LOGIN_ERROR, "用户未登录");
    }

    @ExceptionHandler(NotPermissionException.class)
    public BaseResponse<?> notPermissionExceptionHandler(NotPermissionException e) {
        log.error("NotPermissionException", e);
        return ResultUtils.error(RespCode.NO_AUTH_ERROR, "该登录用户权限不足");
    }

    @ExceptionHandler(NotRoleException.class)
    public BaseResponse<?> notRoleExceptionHandler(NotRoleException e) {
        log.error("NotRoleException", e);
        return ResultUtils.error(RespCode.NO_AUTH_ERROR, "该登录用户权限不足");
    }

}