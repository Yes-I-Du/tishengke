package com.tishengke.tishengkebackend.domain.question.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import lombok.Data;

/**
 * 题库题目
 *
 * @TableName question_bank_question
 */
@TableName(value = "question_bank_question")
@Data
public class QuestionBankQuestion implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 题库 id
     */
    private Long questionBankId;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 题目顺序（题号）
     */
    private Integer questionOrder;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 题库题目信息数据校验
     */
    public void vaildQuestionBankQuestion() {
        // 题库题目信息取得
        Long questionBankId = this.getQuestionBankId();
        Long questionId = this.getQuestionId();
        // 校验
        // 题库 id 不能为空
        ThrowUtils.throwIf(questionBankId == null, RespCode.PARAMS_ERROR, "题库 id 不能为空");
        // 题目 id 不能为空
        ThrowUtils.throwIf(questionId == null, RespCode.PARAMS_ERROR, "题目 id 不能为空");
    }
}