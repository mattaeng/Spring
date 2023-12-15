package com.mattaeng.mattaengapi.dto.feed;

import com.mattaeng.mattaengapi.common.enums.FeedStatus;
import com.mattaeng.mattaengapi.entity.Feed;
import lombok.Builder;

@Builder
public record FeedResponse(
    Long id,
    String feedContent,
    FeedStatus feedStatus
) {
    public static FeedResponse from(Feed feed){
        return FeedResponse.builder()
            .id(feed.getFeedId())
            .feedContent(feed.getFeedContent())
            .feedStatus(feed.getFeedStatus())
            .build();
    }
}
