package com.mattaeng.mattaengapi.service;

import com.mattaeng.mattaengapi.dto.feed.CreateFeedRequest;
import com.mattaeng.mattaengapi.dto.feed.CreateFeedResponse;
import com.mattaeng.mattaengapi.dto.feed.DeleteFeedRequest;
import com.mattaeng.mattaengapi.dto.feed.DeleteFeedResponse;
import com.mattaeng.mattaengapi.dto.feed.FeedResponse;
import com.mattaeng.mattaengapi.dto.feed.UpdateFeedRequest;
import com.mattaeng.mattaengapi.dto.feed.UpdateFeedResponse;
import com.mattaeng.mattaengapi.entity.Feed;
import com.mattaeng.mattaengapi.repository.FeedRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public FeedResponse getFeed(Long id){
        return feedRepository.findById(id)
            .map(FeedResponse::from)
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 피드 입니다."));
    }

    @Transactional(readOnly = true)
    public List<FeedResponse> getFeeds(){
        List<Feed> feeds = feedRepository.findAll();
        return feeds.stream().map(FeedResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public CreateFeedResponse createFeed(CreateFeedRequest createFeedRequest) {
        Feed feed = feedRepository.save(Feed.from(createFeedRequest));
        return CreateFeedResponse.from(feed);
    }

    @Transactional
    public UpdateFeedResponse updateFeed(UpdateFeedRequest updateFeedRequest,Long id){
        return feedRepository.findById(id)
            .map(feed -> {
                feed.updateFeed(updateFeedRequest);
                return UpdateFeedResponse.of(true);
            })
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 피드입니다."));
    }

    //TODO: 댓글 관련 같이 삭제 되는 로직 작성 필요함
    @Transactional
    public DeleteFeedResponse deleteFeed(DeleteFeedRequest deleteFeedRequest, Long id){
        return feedRepository.findById(id)
            .map(feed -> {
                feed.deleteFeed(deleteFeedRequest);
                return DeleteFeedResponse.of(true);
            })
            .orElseThrow(() -> new NoSuchElementException("존재하지 않는 피드입니다."));
    }

}
