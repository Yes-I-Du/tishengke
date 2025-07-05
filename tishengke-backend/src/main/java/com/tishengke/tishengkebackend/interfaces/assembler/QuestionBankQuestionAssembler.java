package com.tishengke.tishengkebackend.interfaces.assembler;

import com.tishengke.tishengkebackend.domain.question.entity.QuestionBankQuestion;
import com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion.QuestionBankQuestionAddRequest;
import com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion.QuestionBankQuestionUpdateRequest;
import org.springframework.beans.BeanUtils;

/**
 * 题库题目关系请求转换类
 *
 * @author dmz xxx@163.com
 * @version 2025/7/6 0:45
 * @since JDK17
 */
public class QuestionBankQuestionAssembler {
    public static QuestionBankQuestion toQuestionBankQuestionEntity(
        QuestionBankQuestionAddRequest questionBankQuestionAddRequest) {
        QuestionBankQuestion questionBankQuestion = new QuestionBankQuestion();
        BeanUtils.copyProperties(questionBankQuestionAddRequest, questionBankQuestion);
        return questionBankQuestion;
    }

    public static QuestionBankQuestion toQuestionBankQuestionEntity(
        QuestionBankQuestionUpdateRequest questionBankQuestionUpdateRequest) {
        QuestionBankQuestion questionBankQuestion = new QuestionBankQuestion();
        BeanUtils.copyProperties(questionBankQuestionUpdateRequest, questionBankQuestion);
        return questionBankQuestion;
    }
}

