package com.mattaeng.mattaengapi.dto.feed;

import com.mattaeng.mattaengapi.common.enums.FeedStatus;

public record DeleteFeedRequest (
    FeedStatus feedStatus
){

}
