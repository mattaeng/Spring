package com.mattaeng.mattaengapi.entity;

import com.mattaeng.mattaengapi.common.auditing.BaseTimeEntity;
import com.mattaeng.mattaengapi.common.enums.CommentStatus;
import com.mattaeng.mattaengapi.dto.comment.DeleteCommentRequest;
import com.mattaeng.mattaengapi.dto.comment.UpdateCommentRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	@Enumerated(EnumType.STRING)
	private CommentStatus commentStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feed_id")
	private Feed feed;

	public void updateComment(UpdateCommentRequest updateCommentRequest) {
		if (updateCommentRequest.content() != null) {
			this.content = updateCommentRequest.content();
		}
	}

	public void deleteComment(DeleteCommentRequest deleteCommentRequest) {
		if (deleteCommentRequest.commentStatus() != null) {
			this.commentStatus = CommentStatus.INACTIVATE;
		}
	}

	@Builder
	private Comment(Long id, String content, CommentStatus commentStatus, Feed feed) {
		this.id = id;
		this.content = content;
		this.commentStatus = commentStatus;
		this.feed = feed;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}
}
