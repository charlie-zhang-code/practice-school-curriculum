package io.github.charlie.convert;

import io.github.charlie.model.entity.Trolley;
import io.github.charlie.model.form.TrolleyForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/9
 * @Description: TODO
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TrolleyConvert {
    Trolley toEntity(TrolleyForm form);
}
