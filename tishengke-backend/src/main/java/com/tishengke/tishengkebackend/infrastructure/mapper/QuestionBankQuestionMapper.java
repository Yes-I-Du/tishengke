package com.tishengke.tishengkebackend.infrastructure.mapper;

import com.tishengke.tishengkebackend.domain.question.entity.QuestionBankQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Dmz
 * @description 针对表【question_bank_question(题库题目)】的数据库操作Mapper
 * @createDate 2025-06-26 21:47:13
 * @Entity com.tishengke.tishengkebackend.domain.question.entity.QuestionBankQuestion
 */
@Mapper
public interface QuestionBankQuestionMapper extends BaseMapper<QuestionBankQuestion> {
    // 查询题库关联的所有题目ID
    List<Long> selectQuestionIdsByBankId(@Param("bankId") Long bankId);

    // 查询题目是否被其他题库引用
    int countReferencesExcludingBank(@Param("questionId") Long questionId, @Param("excludeBankId") Long excludeBankId);
}




