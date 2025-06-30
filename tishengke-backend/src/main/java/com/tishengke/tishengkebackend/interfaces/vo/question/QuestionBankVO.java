package com.tishengke.tishengkebackend.interfaces.vo.question;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.interfaces.vo.user.UserVO;
import lombok.Data;
import org.apache.poi.ss.usermodel.Picture;

import java.io.Serializable;
import java.util.Date;

/**
 * 题库视图信息封装
 */
@Data
public class QuestionBankVO implements Serializable {

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
     * 浏览量
     */
    private Integer viewNum;

    /**
     * 创建用户信息
     */
    private UserVO user;

    /**
     * 题库里的题目列表（分页）
     */
    Page<QuestionVO> questionPage;

    /**
     * 封装类 -> 题库对象
     */
    public static QuestionBank voToQuestionBank(QuestionBankVO questionBankVO) {
        if (questionBankVO == null) {
            return null;
        }
        QuestionBank questionBank = new QuestionBank();
        BeanUtil.copyProperties(questionBankVO, questionBank);
        return questionBank;
    }

    /**
     * 题库对象 -> 封装类
     */
    public static QuestionBankVO QuestionBankToVo(QuestionBank questionBank) {
        if (questionBank == null) {
            return null;
        }
        QuestionBankVO questionBankVO = new QuestionBankVO();
        BeanUtil.copyProperties(questionBank, questionBankVO);
        return questionBankVO;
    }
}