package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.model.entity.TUser;
import com.example.demo.service.TUserService;
import com.example.demo.mapper.TUserMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2025-04-26 12:23:44
*/
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser>
    implements TUserService{

}




