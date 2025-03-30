package io.github.charlie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.charlie.model.entity.Question;
import io.github.charlie.service.QuestionService;
import io.github.charlie.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【sys_question(提问表)】的数据库操作Service实现
* @createDate 2025-02-05 16:51:47
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




