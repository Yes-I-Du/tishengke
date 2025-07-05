package com.tishengke.tishengkebackend.interfaces.assembler;

import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.interfaces.dto.question.QuestionAddRequest;
import com.tishengke.tishengkebackend.interfaces.dto.question.QuestionUpdateRequest;
import org.springframework.beans.BeanUtils;

/**
 * 题目相关请求转换类
 *
 * @author dmz xxx@163.com
 * @version 2025/7/5 16:32
 * @since JDK17
 */
public class QuestionAssembler {
    public static Question toQuestionEntity(QuestionAddRequest questionBankAddRequest) {
        Question question = new Question();
        BeanUtils.copyProperties(questionBankAddRequest, question);
        return question;
    }

    public static Question toQuestionEntity(QuestionUpdateRequest questionUpdateRequest) {
        Question question= new Question();
        BeanUtils.copyProperties(questionUpdateRequest, question);
        return question;
    }
}

