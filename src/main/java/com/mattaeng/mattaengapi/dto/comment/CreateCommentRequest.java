package com.mattaeng.mattaengapi.dto.comment;

import com.mattaeng.mattaengapi.common.enums.CommentStatus;
import lombok.Builder;

@Builder
public record CreateCommentRequest(
    String content,
    CommentStatus commentStatus
) {

}
