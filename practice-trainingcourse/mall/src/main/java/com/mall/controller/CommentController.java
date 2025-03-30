package com.mall.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.CommonPageQuery;
import com.mall.common.CommonResult;
import com.mall.model.entity.Comment;
import com.mall.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Charlie Zhang(Github: @charlie-zhang-code)
 * @Date: 2025/1/20
 * @Description: 评论控制器
 */
@Tag(name = "评论管理")
@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "创建评论")
    @Transactional
    public CommonResult create(@RequestBody Comment comment) {
        boolean save = commentService.save(comment);
        if (!save) {
            return new CommonResult(500, "评论失败", false);
        }
        log.info("评论成功");
        return new CommonResult(200, "评论成功", true);
    }

    @PutMapping
    @Operation(summary = "修改评论")
    public CommonResult update(@RequestBody Comment comment) {
        boolean update = commentService.updateById(comment);
        if (!update) {
            return new CommonResult(500, "修改评论失败", false);
        }
        log.info("修改评论成功");
        return new CommonResult(200, "修改评论成功", true);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除评论")
    public CommonResult delete(@PathVariable Integer id) {
        boolean remove = commentService.removeById(id);
        if (!remove) {
            return new CommonResult(500, "删除评论失败", false);
        }
        log.info("删除评论成功");
        return new CommonResult(200, "删除评论成功", true);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询评论")
    public CommonResult get(@PathVariable Integer id) {
        Comment comment = commentService.getById(id);
        if (comment == null) {
            return new CommonResult(500, "查询评论失败", false);
        }
        log.info("查询评论成功");
        return new CommonResult(200, "查询评论成功", comment);
    }

    @GetMapping("/list")
    @Operation(summary = "查询评论分页列表")
    public CommonResult list(@ParameterObject CommonPageQuery query) {

        Page<Comment> conmmentPage = new Page<>(query.getPageNum(), query.getPageSize());

        Page<Comment> page;
        if (StrUtil.isNotBlank(query.getKeyword())) {
            LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<Comment>()
                    .like(Comment::getComment, query.getKeyword());
            page = commentService.page(conmmentPage, commentLambdaQueryWrapper);
        } else {
            page = commentService.page(conmmentPage);
        }

        return new CommonResult(200, "查询评论分页列表成功", page);
    }

    @GetMapping("/goods/{id}")
    @Operation(summary = "查询评论列表（通过商品）")
    public CommonResult goodsList(@PathVariable Integer id, @ParameterObject CommonPageQuery query) {
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<Comment>()
                .eq(Comment::getGoodsid, id);
        Page<Comment> commentPage = commentService.page(new Page<>(query.getPageNum(), query.getPageSize()), commentLambdaQueryWrapper);
        return new CommonResult(200, "查询评论列表成功", commentPage);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "查询评论列表（通过用户）")
    public CommonResult userList(@PathVariable Integer id, @ParameterObject CommonPageQuery query) {
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<Comment>()
                .eq(Comment::getUserid, id);
        Page<Comment> commentPage = commentService.page(new Page<>(query.getPageNum(), query.getPageSize()), commentLambdaQueryWrapper);
        return new CommonResult(200, "查询评论列表成功", commentPage);
    }

    @GetMapping("detail")
    @Operation(summary = "查询评论详情(商品ID和用户ID)")
    public CommonResult detail(
            @RequestParam(value = "goodsid", required = false) Integer goodsid,
            @RequestParam(value = "userid", required = false) Integer userid
    ) {
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<Comment>()
                .eq(Comment::getGoodsid, goodsid)
                .eq(Comment::getUserid, userid);
        Comment comment = commentService.getOne(commentLambdaQueryWrapper);
        return new CommonResult(200, "查询评论详情成功", comment);
    }
}
