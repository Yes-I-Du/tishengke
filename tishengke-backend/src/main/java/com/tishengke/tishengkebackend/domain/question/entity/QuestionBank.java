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
 * 题库
 *
 * @TableName question_bank
 */
@TableName(value = "question_bank")
@Data
public class QuestionBank implements Serializable {
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
     * 优先级
     */
    private Integer priority;

    /**
     * 浏览量
     */
    private Integer viewNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 题库信息校验
     *
     * @param addFlag 是否是新增
     */
    public void vaildQuestionBank(boolean addFlag) {
        // 题库信息取得
        String title = this.getTitle();
        String description = this.getDescription();
        String picture = this.getPicture();
        // 新增题库的场合
        if (addFlag) {
            // 校验
            // 题库标题不能为空
            ThrowUtils.throwIf(StrUtil.isBlank(title), RespCode.PARAMS_ERROR, "题库标题不能为空");
            // 题库描述不能为空
            ThrowUtils.throwIf(StrUtil.isBlank(description), RespCode.PARAMS_ERROR, "题库描述不能为空");
            // 题库图片不能为空
            ThrowUtils.throwIf(StrUtil.isBlank(picture), RespCode.PARAMS_ERROR, "题库图片不能为空");
        }
        // 更新题库的场合
        // 题库名称过长
        ThrowUtils.throwIf(title.length() > QuestionConstant.QUESTIONBANK_TITTLE_LENGTH, RespCode.PARAMS_ERROR,
            "题库名称过长,最大长度为10");
        // 题库描述过长
        ThrowUtils.throwIf(description.length() > QuestionConstant.QUESTIONBANK_DESCRIPTION_LENGTH,
            RespCode.PARAMS_ERROR, "题库描述过长,最大长度为20");
    }
}