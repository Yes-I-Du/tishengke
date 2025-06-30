package com.tishengke.tishengkebackend.interfaces.dto.questionBank;

import lombok.Data;

import java.io.Serializable;

/**
 * 题库创建请求
 *
 * @author dmz xxx@163.com
 * @version 2025/6/29 23:01
 * @since JDK17
 */
@Data
public class QuestionBankAddRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片
     */
    private String picture;

}

