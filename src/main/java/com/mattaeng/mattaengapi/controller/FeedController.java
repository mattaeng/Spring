package com.mattaeng.mattaengapi.controller;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.dto.feed.CreateFeedRequest;
import com.mattaeng.mattaengapi.dto.feed.CreateFeedResponse;
import com.mattaeng.mattaengapi.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FeedController {

    private final FeedService feedService;

    @Operation(summary = "Feed 생성")
    @PostMapping("/feed")
    public Api<CreateFeedResponse> createFeed(@RequestBody CreateFeedRequest createFeedRequest){
        return Api.ok(feedService.createFeed(createFeedRequest));
    }
}
