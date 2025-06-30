package com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新题库题目关联请求
 *
 * @author dmz xxx@163.com
 * @version 2025/6/29 23:14
 * @since JDK17
 */
@Data
public class QuestionBankQuestionUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 题库 id
     */
    private Long questionBankId;

    /**
     * 题目 id
     */
    private Long questionId;
}

