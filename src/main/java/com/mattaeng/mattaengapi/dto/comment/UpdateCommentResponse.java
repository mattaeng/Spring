package com.mattaeng.mattaengapi.dto.comment;

public record UpdateCommentResponse(
    boolean updateCommentresult
) {
    public static UpdateCommentResponse of(boolean updateCommentresult) {
        return new UpdateCommentResponse(updateCommentresult);
    }
}
