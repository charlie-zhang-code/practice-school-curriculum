package io.github.charlie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.charlie.model.entity.ProductS;
import io.github.charlie.model.entity.Trolley;
import io.github.charlie.model.vo.ProductSVO;
import io.github.charlie.service.TrolleyService;
import io.github.charlie.mapper.TrolleyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Charlie
 * @description 针对表【sys_trolley(购物车表)】的数据库操作Service实现
 * @createDate 2025-02-09 15:04:35
 */
@Service
public class TrolleyServiceImpl extends ServiceImpl<TrolleyMapper, Trolley>
        implements TrolleyService {

    @Override
    public List<ProductSVO> getProducts(Long uid, Long sate) {
        return this.baseMapper.selectProducts(uid, sate);
    }
}




