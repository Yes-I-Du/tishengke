package com.tishengke.tishengkebackend.shared.auth;

import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.stereotype.Component;

/**
 * 自定义权限加载接口实现类 获取当前账号权限码集合

 * Sa-Token多账号认证体系(Kit模式)
 * StpLogic 门面类，管理项目中所有的 StpLogic 账号体系
 *
 * @author dmz xxx@163.com
 * @version 2025/6/23 17:22
 * @since JDK17
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpKit {
    public static final String USER_TYPE = "user";
    /**
     * 默认原生会话对象
     */
    public static final StpLogic DEFAULT = StpUtil.stpLogic;

    /**
     * User 会话对象，管理 user 表所有账号的登录、权限认证
     */
    public static final StpLogic USER = new StpLogic(USER_TYPE);
}

