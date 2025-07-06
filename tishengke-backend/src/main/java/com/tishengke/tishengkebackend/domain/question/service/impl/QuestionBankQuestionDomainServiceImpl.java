package com.tishengke.tishengkebackend.domain.question.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.application.service.QuestionApplicationService;
import com.tishengke.tishengkebackend.application.service.QuestionBankApplicationService;
import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBankQuestion;
import com.tishengke.tishengkebackend.domain.question.service.QuestionBankQuestionDomainService;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import com.tishengke.tishengkebackend.infrastructure.repository.QuestionBankQuestionRepository;
import com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion.QuestionBankQuestionQueryRequest;
import com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion.QuestionBankQuestionRemoveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 针对表【questionBankQuestion(题库题目关系)】相关的数据库操作Service实现
 *
 * @author dmz xxx@163.com
 * @version 2025/7/6 0:12
 * @since JDK17
 */
@Service
@Slf4j
public class QuestionBankQuestionDomainServiceImpl implements QuestionBankQuestionDomainService {
    @Resource
    private QuestionBankApplicationService questionBankApplicationService;

    @Resource
    private QuestionApplicationService questionApplicationService;

    @Resource
    private QuestionBankQuestionRepository questionBankQuestionRepository;

    @Override
    public void vaildQuestionBankQuestion(QuestionBankQuestion questionBankQuestion) {
        // 题库信息校验
        QuestionBank questionBank =
            questionBankApplicationService.getQuestionBankById(questionBankQuestion.getQuestionBankId());
        ThrowUtils.throwIf(questionBank == null, RespCode.NOT_FOUND_ERROR, "题库不存在");
        // 题目信息校验
        Question question = questionApplicationService.getQuestionById(questionBankQuestion.getQuestionId());
        ThrowUtils.throwIf(question == null, RespCode.NOT_FOUND_ERROR, "题目不存在");
    }

    @Override
    public long addQuestionBankQuestion(QuestionBankQuestion questionBankQuestion, User loginUser) {
        boolean result = questionBankQuestionRepository.save(questionBankQuestion);
        ThrowUtils.throwIf(!result, RespCode.OPERATION_ERROR, "题目创建失败");
        return questionBankQuestion.getId();
    }

    @Override
    public void deleteQuestionBankQuestion(long questionBankQuestionId, User loginUser) {
        // 判断是否存在
        QuestionBankQuestion existQuestionBankQuestion = this.getQuestionBankQuestionById(questionBankQuestionId);
        ThrowUtils.throwIf(existQuestionBankQuestion == null, RespCode.NOT_FOUND_ERROR, "数据不存在");
        // TODO:后续拓展为用户可创建题目，权限控制
        // ....
        // ....

        // 操作数据库
        ThrowUtils.throwIf(!questionBankQuestionRepository.removeById(questionBankQuestionId), RespCode.OPERATION_ERROR,
            "删除失败");
    }

    @Override
    public boolean updateQuestionBankQuestionById(QuestionBankQuestion questionBankQuestion) {
        return questionBankQuestionRepository.updateById(questionBankQuestion);
    }

    @Override
    public QuestionBankQuestion getQuestionBankQuestionById(Long questionBankQuestionId) {
        return questionBankQuestionRepository.getById(questionBankQuestionId);
    }

    @Override
    public QueryWrapper<QuestionBankQuestion> getQueryWrapper(
        QuestionBankQuestionQueryRequest questionBankQuestionQueryRequest) {
        QueryWrapper<QuestionBankQuestion> queryWrapper = new QueryWrapper<>();
        if (questionBankQuestionQueryRequest == null) {
            return queryWrapper;
        }
        // 从对象中取值
        Long id = questionBankQuestionQueryRequest.getId();
        Long notId = questionBankQuestionQueryRequest.getNotId();
        String sortField = questionBankQuestionQueryRequest.getSortField();
        String sortOrder = questionBankQuestionQueryRequest.getSortOrder();
        Long questionBankId = questionBankQuestionQueryRequest.getQuestionBankId();
        Long questionId = questionBankQuestionQueryRequest.getQuestionId();
        Long userId = questionBankQuestionQueryRequest.getUserId();

        // 精确查询
        queryWrapper.ne(ObjectUtils.isNotEmpty(notId), "id", notId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionBankId), "questionBankId", questionBankId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);

        // 排序
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("ascend"), sortField);

        return queryWrapper;
    }

    @Override
    public Page<QuestionBankQuestion> getQuestionBankQuestionPage(Page<QuestionBankQuestion> questionBankQuestionPage,
        QueryWrapper<QuestionBankQuestion> queryWrapper) {
        return questionBankQuestionRepository.page(questionBankQuestionPage, queryWrapper);
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

    @Override
    public void removeQuestionBankQuestion(QuestionBankQuestionRemoveRequest questionBankQuestionRemoveRequest) {
        // 题库信息校验
        QuestionBank questionBank =
            questionBankApplicationService.getQuestionBankById(questionBankQuestionRemoveRequest.getQuestionBankId());
        ThrowUtils.throwIf(questionBank == null, RespCode.NOT_FOUND_ERROR, "题库不存在");
        // 题目信息校验
        Question question = questionApplicationService.getQuestionById(questionBankQuestionRemoveRequest.getQuestionId());
        ThrowUtils.throwIf(question == null, RespCode.NOT_FOUND_ERROR, "题目不存在");


        // 构造查询
        LambdaQueryWrapper<QuestionBankQuestion> lambdaQueryWrapper = Wrappers.lambdaQuery(QuestionBankQuestion.class)
            .eq(QuestionBankQuestion::getQuestionBankId, questionBankQuestionRemoveRequest.getQuestionBankId())
            .eq(QuestionBankQuestion::getQuestionId, questionBankQuestionRemoveRequest.getQuestionId());
        ThrowUtils.throwIf(!questionBankQuestionRepository.remove(lambdaQueryWrapper), RespCode.OPERATION_ERROR, "删除失败");
    }
}

