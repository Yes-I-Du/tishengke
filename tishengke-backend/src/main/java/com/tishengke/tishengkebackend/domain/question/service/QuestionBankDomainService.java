package com.tishengke.tishengkebackend.domain.question.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.interfaces.dto.questionBank.QuestionBankQueryRequest;

/**
 * @description 针对【questionBank(题目)】相关的数据库操作Service
 *
 * @author Dmz
 * Email:  *
 * @since 2025/06/29 23:47
 */
public interface QuestionBankDomainService {

    /**
     * 添加题库
     *
     * @param questionBank 题库信息
     * @param loginUSer 请求
     * @return 题库Id
     */
    long addQuestionBank(QuestionBank questionBank, User loginUSer);

    /**
     * 删除题库
     *
     * @param questionBankId   题库Id
     * @param loginUser 当前登录用户
     */
    void deleteQuestionBank(long questionBankId, User loginUser);

    /**
     * 根据Id更新题库
     *
     * @param questionBank 题库信息
     * @return 更新结果
     */
    boolean updateQuestionBankById(QuestionBank questionBank);

    /**
     * 根据Id获取题库信息
     *
     * @param questionBankId 题库Id
     * @return 题库信息
     */
    QuestionBank getById(Long questionBankId);

    /**
     * 分页查询题库信息
     *
     * @param questionBankPage 分页信息
     * @param queryWrapper 查询条件
     * @return 题库信息
     */
    Page<QuestionBank> page(Page<QuestionBank> questionBankPage, QueryWrapper<QuestionBank> queryWrapper);

    /**
     * 构造QueryWrapper对象生成Sql查询
     *
     * @param questionBankQueryRequest 题库信息查询对象
     * @return QueryWrapper QueryWrapper对象
     */
    QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest);

    /**
     * 设置题库审核状态
     *
     * @param questionBank 题库信息
     * @param loginUser 用户信息
     */
    void setReviewStatus(QuestionBank questionBank, User loginUser);
}
