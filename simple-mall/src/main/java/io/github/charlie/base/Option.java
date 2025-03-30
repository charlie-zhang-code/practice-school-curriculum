package io.github.charlie.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 下拉选项对象
 */
@Data
@NoArgsConstructor
public class Option<T> implements Serializable {
    private T value;

    private String label;

    @JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Serial
    private static final long serialVersionUID = 1L;
}
