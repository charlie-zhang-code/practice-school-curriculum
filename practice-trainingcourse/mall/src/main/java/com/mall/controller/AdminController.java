package com.mall.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.CommonResult;
import com.mall.model.entity.Admin;
import com.mall.model.query.AdminQuery;
import com.mall.model.params.AdminParams;
import com.mall.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author 李茂鑫
 * @Date 2025/1/20 10:42
 * @Descripition
 */
@Tag(name = "管理员控制器")
@RequestMapping("/admin")
@RestController
public class AdminController {
    @Resource
    private AdminService adminService;

    @GetMapping("/{id}")
    @CrossOrigin
    @Operation(summary = "查询用户")
    public CommonResult get(@PathVariable String id) {
        //查询用户
        Admin item = adminService.getById(id); // 直接通过ID获取Admin对象
        if (item == null) {
            return new CommonResult(500, "查询用户失败", false); // 返回失败的响应
        }

        return new CommonResult(200, "查询用户成功", item); // 返回查询到的Admin对象
    }

    /**
     * 分页查询用户 (根据email和nickname两个字段进行模糊查询）
     * @param AdminQuery
     * @return
     */
    @GetMapping("/listAll")
    @CrossOrigin
    @Operation(summary = "分页查询用户")
    public CommonResult listAdmin(AdminQuery AdminQuery) {
        // TODO 【优化方向】01 管理员分页查询的参数过多，后续可以进行优化，减少一些不必要的参数，例如ID这些等
        IPage<Admin> page=new Page(AdminQuery.getPageNo(), AdminQuery.getPageSize());
        LambdaQueryWrapper<Admin> queryWrapper=new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(AdminQuery.getEmail())){
            queryWrapper.like(Admin::getEmail, AdminQuery.getEmail());
        }
        if(StrUtil.isNotBlank(AdminQuery.getNickname())){
            queryWrapper.like(Admin::getNickname, AdminQuery.getNickname());
        }
        adminService.page(page,queryWrapper);
        return CommonResult.success(page);
    }

    /**
     * 添加用户
     * @param admin
     * @return
     */
    @PostMapping("/add")
    @CrossOrigin
    @Operation(summary = "添加用户")
    public CommonResult add(@RequestBody AdminParams admin) {
        return adminService.add(admin)? CommonResult.success("添加成功"): CommonResult.error("添加失败");
    }

    /**
     * 更新用户
     * @param admin
     * @return
     */
    @PutMapping("/update")
    @CrossOrigin
    @Operation(summary = "更新用户")
    public CommonResult update(@RequestBody Admin admin) {
        // TODO 【优化方向】02 更新用户的参数保留过多，其中createPeople、updatePeople、createTime、updateTime字段是发请求多余的，后续可以进行优化
        //查询用户
        Admin item = adminService.findAdminByAdminId(admin.getId());
        //判断对象是否为空,且查询到的用户ID不等于当前编辑的用户ID，表示该名称被占用
        if (item != null && ! Objects.equals(item.getId(), admin.getId())) {
            return new CommonResult(500, "用户已存在",false);
        }
        //调用修改方法
        if (adminService.updateById(admin)) {
            return  new CommonResult(200, "用户修改成功",true);
        } else return new CommonResult(500, "用户修改失败",false);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    @Operation(summary = "删除用户")
    public CommonResult delete(@PathVariable String id) {

        //调用删除方法
        if (adminService.removeById(id)) {
            return  new CommonResult(200, "用户删除成功",true);
        } else return new CommonResult(500, "用户删除失败",false);
    }

}
