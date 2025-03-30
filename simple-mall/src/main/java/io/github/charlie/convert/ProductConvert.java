package io.github.charlie.convert;

import io.github.charlie.model.entity.ProductS;
import io.github.charlie.model.vo.ProductSVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 商品转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductConvert {
    List<ProductSVO> toE(List<ProductS> s);
}
