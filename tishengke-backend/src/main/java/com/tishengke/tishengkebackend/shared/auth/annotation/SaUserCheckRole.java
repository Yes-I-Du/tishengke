package com.tishengke.tishengkebackend.shared.auth.annotation;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import com.tishengke.tishengkebackend.shared.auth.StpKit;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义Space账号体系下角色检查
 *
 * @author Dmz Email:  * @since 2025/06/29 16:41
 */
@SaCheckRole(type = StpKit.USER_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SaUserCheckRole {
    /**
     * 需要校验的角色标识
     * @return 需要校验的角色标识
     */
    @AliasFor(annotation = SaCheckRole.class)
    String [] value() default {};

    /**
     * 验证模式：AND | OR，默认AND
     * @return 验证模式
     */
    @AliasFor(annotation = SaCheckRole.class)
    SaMode mode() default SaMode.AND;
}
