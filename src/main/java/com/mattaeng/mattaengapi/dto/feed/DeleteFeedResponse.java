package com.mattaeng.mattaengapi.dto.feed;

public record DeleteFeedResponse (
    boolean deleteFeedresult
){
    public static DeleteFeedResponse of(boolean deleteFeedresult){
        return new DeleteFeedResponse(deleteFeedresult);
    }
}
