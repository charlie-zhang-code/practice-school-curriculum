package com.mall.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author 李茂鑫
 * @Date 2025/1/17 17:54
 * @Descripition  封装统一的返回值，要求和前端响应的返回值要求一致
 */
@Data
@AllArgsConstructor
@Schema(description = "通用返回对象")
public class CommonResult {
    @Schema(description = "状态码")
    private int code;
    @Schema(description = "返回信息")
    private String message;
    @Schema(description = "返回数据")
    private Object data;
    public static CommonResult success() {

        return new CommonResult(200, "操作成功", null);
    }
    public static CommonResult success(String message) {

        return new CommonResult(200, message, null);
    }
    public static CommonResult success(Object data) {

        return new CommonResult(200, "操作成功", data);
    }
    public static CommonResult error( ) {

        return new CommonResult(500, "操作失败", null);
    }
    public static CommonResult error(String message) {
        return new CommonResult(500, message, null);
    }
}
