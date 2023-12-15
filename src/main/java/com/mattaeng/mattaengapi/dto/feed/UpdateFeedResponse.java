package com.mattaeng.mattaengapi.dto.feed;

public record UpdateFeedResponse(
    boolean updateFeedresult
) {
    public static UpdateFeedResponse of(boolean updateFeedresult){
        return new UpdateFeedResponse(updateFeedresult);
    }
}
