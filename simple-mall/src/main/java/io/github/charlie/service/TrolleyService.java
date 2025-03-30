package io.github.charlie.service;

import io.github.charlie.model.entity.ProductS;
import io.github.charlie.model.entity.Trolley;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.charlie.model.vo.ProductSVO;

import java.util.List;

/**
 * @author Charlie
 * @description 针对表【sys_trolley(购物车表)】的数据库操作Service
 * @createDate 2025-02-09 15:04:35
 */
public interface TrolleyService extends IService<Trolley> {
    List<ProductSVO> getProducts(Long uid, Long sate);
}
