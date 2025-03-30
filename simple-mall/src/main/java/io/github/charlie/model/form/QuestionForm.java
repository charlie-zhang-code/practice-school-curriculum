package io.github.charlie.model.form;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author: charlie-zhang-code
 * @Date: 2025/2/5
 * @Description: 提问表单
 */
@Data
public class QuestionForm implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long productId;

    private Long specificationId;

    private Long parentId;

    private String title;

    private String content;

    private Integer state;
}
