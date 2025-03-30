package io.github.charlie.base;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 返回结果类
 */
@Data
public class Result<T> implements Serializable {
    private String code;

    private T data;

    private String message;

    private Boolean success;

    private static <T> Result<T> result(String code, T data, String message, Boolean success) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        result.setSuccess(success);
        return result;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setData(data);
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> failure() {
        return result(ResultCode.FAIL.getCode(), null, ResultCode.FAIL.getMessage(), false);
    }

    public static <T> Result<T> failure(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAIL.getCode());
        result.setData(data);
        result.setMessage(ResultCode.FAIL.getMessage());
        result.setSuccess(false);
        return result;
    }

    public static <T> Result<T> failure(String message) {
        return result(ResultCode.FAIL.getCode(), null, message, false);
    }

    public static <T> Result<T> failure(IResultCode resultCode) {
        return result(resultCode.getCode(), null, resultCode.getMessage(), false);
    }

    public static <T> Result<T> failure(IResultCode resultCode, String message) {
        return result(resultCode.getCode(), null, message, false);
    }

    public static <T> Result<T> status(Boolean success) {
        if (success) {
            return success();
        } else {
            return failure();
        }
    }

    public static boolean isSuccess(Result<?> result) {
        return result != null && ResultCode.SUCCESS.getCode().equals(result.getCode());
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
