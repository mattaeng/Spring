package com.mattaeng.mattaengapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.dto.comment.CommentResponse;
import com.mattaeng.mattaengapi.dto.comment.CreateCommentRequest;
import com.mattaeng.mattaengapi.dto.comment.CreateCommentResponse;
import com.mattaeng.mattaengapi.dto.comment.DeleteCommentRequest;
import com.mattaeng.mattaengapi.dto.comment.DeleteCommentResponse;
import com.mattaeng.mattaengapi.dto.comment.UpdateCommentRequest;
import com.mattaeng.mattaengapi.dto.comment.UpdateCommentResponse;
import com.mattaeng.mattaengapi.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
	private final CommentService commentService;

	@Operation(summary = "댓글 조회")
	@GetMapping("/{id}")
	public Api<List<CommentResponse>> getComments(@PathVariable final Long id) {
		return Api.ok(commentService.getComments(id));
	}

	//TODO:: id를 조금 더 명시적으로 바꾸고 Path를 어떻게 줘야할까?
	@Operation(summary = "댓글 생성")
	@PostMapping("/{id}")
	public Api<CreateCommentResponse> createComment(@RequestBody final CreateCommentRequest createCommentRequest,
		@PathVariable Long id) {
		return Api.ok(commentService.createComment(createCommentRequest, id));
	}

	@Operation(summary = "댓글 수정")
	@PutMapping
	public Api<UpdateCommentResponse> updateComment(@RequestBody final UpdateCommentRequest updateCommentRequest,
		Long id) {
		return Api.ok(commentService.updateComment(updateCommentRequest, id));
	}

	@Operation(summary = "댓글 삭제")
	@DeleteMapping
	public Api<DeleteCommentResponse> deleteComment(final DeleteCommentRequest deleteCommentRequest, Long id) {
		return Api.ok(commentService.deleteComment(deleteCommentRequest, id));
	}
}
