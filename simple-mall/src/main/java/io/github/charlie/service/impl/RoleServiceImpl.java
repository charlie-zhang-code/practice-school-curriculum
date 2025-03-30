package io.github.charlie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.charlie.model.entity.Role;
import io.github.charlie.service.RoleService;
import io.github.charlie.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author Charlie
* @description 针对表【sys_role(角色表)】的数据库操作Service实现
* @createDate 2025-02-05 16:51:50
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




