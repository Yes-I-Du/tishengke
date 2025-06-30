package com.tishengke.tishengkebackend.domain.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.domain.user.constant.UserConstant;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.domain.user.service.UserDomainService;
import com.tishengke.tishengkebackend.domain.user.valueObject.UserRoleEnum;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.exception.BusinessException;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import com.tishengke.tishengkebackend.infrastructure.repository.UserRepository;
import com.tishengke.tishengkebackend.interfaces.dto.user.UserQueryRequest;
import com.tishengke.tishengkebackend.interfaces.vo.user.LoginUserVO;
import com.tishengke.tishengkebackend.interfaces.vo.user.UserVO;
import com.tishengke.tishengkebackend.shared.auth.StpKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Dmz
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-05-18 19:04:32
 */
@Service
@Slf4j
public class UserDomainServiceImpl implements UserDomainService {

    @Resource
    private UserRepository userRepository;

    @Override
    public long userRegister(String userAccount, String userPassword) {
        // 账号重复检查
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        ThrowUtils.throwIf(userRepository.getBaseMapper().selectCount(queryWrapper) > 0, RespCode.PARAMS_ERROR,
            "账号已存在");

        // 加密
        String encryptPassword = this.getEncryptPassword(userPassword);

        // 数据入库
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setUserName("用户" + userAccount);
        user.setUserRole(UserRoleEnum.USER.getValue());
        boolean saveResult = userRepository.save(user);
        ThrowUtils.throwIf(!saveResult, RespCode.SYSTEM_ERROR, "注册失败,请稍后再试！！！");

        return user.getId();
    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest httpServletRequest) {
        // 加密
        String encryptPassword = this.getEncryptPassword(userPassword);

        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userRepository.getBaseMapper().selectOne(queryWrapper);
        if (user == null) {
            log.info("user login fail, userAccount cannot match userPassword");
            throw new BusinessException(RespCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 记录用户登陆状态到SaToken,用户登录校验
        // 需要保证用户信息与Session中的过期信息一致
        StpKit.USER.login(user.getId());
        StpKit.USER.getSession().set(UserConstant.USER_LOGIN_STATUS, user);

        // 返回用户脱敏信息
        return this.getLoginUserVO(user);
    }

    @Override
    public User getLoginUser(HttpServletRequest httpServletRequest) {
        Long loginUserId = StpKit.USER.getLoginIdAsLong();
        ThrowUtils.throwIf(loginUserId == null || loginUserId <= 0, RespCode.NOT_LOGIN_ERROR);

        User currentUser = this.getById(loginUserId);
        ThrowUtils.throwIf(currentUser == null, RespCode.NOT_LOGIN_ERROR, "用户不存在");

        return currentUser;
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }

        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtil.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public boolean userLogout(HttpServletRequest httpServletRequest) {
        StpKit.USER.checkLogin();
        StpKit.USER.logout();
        return true;
    }

    @Override
    public UserVO getUserVO(User user) {
        // 用户信息为空
        if (user == null) {
            return null;
        }

        // 用户信息脱敏
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVOList(List<User> userlist) {
        // 用户信息列表为空
        if (CollUtil.isEmpty(userlist)) {
            return new ArrayList<>();
        }
        return userlist.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(RespCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String userAccount = userQueryRequest.getUserAccount();
        String userName = userQueryRequest.getUserName();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjUtil.isNotNull(id), "id", id);
        queryWrapper.eq(StrUtil.isNotBlank(userRole), "userRole", userRole);
        queryWrapper.like(StrUtil.isNotBlank(userAccount), "userAccount", userAccount);
        queryWrapper.like(StrUtil.isNotBlank(userName), "userName", userName);
        queryWrapper.like(StrUtil.isNotBlank(userProfile), "userProfile", userProfile);
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("ascend"), sortField);
        return queryWrapper;
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public long addUser(User user) {
        user.setUserPassword(this.getEncryptPassword(user.getUserPassword()));
        boolean result = userRepository.save(user);
        ThrowUtils.throwIf(!result, RespCode.OPERATION_ERROR, "新增用户失败");
        return user.getId();
    }

    @Override
    public boolean removeUserById(Long id) {
        return userRepository.removeById(id);
    }

    @Override
    public boolean updateUserById(User user) {
        return userRepository.updateById(user);
    }

    @Override
    public Page<User> page(Page<User> userPage, QueryWrapper<User> queryWrapper) {
        return userRepository.page(userPage, queryWrapper);
    }

    @Override
    public List<User> listByIds(Set<Long> userIdSet) {
        return userRepository.listByIds(userIdSet);
    }

    @Override
    public String getEncryptPassword(String userPassword) {
        return DigestUtils.md5DigestAsHex((UserConstant.SALT + userPassword).getBytes());
    }

}