package com.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.CommonResult;
import com.mall.model.entity.Message;
import com.mall.model.query.MessageQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * @author 李茂鑫
 * @Date 2025/1/21 11:17
 * @Descripition
 */
@RestController
@CrossOrigin
@Tag(name = "留言管理")
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IService<Message> messageService;

    /**
     * 添加留言
     * @param message
     * @return
     */
   @PostMapping("/add")
    @CrossOrigin
    @Operation(summary = "添加留言")
    public CommonResult createMessage(@RequestBody Message message) {
       // TODO 【优化方向】 05 参数过多，createPeople、updatePeople、createTime、updateTime字段是发请求多余的
        try {
            boolean createdGoods = messageService.save(message);
            if (createdGoods) {
                return CommonResult.success("添加成功");
            } else {
                return CommonResult.error("添加失败");
            }
        } catch (Exception e) {
            return CommonResult.error("系统错误，请稍后再试");
        }
    }

    /**
     * 根据id查询留言
     * @param id
     * @return
     */
    @PostMapping("/{id}")
    @CrossOrigin
    @Operation(summary = "查询留言")

    public CommonResult getMessageById(@PathVariable String id) {
        Message item = (Message) messageService.getById(id);
        if (item == null) {
            return new CommonResult(500, "查询留言失败", false);
        }
        return new CommonResult(200, "查询留言成功", item);
    }

    /**
     * 更新留言
     * @param message
     * @return
     */
    @PostMapping("/update")
    @CrossOrigin
    @Operation(summary = "更新留言")
    public CommonResult updateMessage(@RequestBody Message message) {
        // TODO 【优化方向】 06 参数过多，createPeople、updatePeople、createTime、updateTime字段是发请求多余的
        try {
            boolean updatedGoods = messageService.updateById(message);
            if (updatedGoods) {
                return CommonResult.success("更新成功");
            } else {
                return CommonResult.error("更新失败");
            }
        } catch (Exception e) {
            return CommonResult.error("系统错误，请稍后再试");
        }
    }

    /**
     * 根据id删除留言
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    @Operation(summary = "删除留言")
    public CommonResult deleteMessage(@PathVariable Serializable id) {
        try {
            boolean deletedGoods = messageService.removeById(id);
            if (deletedGoods) {
                return CommonResult.success("删除成功");
            } else {
                return CommonResult.error("删除失败");
            }
        } catch (Exception e) {
            return CommonResult.error("系统错误，请稍后再试");
        }
    }
    /**
     * 分页查询留言
     * @param MessageQuery
     * @return
     */
    @GetMapping("/list")
    @CrossOrigin
    @Operation(summary = "分页查询留言")
    public CommonResult listMessage(MessageQuery MessageQuery) {
        // TODO 【优化方向】 07 参数过多，可以省略一些，考虑能否提出一些数据，封装起来，作为一个通用的查询条件，可参考 Common目录下的CommonPageQuery
        try {
            Page<Message> page = new Page<>(MessageQuery.getPageNo(), MessageQuery.getPageSize());

            // 创建 QueryWrapper 对象，构建查询条件（此处为空查询条件，若需要根据其他字段查询，可以添加相应的条件）
            QueryWrapper<Message> wrapper = new QueryWrapper<>();

            return CommonResult.success(messageService.page(page, wrapper));
        } catch (Exception e) {
            return CommonResult.error("系统错误，请稍后再试");
        }
    }
}

