package io.github.charlie.base;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 批量删除参数
 */
@Data
public class IDs implements Serializable {
    private List<Long> ids;

    @Serial
    private static final long serialVersionUID = 1L;
}
