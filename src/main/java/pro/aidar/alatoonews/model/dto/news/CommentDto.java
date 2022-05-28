package pro.aidar.alatoonews.model.dto.news;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentDto {
    private Long newsId;
    @NotBlank
    private String comment;
}
