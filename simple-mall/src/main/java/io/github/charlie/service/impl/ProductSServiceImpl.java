package io.github.charlie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.charlie.model.entity.ProductS;
import io.github.charlie.service.ProductSService;
import io.github.charlie.mapper.ProductSMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【sys_product_s(商品表)】的数据库操作Service实现
* @createDate 2025-02-09 14:36:09
*/
@Service
public class ProductSServiceImpl extends ServiceImpl<ProductSMapper, ProductS>
    implements ProductSService{

}




