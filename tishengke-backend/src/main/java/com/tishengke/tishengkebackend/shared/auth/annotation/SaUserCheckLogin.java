package com.tishengke.tishengkebackend.shared.auth.annotation;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.tishengke.tishengkebackend.shared.auth.StpKit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义Space命名空间登录检查
 *
 * @author Dmz Email:  * @since 2025/06/29 16:39
 */
@SaCheckLogin(type = StpKit.USER_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SaUserCheckLogin {
}
