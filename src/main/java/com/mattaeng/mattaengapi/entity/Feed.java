package com.mattaeng.mattaengapi.entity;

import com.mattaeng.mattaengapi.common.auditing.BaseTimeEntity;
import com.mattaeng.mattaengapi.common.enums.FeedStatus;
import com.mattaeng.mattaengapi.dto.feed.CreateFeedRequest;
import com.mattaeng.mattaengapi.dto.feed.DeleteFeedRequest;
import com.mattaeng.mattaengapi.dto.feed.UpdateFeedRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
public class Feed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private FeedStatus feedStatus;

//    @OneToMany(mappedBy = "feed")
//    private List<Comment> comments = new ArrayList<>();

    private Feed(Long id, String content, FeedStatus feedStatus){
        this.id = id;
        this.content = content;
        this.feedStatus = feedStatus;
    }

    public static Feed from(CreateFeedRequest createFeedRequest){
        return  Feed.builder()
            .id(0L)
            .content(createFeedRequest.feedContent())
            .feedStatus(FeedStatus.ACTIVATIE)
            .build();
    }
    public Feed() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFeedStatus(FeedStatus feedStatus) {
        this.feedStatus = feedStatus;
    }

    public void updateFeed(UpdateFeedRequest updateFeedRequest){
        if (updateFeedRequest.feedContent() != null) {
            this.content = updateFeedRequest.feedContent();
        }
    }

    public  void deleteFeed(DeleteFeedRequest deleteFeedRequest) {
        if (deleteFeedRequest.feedStatus() != null) {
            this.feedStatus = FeedStatus.INACTIVATE;
        }
    }
}
