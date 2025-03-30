package io.github.charlie.convert;

import io.github.charlie.model.entity.Question;
import io.github.charlie.model.form.QuestionForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 提问转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionConvert {
    Question toEntity(QuestionForm form);
}
