package com.tishengke.tishengkebackend.interfaces.dto.questionBank;

import com.tishengke.tishengkebackend.infrastructure.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 题库查询请求
 *
 * @author dmz xxx@163.com
 * @version 2025/6/29 23:09
 * @since JDK17
 */
@Data
public class QuestionBankQueryRequest extends PageRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * id
     */
    private Long notId;

    /**
     * 搜索词
     */
    private String searchText;

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

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 是否要关联查询题目列表
     */
    private boolean needQueryQuestionList;

}

