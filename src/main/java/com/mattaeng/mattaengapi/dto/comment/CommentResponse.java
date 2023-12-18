package com.mattaeng.mattaengapi.dto.comment;

import com.mattaeng.mattaengapi.entity.Comment;
import lombok.Builder;

@Builder
public record CommentResponse(
    String content
) {
    public static CommentResponse from(Comment comment) {
        return  CommentResponse.builder()
            .content(comment.getContent())
            .build();
    }
}
