package com.mattaeng.mattaengapi.service;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.dto.comment.CommentResponse;
import com.mattaeng.mattaengapi.dto.comment.CreateCommentRequest;
import com.mattaeng.mattaengapi.dto.comment.CreateCommentResponse;
import com.mattaeng.mattaengapi.dto.comment.UpdateCommentRequest;
import com.mattaeng.mattaengapi.dto.comment.UpdateCommentResponse;
import com.mattaeng.mattaengapi.entity.Comment;
import com.mattaeng.mattaengapi.entity.Feed;
import com.mattaeng.mattaengapi.repository.CommentRepository;
import com.mattaeng.mattaengapi.repository.FeedRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Long id) {
        List<Comment> comments = commentRepository.findByFeed_Id(id);
        log.info("comments {} = ",comments.toString());
        return comments.stream().map(CommentResponse::from).collect(Collectors.toList());
    }
    //TODO: comment 부분 파싱 하기
    @Transactional
    public CreateCommentResponse createComment(CreateCommentRequest createCommentRequest, Long id) {
        Optional<Feed> feed = Optional.ofNullable(feedRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시물입니다.")));
        Comment comment = Comment.builder()
            .content(createCommentRequest.content())
            .commentStatus(createCommentRequest.commentStatus())
            .feed(feed.get())
            .build();
        commentRepository.save(comment);
        return CreateCommentResponse.from(comment);
    }

    @Transactional
    public UpdateCommentResponse updateComment(UpdateCommentRequest updateCommentRequest, Long id) {
        return commentRepository.findByFeed_IdAndId(id,updateCommentRequest.id())
            .map(comment -> {
                comment.updateComment(updateCommentRequest);
                return UpdateCommentResponse.of(true);
            })
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 댓글입니다."));
    }
}
