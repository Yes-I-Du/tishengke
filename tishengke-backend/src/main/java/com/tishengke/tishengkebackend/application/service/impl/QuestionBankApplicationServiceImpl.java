package com.tishengke.tishengkebackend.application.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.application.service.QuestionBankApplicationService;
import com.tishengke.tishengkebackend.application.service.UserApplicationService;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.domain.question.service.QuestionBankDomainService;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import com.tishengke.tishengkebackend.interfaces.dto.questionBank.QuestionBankQueryRequest;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionBankVO;
import com.tishengke.tishengkebackend.interfaces.vo.user.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 针对【questionBank(题目)】相关的数据库操作Service
 *
 * @author Dmz Email:  *
 * @since 2025/06/29 23:47
 */
@Service
@Slf4j
public class QuestionBankApplicationServiceImpl implements QuestionBankApplicationService {

    @Resource
    private UserApplicationService userApplicationService;

    @Resource
    private QuestionBankDomainService questionBankDomainService;

    @Override
    public void vaildQuestionBank(QuestionBank questionBank, boolean addFlag) {
        ThrowUtils.throwIf(questionBank == null, RespCode.PARAMS_ERROR, "题库信息不存在");
        // 题库信息校验
        questionBank.vaildQuestionBank(addFlag);
    }

    @Override
    public long addQuestionBank(QuestionBank questionBank, User loginUser) {
        questionBank.setUserId(loginUser.getId());
        this.vaildQuestionBank(questionBank, true);
        return questionBankDomainService.addQuestionBank(questionBank, loginUser);
    }

    @Override
    public void deleteQuestionBank(long questionBankId, User loginUser) {
        questionBankDomainService.deleteQuestionBank(questionBankId, loginUser);
    }

    @Override
    public void updateQuestionBank(QuestionBank questionBank) {
        this.vaildQuestionBank(questionBank, false);
        boolean result = questionBankDomainService.updateQuestionBankById(questionBank);
        ThrowUtils.throwIf(!result, RespCode.OPERATION_ERROR, "更新失败");
    }

    @Override
    public QuestionBank getQuestionBankById(Long questionBankId) {
        QuestionBank questionBank = questionBankDomainService.getById(questionBankId);
        ThrowUtils.throwIf(questionBank == null, RespCode.NOT_FOUND_ERROR, "题库信息不存在");
        return questionBank;
    }

    @Override
    public QuestionBankVO getQuestionBankVO(QuestionBank questionBank, HttpServletRequest request) {

        // 对象转换
        QuestionBankVO questionBankVO = QuestionBankVO.QuestionBankToVo(questionBank);

        //关联用户信息
        Long userId = questionBank.getUserId();
        if (userId != null) {
            User user = userApplicationService.getUserById(userId);
            UserVO userVO = userApplicationService.getUserVO(user);
            questionBankVO.setUser(userVO);
        }
        return questionBankVO;
    }

    @Override
    public QuestionBankVO getQuestionBankVOById(QuestionBank questionBank, HttpServletRequest request) {
        return this.getQuestionBankVO(questionBank, request);
    }

    @Override
    public Page<QuestionBank> getQuestionBankPage(Page<QuestionBank> questionBankPage,
        QueryWrapper<QuestionBank> queryWrapper) {
        return questionBankDomainService.page(questionBankPage, queryWrapper);
    }

    @Override
    public Page<QuestionBankVO> getQuestionBankVOPage(Page<QuestionBank> questionBankPage, HttpServletRequest request) {
        List<QuestionBank> questionBankList = questionBankPage.getRecords();
        Page<QuestionBankVO> questionBankVOPage =
            new Page<>(questionBankPage.getCurrent(), questionBankPage.getSize(), questionBankPage.getTotal());
        if (CollUtil.isEmpty(questionBankList)) {
            return questionBankVOPage;
        }
        // 对象列表 => 封装对象列表
        List<QuestionBankVO> questionBankVOList =
            questionBankList.stream().map(QuestionBankVO::QuestionBankToVo).collect(Collectors.toList());
        // 1. 关联查询用户信息
        Set<Long> userIdSet = questionBankList.stream().map(QuestionBank::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap =
            userApplicationService.listByIds(userIdSet).stream().collect(Collectors.groupingBy(User::getId));
        // 2. 填充信息
        questionBankVOList.forEach(questionBankVO -> {
            Long userId = questionBankVO.getUserId();
            User user = null;
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }
            questionBankVO.setUser(userApplicationService.getUserVO(user));
        });
        questionBankVOPage.setRecords(questionBankVOList);
        return questionBankVOPage;
    }

    @Override
    public QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest) {
        return questionBankDomainService.getQueryWrapper(questionBankQueryRequest);
    }

    @Override
    public void setQuestionBankReviewStatus(QuestionBank questionBank, User loginUser) {
        questionBankDomainService.setReviewStatus(questionBank, loginUser);
    }
}