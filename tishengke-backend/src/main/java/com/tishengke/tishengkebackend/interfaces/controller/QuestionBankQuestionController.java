package com.tishengke.tishengkebackend.interfaces.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.application.service.QuestionBankQuestionApplicationService;
import com.tishengke.tishengkebackend.application.service.UserApplicationService;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBankQuestion;
import com.tishengke.tishengkebackend.domain.user.constant.UserConstant;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.BaseResponse;
import com.tishengke.tishengkebackend.infrastructure.common.DeleteRequest;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.common.ResultUtils;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import com.tishengke.tishengkebackend.interfaces.assembler.QuestionBankQuestionAssembler;
import com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion.QuestionBankQuestionAddRequest;
import com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion.QuestionBankQuestionQueryRequest;
import com.tishengke.tishengkebackend.interfaces.dto.questionBankQuestion.QuestionBankQuestionUpdateRequest;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionBankQuestionVO;
import com.tishengke.tishengkebackend.shared.auth.annotation.SaUserCheckRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 针对题库题目关系的ControllerApi接口
 *
 * @author Dmz Email:  * @since 2025/07/06 00:41
 */
@RestController
@Slf4j
@RequestMapping("/questionBankQuestion")
public class QuestionBankQuestionController {
    @Resource
    private UserApplicationService userApplicationService;

    @Resource
    private QuestionBankQuestionApplicationService questionBankQuestionApplicationService;

    // region 增删改
    /*
     * 创建题库题目关联信息
     *
     * @param questionBankQuestionAddRequest 题库题目关联信息创建请求
     * @return 题库题目关联信息id
     */
    @PostMapping("/add")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addQuestionBankQuestion(QuestionBankQuestionAddRequest questionBankQuestionAddRequest,
        HttpServletRequest httpServletRequest) {
        // 请求参数
        ThrowUtils.throwIf(questionBankQuestionAddRequest == null, RespCode.PARAMS_ERROR, "请求失败");
        QuestionBankQuestion questionBankQuestion =
            QuestionBankQuestionAssembler.toQuestionBankQuestionEntity(questionBankQuestionAddRequest);
        User loginUser = userApplicationService.getLoginUser(httpServletRequest);
        return ResultUtils.success(
            questionBankQuestionApplicationService.addQuestionBankQuestion(questionBankQuestion, loginUser));
    }

    /**
     * 更新题库题目关联信息
     *
     * @param questionBankQuestionUpdateRequest 题库题目关联信息更新请求
     * @return 更新结果
     */
    @PostMapping("/update")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateQuestionBankQuestion(
        QuestionBankQuestionUpdateRequest questionBankQuestionUpdateRequest) {
        // 请求参数校验
        ThrowUtils.throwIf(questionBankQuestionUpdateRequest == null || questionBankQuestionUpdateRequest.getId() <= 0,
            RespCode.PARAMS_ERROR, "请求失败");
        // 数据对象转换
        QuestionBankQuestion questionBankQuestion =
            QuestionBankQuestionAssembler.toQuestionBankQuestionEntity(questionBankQuestionUpdateRequest);
        // 题库数据更新
        questionBankQuestionApplicationService.updateQuestionBankQuestion(questionBankQuestion);

        return ResultUtils.success(true);
    }

    /**
     * 删除题库题目关联信息
     *
     * @param deleteRequest      题库题目关联信息删除请求
     * @param httpServletRequest 请求信息
     * @return 删除结果
     */
    @PostMapping("/delete")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteQuestionBankQuestion(@RequestBody DeleteRequest deleteRequest,
        HttpServletRequest httpServletRequest) {
        // 请求参数校验
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() <= 0, RespCode.PARAMS_ERROR, "请求失败");
        // 题库删除
        questionBankQuestionApplicationService.deleteQuestionBankQuestion(deleteRequest.getId(),
            userApplicationService.getLoginUser(httpServletRequest));

        return ResultUtils.success(true);
    }
    // endregion

    // region 查询
    /*
     * 管理员权限
     * 题库题目关联信息——根据Id查询题库题目关联信息
     * @param id 题库题目关联信息id
     * @return 题库信息
     */
    @GetMapping("/get/{id}")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<QuestionBankQuestion> getQuestionBankQuestionById(@PathVariable("id") Long id) {
        return ResultUtils.success(questionBankQuestionApplicationService.getQuestionBankQuestionById(id));
    }

    /*
     * 题库题目关联信息查询(包装类信息查询)
     *
     * @param id 题库id
     * @return 题库题目关联信息(包装类信息)
     */
    @GetMapping("/get/vo")
    public BaseResponse<QuestionBankQuestionVO> getQuestionBankQuestionVOById(Long id,
        HttpServletRequest httpServletRequest) {
        // 参数校验
        ThrowUtils.throwIf(id == null || id <= 0, RespCode.PARAMS_ERROR, "请求参数错误");
        QuestionBankQuestion questionBankQuestion =
            questionBankQuestionApplicationService.getQuestionBankQuestionById(id);

        // 获取题目封装类信息
        QuestionBankQuestionVO questionBankQuestionVO =
            questionBankQuestionApplicationService.getQuestionBankQuestionVOById(questionBankQuestion,
                httpServletRequest);

        return ResultUtils.success(questionBankQuestionVO);
    }

    /*
     * 管理员权限
     * 分页获取题库题目关联信息列表
     *
     * @param questionBankQuestionQueryRequest 题库题目关联信息查询请求
     * @return 题库题目关联页信息
     */
    @PostMapping("/list/page")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<QuestionBankQuestion>> listQuestionBankQuestionByPage(
        @RequestBody QuestionBankQuestionQueryRequest questionBankQuestionQueryRequest) {
        ThrowUtils.throwIf(questionBankQuestionQueryRequest == null, RespCode.PARAMS_ERROR, "请求参数错误");

        Page<QuestionBankQuestion> questionBankQuestionPage =
            questionBankQuestionApplicationService.getQuestionBankQuestionPage(questionBankQuestionQueryRequest);
        return ResultUtils.success(questionBankQuestionPage);
    }

    /**
     * 分页获取题库题目关联信息列表（封装类）
     *
     * @param questionBankQuestionQueryRequest 题库题目关联信息查询请求
     * @param request                          http请求信息
     * @return 题库题目关联页信息
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<QuestionBankQuestionVO>> listQuestionBankVOByPage(
        @RequestBody QuestionBankQuestionQueryRequest questionBankQuestionQueryRequest, HttpServletRequest request) {
        long current = questionBankQuestionQueryRequest.getCurrent();
        long pageSize = questionBankQuestionQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, RespCode.PARAMS_ERROR);

        // 查询数据库
        Page<QuestionBankQuestion> questionBankQuestionPage =
            questionBankQuestionApplicationService.getQuestionBankQuestionPage(questionBankQuestionQueryRequest);

        // 获取封装类
        return ResultUtils.success(
            questionBankQuestionApplicationService.getQuestionBankQuestionVOPage(questionBankQuestionPage, request));
    }

    // endregion
}

