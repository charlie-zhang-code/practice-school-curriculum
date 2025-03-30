package io.github.charlie.controller;

import io.github.charlie.base.IDs;
import io.github.charlie.base.Result;
import io.github.charlie.convert.RoleConvert;
import io.github.charlie.model.entity.Role;
import io.github.charlie.model.form.RoleForm;
import io.github.charlie.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 角色控制器
 */
@Tag(name = "角色管理")
@RequestMapping("/api/v1/role")
@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleService service;
    private final RoleConvert convert;

    @Operation(summary = "创建角色")
    @PostMapping
    public Result<Boolean> create(@RequestBody RoleForm form) {
        Role entity = convert.toEntity(form);
        entity.setId(null);
        boolean save = service.save(entity);
        return Result.status(save);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody IDs iDs) {
        boolean remove = service.removeByIds(iDs.getIds());
        return Result.status(remove);
    }

    @Operation(summary = "修改角色")
    @PutMapping
    public Result<Boolean> update(@RequestBody RoleForm form) {
        Role entity = convert.toEntity(form);
        boolean update = service.updateById(entity);
        return Result.status(update);
    }

    @Operation(summary = "查询角色")
    @GetMapping("/{id}")
    public Result<Role> get(@PathVariable Long id) {
        Role entity = service.getById(id);
        return Result.success(entity);
    }
}
