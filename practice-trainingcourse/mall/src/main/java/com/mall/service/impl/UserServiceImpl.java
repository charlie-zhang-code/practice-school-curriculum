package com.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.model.entity.User;
import com.mall.service.UserService;
import com.mall.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-01-21 10:43:44
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




