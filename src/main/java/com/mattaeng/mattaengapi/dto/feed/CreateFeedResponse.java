package com.mattaeng.mattaengapi.dto.feed;

import com.mattaeng.mattaengapi.entity.Feed;
import lombok.Builder;

@Builder
public record CreateFeedResponse (
    String feedContent
)
{
    public static CreateFeedResponse from(Feed feed){
        return CreateFeedResponse.builder()
            .feedContent(feed.getFeedContent())
            .build();
    }
}
