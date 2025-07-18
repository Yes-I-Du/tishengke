package com.tishengke.tishengkebackend.domain.question.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tishengke.tishengkebackend.domain.question.entity.QuestionBank;
import com.tishengke.tishengkebackend.infrastructure.mapper.QuestionBankMapper;
import com.tishengke.tishengkebackend.infrastructure.repository.QuestionBankRepository;
import org.springframework.stereotype.Service;

/**
 * 针对表【questionbank(题库)】的仓储接口实现
 */
@Service
public class QuestionBankRepositoryImpl extends ServiceImpl<QuestionBankMapper, QuestionBank>
    implements QuestionBankRepository {
}
