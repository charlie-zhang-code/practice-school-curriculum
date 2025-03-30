package io.github.charlie.mapper;

import io.github.charlie.model.entity.ProductS;
import io.github.charlie.model.entity.Trolley;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.charlie.model.vo.ProductSVO;

import java.util.List;

/**
* @author Charlie
* @description 针对表【sys_trolley(购物车表)】的数据库操作Mapper
* @createDate 2025-02-09 15:04:35
* @Entity io.github.charlie.model.entity.Trolley
*/
public interface TrolleyMapper extends BaseMapper<Trolley> {
    List<ProductSVO> selectProducts(Long uid, Long s);
}




