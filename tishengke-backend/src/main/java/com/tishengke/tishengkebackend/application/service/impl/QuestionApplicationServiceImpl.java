package com.tishengke.tishengkebackend.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.application.service.QuestionApplicationService;
import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.DeleteRequest;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionBankVO;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author Dmz
* @description 针对表question(题目)相关的数据库操作Service实现
* @createDate 2025-06-29 20:29:04
*/
@Service
public class QuestionApplicationServiceImpl implements QuestionApplicationService {

    @Override
    public void vaildQuestionBank(QuestionBank questionBank) {

    }

    @Override
    public long addQuestionBank(QuestionBank questionBank) {
        return 0;
    }

    @Override
    public boolean deleteQuestionBank(DeleteRequest deleteRequest) {
        return false;
    }

    @Override
    public void updateQuestionBank(QuestionBank questionBank) {

    }

    @Override
    public QuestionBank getQuestionBank(HttpServletRequest request) {
        return null;
    }

    @Override
    public QuestionBank getQuestionBankById(Long questionBankId) {
        return null;
    }

    @Override
    public QuestionBankVO getQuestionBankVO(QuestionBank questionBank, HttpServletRequest request) {
        return null;
    }

    @Override
    public QuestionBankVO getQuestionBankVOById(QuestionBank questionBank, HttpServletRequest request) {
        return null;
    }

    @Override
    public Page<QuestionBank> getQuestionBankPage(Page<QuestionBank> questionBankPage,
        QueryWrapper<QuestionBank> queryWrapper) {
        return null;
    }

    @Override
    public Page<QuestionBankVO> getQuestionBankVOPage(Page<QuestionBank> questionBankPage, HttpServletRequest request) {
        return null;
    }

//    @Override
//    public QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest) {
//        return null;
//    }
//
//    @Override
//    public void doQuestionBankReview(QuestionBankReviewRequest questionBankReviewRequest, User loginUser) {
//
//    }

    @Override
    public void setQuestionBankReviewStatus(QuestionBank questionBank, User loginUser) {

    }

    @Override
    public void vaildQuestion(Question question) {

    }

    @Override
    public long addQuestion(Question question) {
        return 0;
    }

    @Override
    public boolean deleteQuestion(DeleteRequest deleteRequest) {
        return false;
    }

    @Override
    public void updateQuestion(Question question) {

    }

    @Override
    public Question getQuestion(HttpServletRequest request) {
        return null;
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return null;
    }

    @Override
    public QuestionVO getQuestionVO(Question question, HttpServletRequest request) {
        return null;
    }

    @Override
    public QuestionVO getQuestionVOById(Question question, HttpServletRequest request) {
        return null;
    }

    @Override
    public Page<Question> getQuestionPage(Page<Question> questionPage, QueryWrapper<Question> queryWrapper) {
        return null;
    }

    @Override
    public Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request) {
        return null;
    }

//    @Override
//    public QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest) {
//        return null;
//    }
//
//    @Override
//    public void doQuestionReview(QuestionReviewRequest questionReviewRequest, User loginUser) {
//
//    }

    @Override
    public void setQuestionReviewStatus(Question question, User loginUser) {

    }
}




