package com.tishengke.tishengkebackend.domain.question.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tishengke.tishengkebackend.domain.question.entity.Question;
import com.tishengke.tishengkebackend.infrastructure.mapper.QuestionMapper;
import com.tishengke.tishengkebackend.infrastructure.repository.QuestionRepository;
import org.springframework.stereotype.Service;

/**
 * 针对表【question(题目)】的仓储接口实现
 */
@Service
public class QuestionRepositoryImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionRepository {

}




