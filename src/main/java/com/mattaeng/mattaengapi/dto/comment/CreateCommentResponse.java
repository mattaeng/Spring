package com.mattaeng.mattaengapi.dto.comment;

import com.mattaeng.mattaengapi.common.enums.CommentStatus;
import com.mattaeng.mattaengapi.entity.Comment;

import lombok.Builder;

@Builder
public record CreateCommentResponse(
	String content,
	CommentStatus commentStatus
) {
	public static CreateCommentResponse from(Comment comment) {
		return CreateCommentResponse.builder()
			.content(comment.getContent())
			.commentStatus(comment.getCommentStatus())
			.build();
	}
}