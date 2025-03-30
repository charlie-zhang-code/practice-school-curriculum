package com.mall.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Charlie Zhang(Github: @charlie-zhang-code)
 * @Date: 2025/1/16
 * @Description: 下拉选项对象
 */
@Data
@NoArgsConstructor
public class Option<T> implements Serializable {

    private T value;

    private String label;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<Option<T>> children;

    public Option(T value, String label) {
        this.value = value;
        this.label = label;
    }

    public Option(T value, String label, List<Option<T>> children) {
        this.value = value;
        this.label = label;
        this.children = children;
    }
}
