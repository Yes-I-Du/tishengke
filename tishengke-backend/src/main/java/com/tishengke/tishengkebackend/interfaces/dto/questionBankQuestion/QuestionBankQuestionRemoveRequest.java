package com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion;

import lombok.Data;

import java.io.Serializable;

/**
 * 移除题目题库关系请求
 *
 * @author dmz xxx@163.com
 * @version 2025/6/29 23:39
 * @since JDK17
 */
@Data
public class QuestionBankQuestionRemoveRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题库 id
     */
    private Long questionBankId;

    /**
     * 题目 id
     */
    private Long questionId;
}

