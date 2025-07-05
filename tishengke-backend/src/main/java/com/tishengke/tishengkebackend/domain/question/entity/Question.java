package com.tishengke.tishengkebackend.domain.question.entity;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.tishengke.tishengkebackend.domain.question.constant.QuestionConstant;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import lombok.Data;

/**
 * 题目
 *
 * @TableName question
 */
@TableName(value = "question")
@Data
public class Question implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private String tags;

    /**
     * 推荐答案
     */
    private String answer;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 状态：0-待审核, 1-通过, 2-拒绝
     */
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    private String reviewMessage;

    /**
     * 审核人 id
     */
    private Long reviewerId;

    /**
     * 审核时间
     */
    private Date reviewTime;

    /**
     * 浏览量
     */
    private Integer viewNum;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 题目来源
     */
    private String source;

    /**
     * 仅会员可见（1 表示仅会员可见）
     */
    private Integer needVip;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 题目信息校验
     *
     * @param addFlag 是否是新增
     */
    public void vaildQuestion(boolean addFlag) {
        // 题目信息取得
        String title = this.getTitle();
        String content = this.getContent();
        String answer = this.getAnswer();
        String source = this.getSource();
        // 新增题库的场合
        if (addFlag) {
            // 校验
            // 题目标题不能为空
            ThrowUtils.throwIf(StrUtil.isBlank(title), RespCode.PARAMS_ERROR, "题目标题不能为空");
            // 题目内容不能为空
            ThrowUtils.throwIf(StrUtil.isBlank(content), RespCode.PARAMS_ERROR, "题目描述不能为空");
            // 题目答案不能为空
            ThrowUtils.throwIf(StrUtil.isBlank(answer), RespCode.PARAMS_ERROR, "题目答案不能为空");
        }
        // 更新题库的场合
        // 题库名称过长
        ThrowUtils.throwIf(title.length() > QuestionConstant.QUESTION_TITTLE_LENGTH, RespCode.PARAMS_ERROR,
            "题目标题过长,最大长度为60");
        // 题库描述过长
        ThrowUtils.throwIf(content.length() > QuestionConstant.QUESTION_CONTENT_LENGTH, RespCode.PARAMS_ERROR,
            "题目内容过长,最大长度为600");
        // 题库描述过长
        ThrowUtils.throwIf(answer.length() > QuestionConstant.QUESTION_ANSWER_LENGTH, RespCode.PARAMS_ERROR,
            "题目答案过长,最大长度为10240");
        // 题库描述过长
        ThrowUtils.throwIf(source != null && source.length() > QuestionConstant.QUESTION_SOURCE_LENGTH,
            RespCode.PARAMS_ERROR, "题目来源过长,最大长度为40");
    }
}