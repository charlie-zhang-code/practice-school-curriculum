package com.mall.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.model.entity.User;
import com.mall.model.vo.UserVO;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapper;

/**
 * @Author: Charlie Zhang(Github: @charlie-zhang-code)
 * @Date: 2025/1/17
 * @Description: 用户转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserConvert {
    UserVO toVO(User user);
}
