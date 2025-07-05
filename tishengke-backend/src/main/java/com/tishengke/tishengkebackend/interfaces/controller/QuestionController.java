package com.tishengke.tishengkebackend.interfaces.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.application.service.QuestionApplicationService;
import com.tishengke.tishengkebackend.application.service.UserApplicationService;
import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.domain.user.constant.UserConstant;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.BaseResponse;
import com.tishengke.tishengkebackend.infrastructure.common.DeleteRequest;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.common.ResultUtils;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import com.tishengke.tishengkebackend.interfaces.assembler.QuestionAssembler;
import com.tishengke.tishengkebackend.interfaces.dto.question.QuestionAddRequest;
import com.tishengke.tishengkebackend.interfaces.dto.question.QuestionQueryRequest;
import com.tishengke.tishengkebackend.interfaces.dto.question.QuestionUpdateRequest;
import com.tishengke.tishengkebackend.interfaces.vo.question.QuestionVO;
import com.tishengke.tishengkebackend.shared.auth.annotation.SaUserCheckRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 针对题目的ControllerApi接口
 *
 * @author Dmz Email:  * @since 2025/07/05 16:27
 */
@RestController
@Slf4j
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private UserApplicationService userApplicationService;

    @Resource
    private QuestionApplicationService questionApplicationService;

    // region 增删改
    /*
     * 创建题目
     *
     * @param questionAddRequest 题目信息
     * @return 题目id
     */
    @PostMapping("/add")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addQuestion(QuestionAddRequest questionAddRequest,
        HttpServletRequest httpServletRequest) {
        // 请求参数
        ThrowUtils.throwIf(questionAddRequest == null, RespCode.PARAMS_ERROR, "请求失败");
        Question question = QuestionAssembler.toQuestionEntity(questionAddRequest);
        // 标签(tags)列表处理
        List<String> tagList = questionAddRequest.getTags();
        if (tagList != null) {
            question.setTags(JSONUtil.toJsonStr(tagList));
        }
        User loginUser = userApplicationService.getLoginUser(httpServletRequest);
        return ResultUtils.success(questionApplicationService.addQuestion(question, loginUser));
    }

    /**
     * 题目基础信息更新
     *
     * @param questionUpdateRequest 题目更新信息
     * @return 更新结果
     */
    @PostMapping("/update")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateQuestion(QuestionUpdateRequest questionUpdateRequest) {
        // 请求参数校验
        ThrowUtils.throwIf(questionUpdateRequest == null || questionUpdateRequest.getId() <= 0, RespCode.PARAMS_ERROR,
            "请求失败");
        // 题目存在校验
        questionApplicationService.getQuestionById(questionUpdateRequest.getId());
        // 数据对象转换
        Question question = QuestionAssembler.toQuestionEntity(questionUpdateRequest);
        // 标签(tags)列表处理
        List<String> tagList = questionUpdateRequest.getTags();
        if (tagList != null) {
            question.setTags(JSONUtil.toJsonStr(tagList));
        }
        // 题目数据更新
        questionApplicationService.updateQuestion(question);

        return ResultUtils.success(true);
    }

    /**
     * 题目删除
     *
     * @param deleteRequest      题目删除信息
     * @param httpServletRequest 请求信息
     * @return 删除结果
     */
    @PostMapping("/delete")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteQuestion(@RequestBody DeleteRequest deleteRequest,
        HttpServletRequest httpServletRequest) {
        // 请求参数校验
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() <= 0, RespCode.PARAMS_ERROR, "请求失败");
        // 题目存在校验
        questionApplicationService.getQuestionById(deleteRequest.getId());
        // 题目删除
        questionApplicationService.deleteQuestion(deleteRequest.getId(),
            userApplicationService.getLoginUser(httpServletRequest));

        return ResultUtils.success(true);
    }
    // endregion

    // region 查询
    /*
     * 管理员权限
     * 题目——根据Id查询题目
     * @param id 题目id
     * @return 题目信息
     */
    @GetMapping("/get/{id}")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Question> getQuestionById(@PathVariable("id") Long id) {
        return ResultUtils.success(questionApplicationService.getQuestionById(id));
    }

    /*
     * 题目信息查询(包装类信息查询)
     *
     * @param id 题目id
     * @return 题目脱敏信息
     */
    @GetMapping("/get/vo")
    public BaseResponse<QuestionVO> getQuestionVOById(Long id, HttpServletRequest httpServletRequest) {
        // 参数校验
        ThrowUtils.throwIf(id == null || id <= 0, RespCode.PARAMS_ERROR, "请求参数错误");
        Question question = questionApplicationService.getQuestionById(id);

        // 获取题目封装类信息
        QuestionVO questionVO = questionApplicationService.getQuestionVO(question, httpServletRequest);

        return ResultUtils.success(questionVO);
    }

    /*
     * 管理员权限
     * 分页获取题目列表
     *
     * @param QuestionQueryRequest 题目信息查询请求
     * @return 题目信息
     */
    @PostMapping("/list/page")
    @SaUserCheckRole(UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<Question>> listQuestionByPage(@RequestBody QuestionQueryRequest questionQueryRequest) {
        // 请求参数校验
        ThrowUtils.throwIf(questionQueryRequest == null, RespCode.PARAMS_ERROR, "请求参数错误");

        // 分页获取题目信息列表
        Page<Question> QuestionPage = questionApplicationService.getQuestionPage(questionQueryRequest);
        return ResultUtils.success(QuestionPage);
    }

    /**
     * 分页获取题目信息列表（封装类）
     *
     * @param questionQueryRequest 题目信息查询请求
     * @param request              http请求信息
     * @return 题目信息
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<QuestionVO>> listQuestionVOByPage(@RequestBody QuestionQueryRequest questionQueryRequest,
        HttpServletRequest request) {
        long pageSize = questionQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(pageSize > 20, RespCode.PARAMS_ERROR);

        // 查询数据库
        Page<Question> QuestionPage = questionApplicationService.getQuestionPage(questionQueryRequest);

        // 获取封装类
        return ResultUtils.success(questionApplicationService.getQuestionVOPage(QuestionPage, request));
    }

    // endregion

}

