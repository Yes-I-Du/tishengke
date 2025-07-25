package com.tishengke.tishengkebackend.domain.user.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.mapper.UserMapper;
import com.tishengke.tishengkebackend.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * 用户仓储接口实现
 *
 * @author Dmz Email:  * @since 2025/06/26 22:02
 */
@Service
public class UserRepositoryImpl extends ServiceImpl<UserMapper, User> implements UserRepository {
}
