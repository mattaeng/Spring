package com.mattaeng.mattaengapi.dto.comment;

public record UpdateCommentRequest(
    Long id,
    String content
) {

}
