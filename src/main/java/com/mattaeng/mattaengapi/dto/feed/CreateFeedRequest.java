package com.mattaeng.mattaengapi.dto.feed;


import lombok.Builder;
@Builder
public record CreateFeedRequest (
    Long id,
    String title,
    String content
){

}
