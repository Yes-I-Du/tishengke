package com.tishengke.tishengkebackend.domain.question.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.domain.question.service.QuestionDomainService;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.common.ReviewStatusEnum;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import com.tishengke.tishengkebackend.infrastructure.repository.QuestionRepository;
import com.tishengke.tishengkebackend.interfaces.dto.question.QuestionQueryRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Dmz
 * 针对表question(题目)相关的数据库操作Service实现
 * 2025-06-29 20:29:04
 */
@Service
public class QuestionDomainServiceImpl implements QuestionDomainService {

    @Resource
    private QuestionRepository questionRepository;

    @Override
    public long addQuestion(Question question, User loginUSer) {
        boolean result = questionRepository.save(question);
        ThrowUtils.throwIf(!result, RespCode.OPERATION_ERROR, "题目创建失败");
        return question.getId();
    }

    @Override
    public void deleteQuestion(long questionId, User loginUser) {
        // 判断是否存在
        Question existQuestion = this.getById(questionId);
        ThrowUtils.throwIf(existQuestion == null, RespCode.NOT_FOUND_ERROR, "题目不存在");
        // TODO:后续拓展为用户可创建题目，权限控制
        // ....
        // ....

        // 操作数据库
        ThrowUtils.throwIf(!questionRepository.removeById(questionId), RespCode.OPERATION_ERROR, "删除失败");
    }

    @Override
    public boolean updateQuestionById(Question question) {
        return questionRepository.updateById(question);
    }

    @Override
    public Question getById(Long questionId) {
        return questionRepository.getById(questionId);
    }

    @Override
    public Page<Question> page(Page<Question> questionPage, QueryWrapper<Question> queryWrapper) {
        return questionRepository.page(questionPage, queryWrapper);
    }

    @Override
    public QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        if (questionQueryRequest == null) {
            return queryWrapper;
        }
        // 从对象中取值
        Long id = questionQueryRequest.getId();
        Long notId = questionQueryRequest.getNotId();
        String title = questionQueryRequest.getTitle();
        String searchText = questionQueryRequest.getSearchText();
        String content = questionQueryRequest.getContent();
        List<String> tagList = questionQueryRequest.getTags();
        String answer = questionQueryRequest.getAnswer();
        Long userId = questionQueryRequest.getUserId();
        String sortField = questionQueryRequest.getSortField();
        String sortOrder = questionQueryRequest.getSortOrder();

        // 从多字段中搜索
        if (StringUtils.isNotBlank(searchText)) {
            // 需要拼接查询条件
            queryWrapper.and(qw -> qw.like("title", searchText).or().like("content", searchText));
        }
        // 拼接查询条件
        // 模糊查询
        queryWrapper.like(StrUtil.isNotBlank(title), "title", title);
        queryWrapper.like(StrUtil.isNotBlank(content), "content", content);
        queryWrapper.like(StrUtil.isNotBlank(answer), "answer", answer);
        // JSON 数组查询
        if (CollUtil.isNotEmpty(tagList)) {
            for (String tag : tagList) {
                queryWrapper.like("tags", "\"" + tag + "\"");
            }
        }
        // 精确查询
        queryWrapper.eq(ObjUtil.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjUtil.isNotEmpty(notId), "notId", notId);
        queryWrapper.eq(ObjUtil.isNotEmpty(userId), "userId", userId);

        // 排序
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("ascend"), sortField);
        return queryWrapper;
    }

    @Override
    public void setReviewStatus(Question question, User loginUser) {
        // 用户为管理员则自动过审,并添加审核信息
        if (loginUser.isAdmin()) {
            question.setReviewStatus(ReviewStatusEnum.PASS.getValue());
            question.setReviewTime(new Date());
            question.setReviewerId(loginUser.getId());
            question.setReviewMessage("管理员自动审核通过");
        } else {
            // 否则设置为待审核
            question.setReviewStatus(ReviewStatusEnum.REVIEWING.getValue());
        }
    }
}




