package com.mattaeng.mattaengapi.dto.comment;

import com.mattaeng.mattaengapi.common.enums.CommentStatus;

public record DeleteCommentRequest(
    Long id,
    CommentStatus commentStatus
) {

}
