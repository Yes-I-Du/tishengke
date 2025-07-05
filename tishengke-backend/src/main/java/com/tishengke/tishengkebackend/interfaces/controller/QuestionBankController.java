package com.tishengke.tishengkebackend.interfaces.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.application.service.QuestionApplicationService;
import com.tishengke.tishengkebackend.application.service.QuestionBankApplicationService;
import com.tishengke.tishengkebackend.application.service.UserApplicationService;
import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.domain.user.constant.UserConstant;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.BaseResponse;
import com.tishengke.tishengkebackend.infrastructure.common.DeleteRequest;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.common.ResultUtils;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import com.tishengke.tishengkebackend.interfaces.assembler.QuestionBankAssembler;
import com.tishengke.tishengkebackend.interfaces.dto.question.QuestionQueryRequest;
import com.tishengke.tishengkebackend.interfaces.dto.questionBank.QuestionBankAddRequest;
import com.tishengke.tishengkebackend.interfaces.dto.questionBank.QuestionBankQueryRequest;
import com.tishengke.tishengkebackend.interfaces.dto.questionBank.QuestionBankUpdateRequest;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionBankVO;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionVO;
import com.tishengke.tishengkebackend.shared.auth.annotation.SaUserCheckRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 针对题库的ControllerApi接口
 *
 * @author dmz xxx@163.com
 * @version 2025/7/1 21:42
 * @since JDK17
 */
@RestController
@Slf4j
@RequestMapping("/questionBank")
public class QuestionBankController {
    @Resource
    private UserApplicationService userApplicationService;

    @Resource
    private QuestionBankApplicationService questionBankApplicationService;

    @Resource
    private QuestionApplicationService questionApplicationService;

    // region 增删改
    /*
     * 创建题库
     *
     * @param QuestionBankAddRequest 题库信息
     * @return 题库id
     */
    @PostMapping("/add")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addQuestionBank(QuestionBankAddRequest questionBankAddRequest,
        HttpServletRequest httpServletRequest) {
        // 请求参数
        ThrowUtils.throwIf(questionBankAddRequest == null, RespCode.PARAMS_ERROR, "请求失败");
        QuestionBank questionBank = QuestionBankAssembler.toQuestionBankEntity(questionBankAddRequest);
        User loginUser = userApplicationService.getLoginUser(httpServletRequest);
        return ResultUtils.success(questionBankApplicationService.addQuestionBank(questionBank, loginUser));
    }

    /**
     * 题库基础信息更新
     *
     * @param questionBankUpdateRequest 题库更新信息
     * @return 更新结果
     */
    @PostMapping("/update")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateQuestionBank(QuestionBankUpdateRequest questionBankUpdateRequest) {
        // 请求参数校验
        ThrowUtils.throwIf(questionBankUpdateRequest == null || questionBankUpdateRequest.getId() <= 0,
            RespCode.PARAMS_ERROR, "请求失败");
        // 题库存在校验
        questionBankApplicationService.getQuestionBankById(questionBankUpdateRequest.getId());
        // 数据对象转换
        QuestionBank questionBank = QuestionBankAssembler.toQuestionBankEntity(questionBankUpdateRequest);
        // 题库数据更新
        questionBankApplicationService.updateQuestionBank(questionBank);

        return ResultUtils.success(true);
    }

    /**
     * 题库删除
     *
     * @param deleteRequest      题库删除信息
     * @param httpServletRequest 请求信息
     * @return 删除结果
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteQuestionBank(@RequestBody DeleteRequest deleteRequest,
        HttpServletRequest httpServletRequest) {
        // 请求参数校验
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() <= 0, RespCode.PARAMS_ERROR, "请求失败");
        // 题库存在校验
        questionBankApplicationService.getQuestionBankById(deleteRequest.getId());
        // 题库删除
        questionBankApplicationService.deleteQuestionBank(deleteRequest.getId(),
            userApplicationService.getLoginUser(httpServletRequest));

        return ResultUtils.success(true);
    }
    // endregion

    // region 查询
    /*
     * 管理员权限
     * 题库——根据Id查询题库
     * @param id 题库id
     * @return 题库信息
     */
    @GetMapping("/get/{id}")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<QuestionBank> getQuestionBankById(@PathVariable("id") Long id) {
        return ResultUtils.success(questionBankApplicationService.getQuestionBankById(id));
    }

