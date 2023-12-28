package com.mattaeng.mattaengapi.dto.feed;

import com.mattaeng.mattaengapi.entity.Feed;
import lombok.Builder;

@Builder
public record CreateFeedResponse (
    String title,
    String content
)
{
    public static CreateFeedResponse from(Feed feed){
        return CreateFeedResponse.builder()
            .title(feed.getTitle())
            .content(feed.getContent())
            .build();
    }
}
