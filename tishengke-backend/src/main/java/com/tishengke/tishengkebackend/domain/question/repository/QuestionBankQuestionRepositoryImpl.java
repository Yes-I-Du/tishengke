package com.tishengke.tishengkebackend.domain.question.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBankQuestion;
import com.tishengke.tishengkebackend.infrastructure.mapper.QuestionBankQuestionMapper;
import com.tishengke.tishengkebackend.infrastructure.repository.QuestionBankQuestionRepository;
import org.springframework.stereotype.Service;

/**
 * @author Dmz
 * @description 针对表【question_bank_question(题库题目)】的数据库仓储接口实现
 * @createDate 2025-06-26 21:47:13
 */
@Service
public class QuestionBankQuestionRepositoryImpl extends ServiceImpl<QuestionBankQuestionMapper, QuestionBankQuestion>
    implements QuestionBankQuestionRepository {

}




