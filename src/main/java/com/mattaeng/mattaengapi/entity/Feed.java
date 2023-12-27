package com.mattaeng.mattaengapi.entity;

import java.util.ArrayList;
import java.util.List;

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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@Lob
	private String content;

	private Long heart;

	@Enumerated(EnumType.STRING)
	private FeedStatus feedStatus;

	@OneToMany(mappedBy = "feed")
	private List<Comment> comments = new ArrayList<>();

	@Builder
	private Feed(String title, String content, Long heart, FeedStatus feedStatus, List<Comment> comments) {
		this.title = title;
		this.content = content;
		this.heart = heart;
		this.feedStatus = feedStatus;
		this.comments = comments != null ? comments : new ArrayList<>();
	}

	public static Feed from(CreateFeedRequest createFeedRequest) {
		return Feed.builder()
			.title(createFeedRequest.title())
			.content(createFeedRequest.content())
			.heart(0L)
			.feedStatus(FeedStatus.ACTIVATIE)
			.build();
	}

	public void updateFeed(UpdateFeedRequest updateFeedRequest) {
		if (updateFeedRequest.content() != null) {
			this.content = updateFeedRequest.content();
		}
	}

	public void deleteFeed(DeleteFeedRequest deleteFeedRequest) {
		if (deleteFeedRequest.feedStatus() != null) {
			this.feedStatus = FeedStatus.INACTIVATE;
		}
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setHeart(Long heart) {
		this.heart = heart;
	}

	public void setFeedStatus(FeedStatus feedStatus) {
		this.feedStatus = feedStatus;
	}
}
