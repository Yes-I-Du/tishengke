package com.tishengke.tishengkebackend.infrastructure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验
 *
 * @author Dmz Email:  * @since 2025/06/22 19:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    /**
     * 必须有某个角色
     *
     * @return
     */
    String mustRole()

        default "";
}
