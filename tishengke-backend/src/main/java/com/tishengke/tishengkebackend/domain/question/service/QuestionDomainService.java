package com.tishengke.tishengkebackend.domain.question.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.interfaces.dto.question.QuestionQueryRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dmz
 * @description 针对question(题目)相关的数据库操作Service
 * @createDate 2025-06-26 21:46:05
 */
public interface QuestionDomainService {

    /**
     * 添加题目
     *
     * @param question 题目信息
     * @param loginUSer 请求
     * @return 题目Id
     */
    long addQuestion(Question question, User loginUSer);

    /**
     * 删除题目
     *
     * @param questionId   题目Id
     * @param loginUser 当前登录用户
     */
    void deleteQuestion(long questionId, User loginUser);

    /**
     * 根据Id更新题目
     *
     * @param question 题目信息
     * @return 更新结果
     */
    boolean updateQuestionById(Question question);

    /**
     * 根据Id获取题目信息
     *
     * @param questionId 题目Id
     * @return 题目信息
     */
    Question getById(Long questionId);

    /**
     * 分页查询题目信息
     *
     * @param questionPage 分页信息
     * @param queryWrapper 查询条件
     * @return 题目信息
     */
    Page<Question> page(Page<Question> questionPage, QueryWrapper<Question> queryWrapper);

    /**
     * 构造QueryWrapper对象生成Sql查询
     *
     * @param questionQueryRequest 题目信息查询对象
     * @return QueryWrapper QueryWrapper对象
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 设置题目审核状态
     *
     * @param question 题目信息
     * @param loginUser 用户信息
     */
    void setReviewStatus(Question question, User loginUser);
}
