package com.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.model.entity.Admin;
import com.mall.model.query.AdminQuery;
import com.mall.model.params.AdminParams;
import com.mall.service.AdminService;
import com.mall.mapper.AdminMapper;
import com.mall.utils.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
* @author Administrator
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2025-01-20 15:04:43
*/
@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService{

    @Override
    public Admin findAdminByAdminId(String id) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean add(AdminParams admin) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getNickname,admin.getNickname());
        Admin one = getOne(queryWrapper);
        if (one==null){
            Admin addAdmin=new Admin();
            BeanUtils.copyProperties(admin,addAdmin);
            addAdmin.setId(null);
            addAdmin.setPwd(MD5Util.getMD5String(admin.getPwd()));
            return save(addAdmin);
        }
        return false;
    }

    @Override
    public IPage<Admin> findAdminByAdminId(IPage<Admin> page, AdminQuery AdminQuery) {
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        //用户Id
        queryWrapper.like(!ObjectUtils.isEmpty(AdminQuery.getId()), "id", AdminQuery.getId());
        //真实姓名
        queryWrapper.like(!ObjectUtils.isEmpty(AdminQuery.getNickname()), "nickname", AdminQuery.getNickname());
        //邮箱
        queryWrapper.like(!ObjectUtils.isEmpty(AdminQuery.getEmail()) ,"email", AdminQuery.getEmail());
        //        查询并返回数据
        return baseMapper.selectPage(page, queryWrapper);
    }
}




