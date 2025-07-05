package com.tishengke.tishengkebackend.application.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.application.service.QuestionBankQuestionApplicationService;
import com.tishengke.tishengkebackend.application.service.UserApplicationService;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBankQuestion;
import com.tishengke.tishengkebackend.domain.question.service.QuestionBankQuestionDomainService;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion.QuestionBankQuestionQueryRequest;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionBankQuestionVO;
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
 * 针对【questionBankQuestion(题库题目关系)】相关的数据库操作Service
 *
 * @author dmz xxx@163.com
 * @version 2025/7/5 23:01
 * @since JDK17
 */
@Service
@Slf4j
public class QuestionBankQuestionApplicationServiceImpl implements QuestionBankQuestionApplicationService {

    @Resource
    private UserApplicationService userApplicationService;

    @Resource
    private QuestionBankQuestionDomainService questionBankQuestionDomainService;

    @Override
    public void vaildQuestionBankQuestion(QuestionBankQuestion questionBankQuestion) {
        ThrowUtils.throwIf(questionBankQuestion == null, RespCode.PARAMS_ERROR, "题目信息不存在");
        // 校验题库题目关系信息
        questionBankQuestion.vaildQuestionBankQuestion();
        questionBankQuestionDomainService.vaildQuestionBankQuestion(questionBankQuestion);
    }

    @Override
    public long addQuestionBankQuestion(QuestionBankQuestion questionBankQuestion, User loginUser) {
        questionBankQuestion.setUserId(loginUser.getId());
        this.vaildQuestionBankQuestion(questionBankQuestion);
        return questionBankQuestionDomainService.addQuestionBankQuestion(questionBankQuestion, loginUser);
    }

    @Override
    public void deleteQuestionBankQuestion(long questionBankQuestionId, User loginUser) {
        questionBankQuestionDomainService.deleteQuestionBankQuestion(questionBankQuestionId, loginUser);
    }

    @Override
    public void updateQuestionBankQuestion(QuestionBankQuestion questionBankQuestion) {
        this.vaildQuestionBankQuestion(questionBankQuestion);
        boolean result = questionBankQuestionDomainService.updateQuestionBankQuestionById(questionBankQuestion);
        ThrowUtils.throwIf(!result, RespCode.OPERATION_ERROR, "更新失败");
    }

    @Override
    public QuestionBankQuestion getQuestionBankQuestionById(Long questionBankQuestionId) {
        QuestionBankQuestion questionBankQuestion = questionBankQuestionDomainService.getQuestionBankQuestionById(questionBankQuestionId);
        ThrowUtils.throwIf(questionBankQuestion == null, RespCode.NOT_FOUND_ERROR, "题目信息不存在");
        return questionBankQuestion;
    }

    @Override
    public QueryWrapper<QuestionBankQuestion> getQueryWrapper(
        QuestionBankQuestionQueryRequest questionBankQuestionQueryRequest) {
        return questionBankQuestionDomainService.getQueryWrapper(questionBankQuestionQueryRequest);
    }

    @Override
    public Page<QuestionBankQuestion> getQuestionBankQuestionPage(
        QuestionBankQuestionQueryRequest questionBankQuestionQueryRequest) {
        // 分页参数取得
        long current = questionBankQuestionQueryRequest.getCurrent();
        long pageSize = questionBankQuestionQueryRequest.getPageSize();

        return questionBankQuestionDomainService.getQuestionBankQuestionPage(new Page<>(current, pageSize),
            this.getQueryWrapper(questionBankQuestionQueryRequest));
    }

    @Override
    public QuestionBankQuestionVO getQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion,
        HttpServletRequest httpServletRequest) {
        // 对象转换
        QuestionBankQuestionVO questionBankQuestionVO =
            QuestionBankQuestionVO.QuestionBankQuestionToVo(questionBankQuestion);

        //关联用户信息
        Long userId = questionBankQuestion.getUserId();
        if (userId != null) {
            User user = userApplicationService.getUserById(userId);
            UserVO userVO = userApplicationService.getUserVO(user);
            questionBankQuestionVO.setUser(userVO);
        }
        return questionBankQuestionVO;
    }

    @Override
    public QuestionBankQuestionVO getQuestionBankQuestionVOById(QuestionBankQuestion questionBankQuestion,
        HttpServletRequest request) {
        return this.getQuestionBankQuestionVO(questionBankQuestion, request);
    }

    @Override
    public Page<QuestionBankQuestionVO> getQuestionBankQuestionVOPage(
        Page<QuestionBankQuestion> questionBankQuestionPage, HttpServletRequest httpServletRequest) {
        List<QuestionBankQuestion> questionBankQuestionList = questionBankQuestionPage.getRecords();
        Page<QuestionBankQuestionVO> questionBankQuestionVOPage =
            new Page<>(questionBankQuestionPage.getCurrent(), questionBankQuestionPage.getSize(), questionBankQuestionPage.getTotal());
        if (CollUtil.isEmpty(questionBankQuestionList)) {
            return questionBankQuestionVOPage;
        }
        // 对象列表 => 封装对象列表
        List<QuestionBankQuestionVO>  questionBankQuestionVOList=
            questionBankQuestionList.stream().map(QuestionBankQuestionVO::QuestionBankQuestionToVo).collect(Collectors.toList());
        // 1. 关联查询用户信息
        Set<Long> userIdSet = questionBankQuestionList.stream().map(QuestionBankQuestion::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap =
            userApplicationService.listByIds(userIdSet).stream().collect(Collectors.groupingBy(User::getId));
        // 2. 填充信息
        questionBankQuestionVOList.forEach(questionBankQuestionVO -> {
            Long userId = questionBankQuestionVO.getUserId();
            User user = null;
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }
            questionBankQuestionVO.setUser(userApplicationService.getUserVO(user));
        });
        questionBankQuestionVOPage.setRecords(questionBankQuestionVOList);
        return questionBankQuestionVOPage;
    }

    @Override
    public void batchAddQuestionsToBank(List<Long> questionIdList, long questionBankId, User loginUser) {

    }

    @Override
    public void batchRemoveQuestionsFromBank(List<Long> questionIdList, long questionBankId, User loginUser) {

    }

    @Override
    public void batchAddQuestionsToBankInner(List<QuestionBankQuestion> questionBankQuestions) {

    }
}

