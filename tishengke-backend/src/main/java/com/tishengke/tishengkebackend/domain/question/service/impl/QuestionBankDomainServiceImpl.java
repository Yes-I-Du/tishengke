package com.tishengke.tishengkebackend.domain.question.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBankQuestion;
import com.tishengke.tishengkebackend.domain.question.service.QuestionBankDomainService;
import com.tishengke.tishengkebackend.domain.user.entity.User;
import com.tishengke.tishengkebackend.infrastructure.common.RespCode;
import com.tishengke.tishengkebackend.infrastructure.common.ReviewStatusEnum;
import com.tishengke.tishengkebackend.infrastructure.exception.ThrowUtils;
import com.tishengke.tishengkebackend.infrastructure.mapper.QuestionBankQuestionMapper;
import com.tishengke.tishengkebackend.infrastructure.repository.QuestionBankRepository;
import com.tishengke.tishengkebackend.interfaces.dto.questionBank.QuestionBankQueryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dmz Email:  *
 * @description 针对【questionBank(题目)】相关的数据库操作Service
 * @since 2025/06/29 23:47
 */
@Service
@Slf4j
public class QuestionBankDomainServiceImpl implements QuestionBankDomainService {
    @Resource
    private QuestionBankRepository questionBankRepository;

    @Resource
    private QuestionBankQuestionMapper questionBankQuestionMapper;

    @Override
    public long addQuestionBank(QuestionBank questionBank, User loginUSer) {
        boolean result = questionBankRepository.save(questionBank);
        ThrowUtils.throwIf(!result, RespCode.OPERATION_ERROR, "题库创建失败");
        return questionBank.getId();
    }

    @Override
    @Transactional
    public void deleteQuestionBank(long questionBankId, User loginUser) {
        // 判断是否存在
        QuestionBank existQuestionBank = this.getById(questionBankId);
        ThrowUtils.throwIf(existQuestionBank == null, RespCode.NOT_FOUND_ERROR, "题库不存在");
        // TODO:后续拓展为用户可创建题库，权限控制
        // ....
        // ....

        // 操作数据库
        ThrowUtils.throwIf(!questionBankRepository.removeById(questionBankId), RespCode.OPERATION_ERROR, "删除失败");

        // 触发异步删除题目
        this.asyncDeleteOrphanQuestions(questionBankId);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 新事务
    public void asyncDeleteOrphanQuestions(long questionBankId) {
        // 1. 获取该题库关联的所有题目ID
        List<Long> questionIds = questionBankQuestionMapper.selectQuestionIdsByBankId(questionBankId);

        // 2. 删除该题库的关系记录
        LambdaQueryWrapper<QuestionBankQuestion> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(QuestionBankQuestion::getQuestionBankId, questionBankId);
        questionBankQuestionMapper.delete(wrapper);

        // 3. 筛选未被其他题库引用的题目
        List<Long> orphanQuestionIds = new ArrayList<>();
        for (Long questionId : questionIds) {
            int refCount = questionBankQuestionMapper.countReferencesExcludingBank(questionId, questionBankId);
            if (refCount == 0) { // 题目未被其他题库引用
                orphanQuestionIds.add(questionId);
            }
        }

        // 4. 批量删除孤儿题目
        if (!orphanQuestionIds.isEmpty()) {
            questionBankQuestionMapper.deleteBatchIds(orphanQuestionIds);
        }
    }

    @Override
    public boolean updateQuestionBankById(QuestionBank questionBank) {
        return questionBankRepository.updateById(questionBank);
    }

    @Override
    public QuestionBank getById(Long questionBankId) {

        return questionBankRepository.getById(questionBankId);
    }

    @Override
    public Page<QuestionBank> page(Page<QuestionBank> questionBankPage, QueryWrapper<QuestionBank> queryWrapper) {
        return questionBankRepository.page(questionBankPage, queryWrapper);
    }

    @Override
    public QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest) {
        QueryWrapper<QuestionBank> queryWrapper = new QueryWrapper<>();
        if (questionBankQueryRequest == null) {
            return queryWrapper;
        }
        // 从对象中取值
        Long id = questionBankQueryRequest.getId();
        Long notId = questionBankQueryRequest.getNotId();
        String title = questionBankQueryRequest.getTitle();
        String searchText = questionBankQueryRequest.getSearchText();
        String description = questionBankQueryRequest.getDescription();
        String picture = questionBankQueryRequest.getPicture();
        Long userId = questionBankQueryRequest.getUserId();
        String sortField = questionBankQueryRequest.getSortField();
        String sortOrder = questionBankQueryRequest.getSortOrder();

        // 从多字段中搜索
        if (StringUtils.isNotBlank(searchText)) {
            // 需要拼接查询条件
            queryWrapper.and(qw -> qw.like("title", searchText).or().like("description", searchText));
        }
        // 拼接查询条件
        // 模糊查询
        queryWrapper.like(StrUtil.isNotBlank(title), "title", title);
        queryWrapper.like(ObjUtil.isNotEmpty(description), "description", description);
        // 精确查询
        queryWrapper.eq(ObjUtil.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjUtil.isNotEmpty(notId), "notId", notId);
        queryWrapper.eq(ObjUtil.isNotEmpty(picture), "picture", picture);
        queryWrapper.eq(ObjUtil.isNotEmpty(userId), "userId", userId);

        // 排序
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("ascend"), sortField);
        return queryWrapper;
    }

    @Override
    public void setReviewStatus(QuestionBank questionBank, User loginUser) {
        // 用户为管理员则自动过审,并添加审核信息
        if (loginUser.isAdmin()) {
            questionBank.setReviewStatus(ReviewStatusEnum.PASS.getValue());
            questionBank.setReviewTime(new Date());
            questionBank.setReviewerId(loginUser.getId());
            questionBank.setReviewMessage("管理员自动审核通过");
        } else {
            // 否则设置为待审核
            questionBank.setReviewStatus(ReviewStatusEnum.REVIEWING.getValue());
        }
    }
}

