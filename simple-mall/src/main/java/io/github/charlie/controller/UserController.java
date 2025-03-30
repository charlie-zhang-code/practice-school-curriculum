package io.github.charlie.controller;

import io.github.charlie.base.IDs;
import io.github.charlie.base.Result;
import io.github.charlie.convert.UserConvert;
import io.github.charlie.model.entity.User;
import io.github.charlie.model.form.UserForm;
import io.github.charlie.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 用户控制器
 */
@Tag(name = "用户管理")
@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserConvert convert;

    @Operation(summary = "创建用户")
    @PostMapping
    public Result<Boolean> create(@RequestBody UserForm form) {
        User entity = convert.toEntity(form);
        entity.setId(null);
        boolean save = service.save(entity);
        return Result.status(save);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody IDs iDs) {
        boolean remove = service.removeByIds(iDs.getIds());
        return Result.status(remove);
    }

    @Operation(summary = "修改用户")
    @PutMapping
    public Result<Boolean> update(@RequestBody UserForm form) {
        User entity = convert.toEntity(form);
        boolean update = service.updateById(entity);
        return Result.status(update);
    }

    @Operation(summary = "查询用户")
    @GetMapping("/{id}")
    public Result<User> get(@PathVariable Long id) {
        User entity = service.getById(id);
        return Result.success(entity);
    }

    @Operation(summary = "查询用户列表")
    @GetMapping("/list")
    public Result<List<User>> list() {
        return Result.success(service.list());
    }
}
