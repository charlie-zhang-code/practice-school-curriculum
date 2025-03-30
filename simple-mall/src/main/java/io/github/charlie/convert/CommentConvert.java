package io.github.charlie.convert;

import io.github.charlie.model.entity.Comment;
import io.github.charlie.model.form.CommentForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 评论转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentConvert {
    Comment toEntity(CommentForm form);
}
