package com.tishengke.tishengkebackend.application.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.application.service.QuestionApplicationService;
import com.tishengke.tishengkebackend.application.service.UserApplicationService;
import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBankQuestion;
import com.tishengke.tishengkebackend.domain.question.service.QuestionDomainService;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import com.tishengke.tishengkebackend.infrastructure.repository.QuestionBankQuestionRepository;
import com.tishengke.tishengkebackend.interfaces.dto.question.QuestionQueryRequest;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionVO;
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
 * @author Dmz
 * @description 针对表question(题目)相关的数据库操作Service实现
 * @createDate 2025-06-29 20:29:04
 */
@Service
@Slf4j
public class QuestionApplicationServiceImpl implements QuestionApplicationService {

    @Resource
    private UserApplicationService userApplicationService;

    @Resource
    private QuestionBankQuestionRepository questionBankQuestionRepository;

    @Resource
    private QuestionDomainService questionDomainService;

    @Override
    public void vaildQuestion(Question question, boolean addFlag) {
        ThrowUtils.throwIf(question == null, RespCode.PARAMS_ERROR, "题目信息不存在");
        // 题目信息校验
        question.vaildQuestion(addFlag);
    }

    @Override
    public long addQuestion(Question question, User loginUser) {

        question.setUserId(loginUser.getId());
        this.vaildQuestion(question, true);
        return questionDomainService.addQuestion(question, loginUser);
    }

    @Override
    public void deleteQuestion(long questionId, User loginUser) {
        questionDomainService.deleteQuestion(questionId, loginUser);
    }

    @Override
    public void updateQuestion(Question question) {
        this.vaildQuestion(question, false);
        boolean result = questionDomainService.updateQuestionById(question);
        ThrowUtils.throwIf(!result, RespCode.OPERATION_ERROR, "更新失败");
    }

    @Override
    public Question getQuestionById(Long questionId) {
        Question question = questionDomainService.getById(questionId);
        ThrowUtils.throwIf(question == null, RespCode.NOT_FOUND_ERROR, "题目信息不存在");
        return question;
    }

    @Override
    public QuestionVO getQuestionVO(Question question, HttpServletRequest request) {

        // 对象转换
        QuestionVO questionVO = QuestionVO.QuestionToVo(question);

        //关联用户信息
        Long userId = question.getUserId();
        if (userId != null) {
            User user = userApplicationService.getUserById(userId);
            UserVO userVO = userApplicationService.getUserVO(user);
            questionVO.setUser(userVO);
        }
        return questionVO;
    }

    @Override
    public QuestionVO getQuestionVOById(Question question, HttpServletRequest request) {
        return this.getQuestionVO(question, request);
    }

    @Override
    public Page<Question> getQuestionPage(QuestionQueryRequest questionQueryRequest) {
        // 分页参数取得
        long current = questionQueryRequest.getCurrent();
        long pageSize = questionQueryRequest.getPageSize();
        // 查询某一题库下的题目列表
        QueryWrapper<Question> queryWrapper = this.getQueryWrapper(questionQueryRequest);
        Long questionBankId = questionQueryRequest.getQuestionBankId();
        if (questionBankId != null) {
            // 查询题库内的题目 id
            LambdaQueryWrapper<QuestionBankQuestion> lambdaQueryWrapper = Wrappers.lambdaQuery(QuestionBankQuestion.class)
                .select(QuestionBankQuestion::getQuestionId)
                .eq(QuestionBankQuestion::getQuestionBankId, questionBankId);
            List<QuestionBankQuestion> questionList = questionBankQuestionRepository.list(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(questionList)) {
                // 取出题目 id 集合
                Set<Long> questionIdSet = questionList.stream()
                    .map(QuestionBankQuestion::getQuestionId)
                    .collect(Collectors.toSet());
                // 复用原有题目表的查询条件
                queryWrapper.in("id", questionIdSet);
            }else {
                // 题库为空，则返回空列表
                return new Page<>(current, pageSize, 0);
            }
        }
        return questionDomainService.page(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    public Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request) {
        List<Question> questionList = questionPage.getRecords();
        Page<QuestionVO> questionVOPage =
            new Page<>(questionPage.getCurrent(), questionPage.getSize(), questionPage.getTotal());
        if (CollUtil.isEmpty(questionList)) {
            return questionVOPage;
        }
        // 对象列表 => 封装对象列表
        List<QuestionVO> questionVOList =
            questionList.stream().map(QuestionVO::QuestionToVo).collect(Collectors.toList());
        // 1. 关联查询用户信息
        Set<Long> userIdSet = questionList.stream().map(Question::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap =
            userApplicationService.listByIds(userIdSet).stream().collect(Collectors.groupingBy(User::getId));
        // 2. 填充信息
        questionVOList.forEach(questionVO -> {
            Long userId = questionVO.getUserId();
            User user = null;
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }
            questionVO.setUser(userApplicationService.getUserVO(user));
        });
        questionVOPage.setRecords(questionVOList);
        return questionVOPage;
    }

    @Override
    public QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest) {
        return questionDomainService.getQueryWrapper(questionQueryRequest);
    }

    @Override
    public void setQuestionReviewStatus(Question question, User loginUser) {
        questionDomainService.setReviewStatus(question, loginUser);
    }
}




