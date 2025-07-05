package com.tishengke.tishengkebackend.interfaces.assembler;

import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.interfaces.dto.questionBank.QuestionBankAddRequest;
import com.tishengke.tishengkebackend.interfaces.dto.questionBank.QuestionBankUpdateRequest;
import org.springframework.beans.BeanUtils;

/**
 * 题库相关请求转换类
 *
 * @author dmz xxx@163.com
 * @version 2025/7/1 20:58
 * @since JDK17
 */
public class QuestionBankAssembler {
    public static QuestionBank toQuestionBankEntity(QuestionBankAddRequest questionBankAddRequest) {
        QuestionBank questionBank = new QuestionBank();
        BeanUtils.copyProperties(questionBankAddRequest, questionBank);
        return questionBank;
    }

    public static QuestionBank toQuestionBankEntity(QuestionBankUpdateRequest questionBankUpdateRequest) {
        QuestionBank questionBank = new QuestionBank();
        BeanUtils.copyProperties(questionBankUpdateRequest, questionBank);
        return questionBank;
    }
}