    /*
     * 题库信息查询(包装类信息查询)
     *
     * @param id 题库id
     * @return 题库脱敏信息
     */
    @GetMapping("/get/vo")
    public BaseResponse<QuestionBankVO> getQuestionBankVOById(
        @RequestBody QuestionBankQueryRequest questionBankQueryRequest, HttpServletRequest httpServletRequest) {
        // 参数校验
        ThrowUtils.throwIf(
            questionBankQueryRequest == null || questionBankQueryRequest.getId() == null || questionBankQueryRequest.getId() <= 0,
            RespCode.PARAMS_ERROR, "请求参数错误");
        QuestionBank questionBank = questionBankApplicationService.getQuestionBankById(questionBankQueryRequest.getId());

        // 获取题库封装类信息
        QuestionBankVO  questionBankVO = questionBankApplicationService.getQuestionBankVO(questionBank, httpServletRequest);

        // 查询题库时需要查询该题库下的题目列表
        boolean questionQueryFlag = questionBankQueryRequest.isNeedQueryQuestionList();
        if (questionQueryFlag) {
            QuestionQueryRequest questionQueryRequest = new QuestionQueryRequest();
            questionQueryRequest.setQuestionBankId(questionBankQueryRequest.getId());

            // 添加查询参数
            questionQueryRequest.setCurrent(questionBankQueryRequest.getCurrent());
            questionQueryRequest.setPageSize(questionBankQueryRequest.getPageSize());

            // 获取题库下的题目列表
            Page<Question> questionPage = questionApplicationService.getQuestionPage(questionQueryRequest);
            Page<QuestionVO> questionVOPage = questionApplicationService.getQuestionVOPage(questionPage, httpServletRequest);
            questionBankVO.setQuestionPage(questionVOPage);

        }

        return ResultUtils.success(questionBankVO);
    }

    /*
     * 管理员权限
     * 分页获取题库列表
     *
     * @param questionBankQueryRequest 题库信息查询请求
     * @return 题库信息
     */
    @PostMapping("/list/page")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<QuestionBank>> listQuestionBankByPage(
        @RequestBody QuestionBankQueryRequest questionBankQueryRequest) {
        ThrowUtils.throwIf(questionBankQueryRequest == null, RespCode.PARAMS_ERROR, "请求参数错误");
        long current = questionBankQueryRequest.getCurrent();
        long pageSize = questionBankQueryRequest.getPageSize();
        Page<QuestionBank> questionBankPage =
            questionBankApplicationService.getQuestionBankPage(new Page<>(current, pageSize),
                questionBankApplicationService.getQueryWrapper(questionBankQueryRequest));
        return ResultUtils.success(questionBankPage);
    }

    /**
     * 分页获取题库信息列表（封装类）
     *
     * @param questionBankQueryRequest 题库信息查询请求
     * @param request                  http请求信息
     * @return 题库信息
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<QuestionBankVO>> listQuestionBankVOByPage(
        @RequestBody QuestionBankQueryRequest questionBankQueryRequest, HttpServletRequest request) {
        long current = questionBankQueryRequest.getCurrent();
        long pageSize = questionBankQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, RespCode.PARAMS_ERROR);

        // 查询数据库
        Page<QuestionBank> questionBankPage =
            questionBankApplicationService.getQuestionBankPage(new Page<>(current, pageSize),
                questionBankApplicationService.getQueryWrapper(questionBankQueryRequest));

        // 获取封装类
        return ResultUtils.success(questionBankApplicationService.getQuestionBankVOPage(questionBankPage, request));
    }

    // endregion

}

