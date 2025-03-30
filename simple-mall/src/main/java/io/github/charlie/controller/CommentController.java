package io.github.charlie.controller;

import io.github.charlie.base.IDs;
import io.github.charlie.base.Result;
import io.github.charlie.convert.CommentConvert;
import io.github.charlie.model.entity.Comment;
import io.github.charlie.model.form.CommentForm;
import io.github.charlie.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 评论控制器
 */
@Tag(name = "评论管理")
@RequestMapping("/api/v1/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    private final CommentConvert convert;

    @Operation(summary = "创建评论")
    @PostMapping
    public Result<Boolean> create(@RequestBody CommentForm form) {
        Comment entity = convert.toEntity(form);
        entity.setId(null);
        boolean save = service.save(entity);
        return Result.status(save);
    }

    @Operation(summary = "删除评论")
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody IDs iDs) {
        boolean remove = service.removeByIds(iDs.getIds());
        return Result.status(remove);
    }

    @Operation(summary = "修改评论")
    @PutMapping
    public Result<Boolean> update(@RequestBody CommentForm form) {
        Comment entity = convert.toEntity(form);
        boolean update = service.updateById(entity);
        return Result.status(update);
    }

    @Operation(summary = "查询评论")
    @GetMapping("/{id}")
    public Result<Comment> get(@PathVariable Long id) {
        Comment entity = service.getById(id);
        return Result.success(entity);
    }


}
