package io.github.charlie.convert;

import io.github.charlie.model.entity.Role;
import io.github.charlie.model.form.RoleForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 角色转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleConvert {
    Role toEntity(RoleForm form);
}
