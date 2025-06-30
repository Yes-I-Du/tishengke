package com.tishengke.tishengkebackend.application.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.DeleteRequest;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionBankVO;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author Dmz
* @description 针对question(题目)相关的数据库操作Service
* @createDate 2025-06-26 21:46:05
*/
public interface QuestionApplicationService{

    /**
     * 题库信息校验
     *
     * @param questionBank 题库信息
     */
    void vaildQuestionBank(QuestionBank questionBank);

    /**
     * 添加题库
     *
     * @param questionBank 题库信息
     * @return 题库Id
     */
    long addQuestionBank(QuestionBank questionBank);

    /**
     * 删除题库
     *
     * @param deleteRequest 删除请求
     * @return 删除结果
     */
    boolean deleteQuestionBank(DeleteRequest deleteRequest);

    /**
     * 更新题库
     *
     * @param questionBank 题库信息
     * @return 更新结果
     */
    void updateQuestionBank(QuestionBank questionBank);

    /**
     * 获取题库信息
     *
     * @param request 请求
     * @return 题库信息
     */
    QuestionBank getQuestionBank(HttpServletRequest request);

    /**
     * 根据Id获取题库信息
     *
     * @param questionBankId 题库Id
     * @return 题库信息
     */
    QuestionBank getQuestionBankById(Long questionBankId);

    /**
     * 获取题库脱敏信息
     *
     * @param questionBank 题库信息
     * @param request 请求
     * @return 题库脱敏信息
     */
    QuestionBankVO getQuestionBankVO(QuestionBank questionBank, HttpServletRequest request);

    /**
     * 根据Id获取题库脱敏信息
     *
     * @param questionBank 题库信息
     * @param request 请求
     * @return 题库脱敏信息
     */
    QuestionBankVO getQuestionBankVOById(QuestionBank questionBank, HttpServletRequest request);


    /**
     * 获取某一页题库信息
     *
     * @param questionBankPage  题库页
     * @param queryWrapper 查询条件
     * @return 题库页信息
     */
    Page<QuestionBank> getQuestionBankPage(Page<QuestionBank> questionBankPage, QueryWrapper<QuestionBank> queryWrapper);

    /**
     * 分页查询题库信息
     *
     * @param questionBankPage 题库页
     * @return 题库信息分页结果
     */
    Page<QuestionBankVO> getQuestionBankVOPage(Page<QuestionBank> questionBankPage, HttpServletRequest request);

//    /**
//     * 构造QueryWrapper对象生成Sql查询
//     *
//     * @param questionBankQueryRequest 题库信息查询对象
//     * @return QueryWrapper QueryWrapper对象
//     */
//    QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest);
//
//    /**
//     * 题库信息审核
//     *
//     * @param questionBankReviewRequest 审核信息
//     * @param loginUser            当前登录用户
//     */
//    void doQuestionBankReview(QuestionBankReviewRequest questionBankReviewRequest, User loginUser);

    /**
     * 设置题库信息审核状态
     *
     * @param questionBank   题库信息
     * @param loginUser 当前登录用户
     */
    void setQuestionBankReviewStatus(QuestionBank questionBank, User loginUser);


    /**
     * 题目信息校验
     *
     * @param question 题目信息
     */
    void vaildQuestion(Question question);

    /**
     * 添加题目
     *
     * @param question 题目信息
     * @return 题目Id
     */
    long addQuestion(Question question);

    /**
     * 删除题目
     *
     * @param deleteRequest 删除请求
     * @return 删除结果
     */
    boolean deleteQuestion(DeleteRequest deleteRequest);

    /**
     * 更新题目
     *
     * @param question 题目信息
     * @return 更新结果
     */
    void updateQuestion(Question question);

    /**
     * 获取题目信息
     *
     * @param request 请求
     * @return 题目信息
     */
    Question getQuestion(HttpServletRequest request);

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
     * @param request 请求
     * @return 题目脱敏信息
     */
    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    /**
     * 根据Id获取题目脱敏信息
     *
     * @param question 题目信息
     * @param request 请求
     * @return 题目脱敏信息
     */
    QuestionVO getQuestionVOById(Question question, HttpServletRequest request);


    /**
     * 获取某一页题目信息
     *
     * @param questionPage  题目页
     * @param queryWrapper 查询条件
     * @return 题目页信息
     */
    Page<Question> getQuestionPage(Page<Question> questionPage, QueryWrapper<Question> queryWrapper);

    /**
     * 分页查询题目信息
     *
     * @param questionPage 题目页
     * @return 题目信息分页结果
     */
    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);

//    /**
//     * 构造QueryWrapper对象生成Sql查询
//     *
//     * @param questionQueryRequest 题库信息查询对象
//     * @return QueryWrapper QueryWrapper对象
//     */
//    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);
//
//    /**
//     * 题目信息审核
//     *
//     * @param questionReviewRequest 审核信息
//     * @param loginUser            当前登录用户
//     */
//    void doQuestionReview(QuestionReviewRequest questionReviewRequest, User loginUser);

    /**
     * 设置题目信息审核状态
     *
     * @param question   题目信息
     * @param loginUser 当前登录用户
     */
    void setQuestionReviewStatus(Question question, User loginUser);
}
