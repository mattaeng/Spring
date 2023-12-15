package com.mattaeng.mattaengapi.controller;

import com.mattaeng.mattaengapi.common.api.Api;
import com.mattaeng.mattaengapi.dto.feed.CreateFeedRequest;
import com.mattaeng.mattaengapi.dto.feed.CreateFeedResponse;
import com.mattaeng.mattaengapi.dto.feed.DeleteFeedRequest;
import com.mattaeng.mattaengapi.dto.feed.DeleteFeedResponse;
import com.mattaeng.mattaengapi.dto.feed.FeedResponse;
import com.mattaeng.mattaengapi.dto.feed.UpdateFeedRequest;
import com.mattaeng.mattaengapi.dto.feed.UpdateFeedResponse;
import com.mattaeng.mattaengapi.service.FeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.responses.ApiResponse;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feed")
public class FeedController {

    private final FeedService feedService;

    @Operation(summary = "Feed 조회")
    @GetMapping
    public Api<List<FeedResponse>> getFeeds() {
        return Api.ok(feedService.getFeeds());
    }

    @Operation(summary = "Feed 상세 조회")
    @GetMapping("/{id}")
    public Api<FeedResponse> getFeed(@PathVariable Long id){
        return Api.ok(feedService.getFeed(id));
    }

    @Operation(summary = "Feed 생성")
    @PostMapping("/create")
    public Api<CreateFeedResponse> createFeed(@RequestBody CreateFeedRequest createFeedRequest){
        return Api.ok(feedService.createFeed(createFeedRequest));
    }

    @Operation(summary = "Feed 수정")
    @PutMapping("/update")
    public Api<UpdateFeedResponse> updateFeed(@RequestBody UpdateFeedRequest updateFeedRequest, Long id){
        return Api.ok(feedService.updateFeed(updateFeedRequest, id));
    }

    @Operation(summary = "Feed 삭제")
    @DeleteMapping("/delete")
    public Api<DeleteFeedResponse> deleteFeed(@RequestBody DeleteFeedRequest deleteFeedRequest, Long id){
        return Api.ok(feedService.deleteFeed(deleteFeedRequest, id));
    }
}
