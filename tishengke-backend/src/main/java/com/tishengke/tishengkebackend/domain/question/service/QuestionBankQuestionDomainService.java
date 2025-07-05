package com.tishengke.tishengkebackend.domain.question.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBankQuestion;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion.QuestionBankQuestionQueryRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 针对【questionBankQuestion(题库题目关系)】相关的数据库操作Service
 *
 * @author Dmz Email:  * @since 2025/07/06 00:11
 */
public interface QuestionBankQuestionDomainService {

    /**
     * 数据信息校验
     *
     * @param questionBankQuestion 题库题目关联信息
     */
    void vaildQuestionBankQuestion(QuestionBankQuestion questionBankQuestion);

    /**
     * 添加题库题目关系信息(添加题目到题库)
     *
     * @param questionBankQuestion 题库题目关联信息
     * @param loginUser            当前登录用户
     * @return 题目Id
     */
    long addQuestionBankQuestion(QuestionBankQuestion questionBankQuestion, User loginUser);

    /**
     * 删除题库题目关联信息(从题库移除题目)
     *
     * @param questionBankQuestionId 题库题目关联信息Id
     * @param loginUser              当前登录用户
     */
    void deleteQuestionBankQuestion(long questionBankQuestionId, User loginUser);

    /**
     * 更新题库题目关联信息
     *
     * @param questionBankQuestion 题库题目关联信息
     * @return 更新结果
     */
    boolean updateQuestionBankQuestionById(QuestionBankQuestion questionBankQuestion);

    /**
     * 根据Id获取题库题目关联信息
     *
     * @param questionBankQuestionId 题库题目关联信息Id
     * @return 题库题目关联信息
     */
    QuestionBankQuestion getQuestionBankQuestionById(Long questionBankQuestionId);

    /**
     * 构造QueryWrapper对象生成Sql查询
     *
     * @param questionBankQuestionQueryRequest tiku信息查询对象
     * @return QueryWrapper QueryWrapper对象
     */
    QueryWrapper<QuestionBankQuestion> getQueryWrapper(
        QuestionBankQuestionQueryRequest questionBankQuestionQueryRequest);

    /**
     * 分页获取题库题目关联信息
     *
     * @param questionBankQuestionPage 题库题目关联信息分页对象
     *                                  @param queryWrapper              QueryWrapper对象
     * @return 题库题目关联页信息
     */
    Page<QuestionBankQuestion> getQuestionBankQuestionPage(
        Page<QuestionBankQuestion> questionBankQuestionPage, QueryWrapper<QuestionBankQuestion> queryWrapper);

    /**
     * 批量添加题目到题库
     *
     * @param questionIdList 题目id列表
     * @param questionBankId 题库id
     * @param loginUser      当前登录用户
     */
    void batchAddQuestionsToBank(List<Long> questionIdList, long questionBankId, User loginUser);

    /**
     * 批量从题库移除题目
     *
     * @param questionIdList 题目id列表
     * @param questionBankId 题库id
     * @param loginUser      当前登录用户
     */
    void batchRemoveQuestionsFromBank(List<Long> questionIdList, long questionBankId, User loginUser);

    /**
     * 批量添加题目到题库（事务，仅供内部调用）
     *
     * @param questionBankQuestions
     */
    @Transactional(rollbackFor = Exception.class)
    void batchAddQuestionsToBankInner(List<QuestionBankQuestion> questionBankQuestions);
}
