package io.github.charlie.convert;

import io.github.charlie.model.entity.User;
import io.github.charlie.model.form.UserForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 用户转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserConvert {
    User toEntity(UserForm form);
}
