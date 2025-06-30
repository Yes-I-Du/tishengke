package com.tishengke.tishengkebackend.application.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.DeleteRequest;
import com.tishengke.tishengkebackend.interfaces.dto.questionBank.QuestionBankAddRequest;
import com.tishengke.tishengkebackend.interfaces.dto.questionBank.QuestionBankQueryRequest;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionBankVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 针对【questionBank(题目)】相关的数据库操作Service
 *
 * @author Dmz
 * Email:  *
 * @since 2025/06/29 23:47
 */
public interface QuestionBankApplicationService {

    /**
     * 题库信息校验
     *
     * @param questionBank 题库信息
     */
    void vaildQuestionBank(QuestionBank questionBank);

    /**
     * 添加题库
     *
     * @param questionBankAddRequest 题库信息
     * @param request 请求
     * @return 题库Id
     */
    long addQuestionBank(QuestionBankAddRequest questionBankAddRequest, HttpServletRequest request);

    /**
     * 删除题库
     *
     * @param deleteRequest 删除请求
     * @return 删除结果
     */
    void deleteQuestionBank(DeleteRequest deleteRequest);

    /**
     * 更新题库
     *
     * @param questionBank 题库信息
     * @return 更新结果
     */
    void updateQuestionBank(QuestionBank questionBank);

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

        /**
         * 构造QueryWrapper对象生成Sql查询
         *
         * @param questionBankQueryRequest 题库信息查询对象
         * @return QueryWrapper QueryWrapper对象
         */
        QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest);

    /**
     * 设置题库信息审核状态
     *
     * @param questionBank   题库信息
     * @param loginUser 当前登录用户
     */
    void setQuestionBankReviewStatus(QuestionBank questionBank, User loginUser);

}
