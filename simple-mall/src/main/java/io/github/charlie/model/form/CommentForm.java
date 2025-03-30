package io.github.charlie.model.form;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 评论表单
 */
@Data
public class CommentForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long orderId;

    private Long productId;

    private Long specificationId;

    private String content;

    private Integer score;
}
