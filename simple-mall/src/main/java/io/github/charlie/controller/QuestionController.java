package io.github.charlie.controller;

import io.github.charlie.base.IDs;
import io.github.charlie.base.Result;
import io.github.charlie.convert.QuestionConvert;
import io.github.charlie.model.entity.Question;
import io.github.charlie.model.form.QuestionForm;
import io.github.charlie.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 提问控制器
 */
@Tag(name = "提问管理")
@RequestMapping("/api/v1/question")
@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService service;
    private final QuestionConvert convert;

    @Operation(summary = "创建提问")
    @PostMapping
    public Result<Boolean> create(@RequestBody QuestionForm form) {
        Question entity = convert.toEntity(form);
        entity.setId(null);
        boolean save = service.save(entity);
        return Result.status(save);
    }

    @Operation(summary = "删除提问")
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody IDs iDs) {
        boolean remove = service.removeByIds(iDs.getIds());
        return Result.status(remove);
    }

    @Operation(summary = "修改提问")
    @PutMapping
    public Result<Boolean> update(@RequestBody QuestionForm form) {
        Question entity = convert.toEntity(form);
        boolean update = service.updateById(entity);
        return Result.status(update);
    }

    @Operation(summary = "查询提问")
    @GetMapping("/{id}")
    public Result<Question> get(@PathVariable Long id) {
        Question entity = service.getById(id);
        return Result.success(entity);
    }
}
