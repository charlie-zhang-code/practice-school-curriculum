package com.mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.model.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.model.query.AdminQuery;
import com.mall.model.params.AdminParams;

/**
* @author Administrator
* @description 针对表【admin】的数据库操作Service
* @createDate 2025-01-20 15:04:43
*/
public interface AdminService extends IService<Admin> {
    /**
     * 添加管理员用户
     * @param id 用户参数
     * @return 是否添加成功
     */
    Admin findAdminByAdminId(String id);
    boolean add(AdminParams admin);


    IPage<Admin> findAdminByAdminId(IPage<Admin> page, AdminQuery AdminQuery);
}



