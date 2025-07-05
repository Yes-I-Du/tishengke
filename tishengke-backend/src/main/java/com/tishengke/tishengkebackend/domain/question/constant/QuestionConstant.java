package com.tishengke.tishengkebackend.domain.question.constant;

/**
 * 题目相关共同变量
 *
 * @author dmz xxx@163.com
 * @version 2025/7/1 20:43
 * @since JDK17
 */
public interface QuestionConstant {
    /* 允许题库标题最大长度 */
    public static final int QUESTIONBANK_TITTLE_LENGTH = 20;

    /* 允许题库描述最大长度 */
    public static final int QUESTIONBANK_DESCRIPTION_LENGTH = 40;

    /* 允许题目标题最大长度 */
    public static final int QUESTION_TITTLE_LENGTH = 60;

    /* 允许题目内容最大长度 */
    public static final int QUESTION_CONTENT_LENGTH = 600;

    /* 允许题目答案最大长度 */
    public static final int QUESTION_ANSWER_LENGTH = 10240;

    /* 允许题目来源最大长度 */
    public static final int QUESTION_SOURCE_LENGTH = 40;
}

