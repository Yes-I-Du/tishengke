package com.tishengke.tishengkebackend.interfaces.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目查询请求
 *
 * @author dmz xxx@163.com
 * @version 2025/6/29 23:09
 * @since JDK17
 */
@Data
public class QuestionQueryRequest implements Serializable {
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
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 推荐答案
     */
    private String answer;

    /**
     * 题库 id
     */
    private Long questionBankId;

    /**
     * 创建用户 id
     */
    private Long userId;

}

