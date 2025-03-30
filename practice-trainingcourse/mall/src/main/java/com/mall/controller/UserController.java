package com.mall.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.CommonPageQuery;
import com.mall.common.CommonResult;
import com.mall.convert.UserConvert;
import com.mall.model.entity.User;
import com.mall.model.vo.UserVO;
import com.mall.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李茂鑫/CharlieZhang
 * @Date 2025/1/17 17:21
 * @Descripition
 */
@RestController
@Tag(name = "用户管理")
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserConvert userConvert;

    @PostMapping
    @Operation(summary = "创建用户")
    @Transactional
    public CommonResult create(@RequestBody User user) {
        boolean save = userService.save(user);
        if (!save) {
            return new CommonResult(500, "创建失败", false);
        }
        log.info("创建成功");
        return new CommonResult(200, "创建成功", true);
    }

    @PutMapping
    @Operation(summary = "修改用户")
    public CommonResult update(@RequestBody User user) {
        boolean save = userService.updateById(user);
        if (!save) {
            return new CommonResult(500, "修改失败", false);
        }
        log.info("修改成功");
        return new CommonResult(200, "修改成功", true);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    public CommonResult delete(@PathVariable Integer id) {
        boolean save = userService.removeById(id);
        if (!save) {
            return new CommonResult(500, "删除失败", false);
        }
        log.info("删除成功");
        return new CommonResult(200, "删除成功", true);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询用户")
    public CommonResult get(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user == null) {
            return new CommonResult(500, "查询失败", false);
        }
        log.info("查询成功");
        return new CommonResult(200, "查询成功", user);
    }

    @GetMapping("/list")
    @Operation(summary = "查询用户列表")
    public CommonResult list(@ParameterObject CommonPageQuery query) {
        Page<User> userPage = new Page<>(query.getPageNum(), query.getPageSize());
        Page<User> page;
        if (StrUtil.isNotBlank(query.getKeyword())) {
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                    .like(User::getNickname, query.getKeyword());
            page = userService.page(userPage, userLambdaQueryWrapper);
        } else {
            page = userService.page(userPage);
        }

        return new CommonResult(200, "查询用户列表成功", page);
    }

    /* ======================= 登录后使用到 ======================== */
    @GetMapping("/profile")
    @Operation(summary = "查询用户信息")
    public CommonResult profile() {
        // TODO 【后续准备】获取当前登录用户信息，要根据用户登录后的情况来做处理
        User user = userService.getById(9);
        UserVO vo = userConvert.toVO(user);
        return new CommonResult(200, "查询用户信息成功", vo);
    }

    /* ======================= 唯一性检查，注册时候可能用到（注册页面输入完就显示状态的那个部分） ======================== */
    @GetMapping("/check/nickname/{nickname}")
    @Operation(summary = "检查用户名唯一性")
    public CommonResult checkNickname(@PathVariable String nickname) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getNickname, nickname);
        long count = userService.count(userLambdaQueryWrapper);
        if (count == 0) {
            return new CommonResult(200, "用户名可用", true);
        }
        return new CommonResult(500, "用户名已存在", false);
    }

    @GetMapping("/check/email/{email}")
    @Operation(summary = "检查邮箱唯一性")
    public CommonResult checkEmail(@PathVariable String email) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getEmail, email);
        long count = userService.count(userLambdaQueryWrapper);
        if (count == 0) {
            return new CommonResult(200, "邮箱可用", true);
        }
        return new CommonResult(500, "邮箱已存在", false);
    }

    @GetMapping("/check/phone/{phone}")
    @Operation(summary = "检查手机号唯一性")
    public CommonResult checkPhone(@PathVariable String phone) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getPhone, phone);
        long count = userService.count(userLambdaQueryWrapper);
        if (count == 0) {
            return new CommonResult(200, "手机号可用", true);
        }
        return new CommonResult(500, "手机号已存在", false);
    }
}
