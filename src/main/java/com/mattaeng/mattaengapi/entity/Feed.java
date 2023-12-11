package com.mattaeng.mattaengapi.entity;

import com.mattaeng.mattaengapi.common.auditing.BaseTimeEntity;
import com.mattaeng.mattaengapi.common.enums.FeedStatus;
import com.mattaeng.mattaengapi.dto.feed.CreateFeedRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
public class Feed extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;

    @Lob
    private String feedContent;

    @Enumerated(EnumType.STRING)
    private FeedStatus feedStatus;

    private Feed(Long feedId, String feedContent, FeedStatus feedStatus){
        this.feedId = feedId;
        this.feedContent = feedContent;
        this.feedStatus = feedStatus;
    }

    public static Feed from(CreateFeedRequest createFeedRequest){
        return  Feed.builder()
            .feedId(0L)
            .feedContent(createFeedRequest.feedContent())
            .feedStatus(FeedStatus.ACTIVATIE)
            .build();
    }
    public Feed() {

    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent;
    }

    public void setFeedStatus(FeedStatus feedStatus) {
        this.feedStatus = feedStatus;
    }

}
