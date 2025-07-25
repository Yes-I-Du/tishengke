package com.tishengke.tishengkebackend.shared.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.tishengke.tishengkebackend.domain.user.constant.UserConstant;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 自定义权限加载接口实现类 获取当前账号权限码集合
 *
 * @author dmz xxx@163.com
 * @version 2025/6/23 20:01
 * @since JDK17
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {

        return new ArrayList<>();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 从当前登录用户信息中获取角色
        User user = (User)StpKit.USER.getSessionByLoginId(loginId).get(UserConstant.USER_LOGIN_STATUS);
        return Collections.singletonList(user.getUserRole());
    }

}

