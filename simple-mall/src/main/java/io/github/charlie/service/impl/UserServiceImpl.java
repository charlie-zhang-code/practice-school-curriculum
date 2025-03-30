package io.github.charlie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.charlie.model.entity.User;
import io.github.charlie.service.UserService;
import io.github.charlie.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2025-02-05 16:51:54
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




