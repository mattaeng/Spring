package com.mattaeng.mattaengapi.service;

import com.mattaeng.mattaengapi.dto.feed.CreateFeedRequest;
import com.mattaeng.mattaengapi.dto.feed.CreateFeedResponse;
import com.mattaeng.mattaengapi.entity.Feed;
import com.mattaeng.mattaengapi.repository.FeedRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;

    public
    public CreateFeedResponse createFeed(CreateFeedRequest createFeedRequest) {
        Feed feed = feedRepository.save(Feed.from(createFeedRequest));
        return CreateFeedResponse.from(feed);
    }
}
