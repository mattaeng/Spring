package com.mattaeng.mattaengapi.dto.comment;

import lombok.Builder;

@Builder
public record DeleteCommentResponse(
    boolean DeleteCommentResult
) {
    public static DeleteCommentResponse of(boolean deleteCommentResult) {
        return new DeleteCommentResponse(deleteCommentResult);
    }
}
