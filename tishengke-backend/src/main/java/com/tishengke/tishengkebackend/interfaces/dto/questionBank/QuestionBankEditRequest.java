package com.tishengke.tishengkebackend.interfaces.dto.questionBank;

import lombok.Data;

import java.io.Serializable;

/**
 * 题库编辑请求
 *
 * @author dmz xxx@163.com
 * @version 2025/6/29 23:05
 * @since JDK17
 */
@Data
public class QuestionBankEditRequest implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private Long id;

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

