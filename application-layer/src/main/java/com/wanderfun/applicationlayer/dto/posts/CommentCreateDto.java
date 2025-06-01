package com.wanderfun.applicationlayer.dto.posts;

import jakarta.validation.constraints.NotBlank;

public class CommentCreateDto {
    @NotBlank
    private String content;
    private Long parentId;

    public CommentCreateDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
