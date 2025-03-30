package com.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.model.entity.Type;
import com.mall.service.TypeService;
import com.mall.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【type】的数据库操作Service实现
* @createDate 2025-01-21 10:43:24
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




