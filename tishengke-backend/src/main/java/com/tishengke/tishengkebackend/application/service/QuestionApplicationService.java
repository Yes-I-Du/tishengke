package com.tishengke.tishengkebackend.application.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.interfaces.dto.question.QuestionQueryRequest;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author Dmz
* @description 针对question(题目)相关的数据库操作Service
* @createDate 2025-06-26 21:46:05
*/
public interface QuestionApplicationService{

    /**
     * 题目信息校验
     *
     * @param question 题目信息
     * @param addFlag      是否为添加操作
     */
    void vaildQuestion(Question question, boolean addFlag);

    /**
     * 添加题目
     *
     * @param question 题目信息
     * @param loginUser    当前登录用户
     * @return 题目Id
     */
    long addQuestion(Question question, User loginUser);

    /**
     * 删除题目
     *
     * @param questionId 题目Id
     * @param loginUser      当前登录用户
     */
    void deleteQuestion(long questionId, User loginUser);

    /**
     * 更新题目
     *
     * @param question 题目信息
     * @return 更新结果
     */
    void updateQuestion(Question question);

    /**
     * 根据Id获取题目信息
     *
     * @param questionId 题目Id
     * @return 题目信息
     */
    Question getQuestionById(Long questionId);

    /**
     * 获取题目脱敏信息
     *
     * @param question 题目信息
     * @param request      请求
     * @return 题目脱敏信息
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     * 根据Id获取题目脱敏信息
     *
     * @param question 题目信息
     * @param request      请求
     * @return 题目脱敏信息
     */
    QuestionVO getQuestionVOById(Question question, HttpServletRequest request);

    /**
     * 分页获取题目信息
     *
     * @param questionQueryRequest 题目信息查询对象
     * @return 题目页信息
     */
    Page<Question> getQuestionPage(QuestionQueryRequest questionQueryRequest);

    /**
     * 分页查询题目信息
     *
     * @param questionPage 题目页
     * @return 题目信息分页结果
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);

    /**
     * 构造QueryWrapper对象生成Sql查询
     *
     * @param questionQueryRequest 题目信息查询对象
     * @return QueryWrapper QueryWrapper对象
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 设置题目信息审核状态
     *
     * @param question 题目信息
     * @param loginUser    当前登录用户
     */
    void setQuestionReviewStatus(Question question, User loginUser);
}
